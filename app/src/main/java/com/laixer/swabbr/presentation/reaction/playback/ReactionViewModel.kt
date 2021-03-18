package com.laixer.swabbr.presentation.reaction.playback

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.laixer.swabbr.utils.resources.Resource
import com.laixer.swabbr.presentation.utils.todosortme.setError
import com.laixer.swabbr.presentation.utils.todosortme.setLoading
import com.laixer.swabbr.presentation.utils.todosortme.setSuccess
import com.laixer.swabbr.domain.usecase.ReactionUseCase
import com.laixer.swabbr.presentation.model.ReactionItem
import com.laixer.swabbr.presentation.model.ReactionWrapperItem
import com.laixer.swabbr.presentation.model.mapToDomain
import com.laixer.swabbr.presentation.model.mapToPresentation
import com.laixer.swabbr.presentation.recording.UploadVideoViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import java.util.*

/**
 *  View model containing functionality for watching and posting
 *  reactions. This includes uploading functionality.
 */
class ReactionViewModel constructor(
    mHttpClient: OkHttpClient,
    private val reactionsUseCase: ReactionUseCase,
    context: Context
) : UploadVideoViewModel(mHttpClient, context) {
    /**
     *  Resource in which [getReaction] stores its result.
     */
    val reaction = MutableLiveData<Resource<ReactionWrapperItem>>()

    /**
     *  Gets a reaction from the data store and stores it in
     *  [reaction] on completion.
     */
    fun getReaction(reactionId: UUID) = compositeDisposable.add(
        reactionsUseCase.get(reactionId)
            .doOnSubscribe { reaction.setLoading() }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { reaction.setSuccess(it.mapToPresentation()) },
                { reaction.setError(it.message) }
            )
    )

    // TODO This is messy.
    // TODO Hard coded content types
    // TODO Make sure the order of execution is correct! It works though...
    // TODO This error hides
    /**
     *  Uploads a [ReactionItem] including thumbnail and posts the
     *  reaction to the backend.
     *
     *  @param localVideoUri Location of the reaction video file.
     *  @param localThumbnailUri Location of the thumbnail file.
     *  @param targetVlogId The vlog to post a reaction to.
     *  @param isPrivate Indicates reaction accessibility.
     */
    fun postReaction(
        localVideoUri: Uri,
        localThumbnailUri: Uri,
        targetVlogId: UUID,
        isPrivate: Boolean
    ): Completable =
        reactionsUseCase.generateUploadWrapper()
            .map { uploadWrapper ->
                Completable.fromCallable {
                    uploadFile(localVideoUri, uploadWrapper.videoUploadUri, "video/mp4")
                    uploadFile(localThumbnailUri, uploadWrapper.thumbnailUploadUri, "image/jpeg")
                }
                    .andThen(
                        reactionsUseCase.postReaction(
                            ReactionItem.createForPosting(
                                id = uploadWrapper.id,
                                targetVlogId = targetVlogId,
                                isPrivate = isPrivate
                            ).mapToDomain()
                        )
                    )
                    .subscribeOn(Schedulers.io())
                    .subscribe({}, {}) // We always want an error handler even if it's empty.
            }
            .ignoreElement()

    companion object {
        private const val TAG = "ReactionViewModel"
    }
}
