package com.laixer.swabbr.data.repository

import com.laixer.swabbr.data.datasource.VlogCacheDataSource
import com.laixer.swabbr.data.datasource.VlogRemoteDataSource
import com.laixer.swabbr.data.datasource.model.WatchVlogResponse
import com.laixer.swabbr.domain.model.LikeList
import com.laixer.swabbr.domain.model.Vlog
import com.laixer.swabbr.domain.repository.VlogRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.UUID

class VlogRepositoryImpl constructor(
    private val cacheDataSource: VlogCacheDataSource,
    private val remoteDataSource: VlogRemoteDataSource
) : VlogRepository {

    override fun getUserVlogs(userId: UUID, refresh: Boolean): Single<List<Vlog>> = when (refresh) {
        true -> remoteDataSource.getUserVlogs(userId).flatMap(cacheDataSource::set)
        false -> cacheDataSource.getUserVlogs(userId).onErrorResumeNext { getUserVlogs(userId, refresh = true) }
    }

    override fun get(vlogId: UUID, refresh: Boolean): Single<Vlog> = when (refresh) {
        true -> remoteDataSource.get(vlogId).flatMap(cacheDataSource::set)
        false -> cacheDataSource.get(vlogId).onErrorResumeNext { get(vlogId, refresh = true) }
    }

    override fun getRecommendedVlogs(refresh: Boolean): Single<List<Vlog>> = when (refresh) {
        true -> remoteDataSource.getRecommendedVlogs().flatMap(cacheDataSource::setRecommendedVlogs)
        false -> cacheDataSource.getRecommendedVlogs().onErrorResumeNext { getRecommendedVlogs(refresh = true) }
    }

    override fun delete(vlogId: UUID): Completable = remoteDataSource.delete(vlogId).doOnComplete{ cacheDataSource.delete(vlogId) }

    override fun getReactionCount(vlogId: UUID): Single<Int> = remoteDataSource.getReactionCount(vlogId)


    override fun getLikes(vlogId: UUID): Single<LikeList> = remoteDataSource.getLikes(vlogId)

    override fun like(vlogId: UUID): Completable =
        remoteDataSource.like(vlogId)

    override fun unlike(vlogId: UUID): Completable =
        remoteDataSource.unlike(vlogId)

    override fun watch(vlogId: UUID): Single<WatchVlogResponse> = remoteDataSource.watch(vlogId)
}
