package com.laixer.swabbr.domain.usecase

import com.laixer.swabbr.domain.model.User
import com.laixer.swabbr.domain.repository.UserRepository
import io.reactivex.Single
import java.util.*

/**
 *  Use case with functionality with regards to other users.
 */
class UsersUseCase constructor(private val userRepository: UserRepository) {
    /**
     *  Get a user based on its id.
     *
     *  @param userId The user to get.
     *  @param refresh Force a data refresh.
     */
    fun get(userId: UUID, refresh: Boolean): Single<User> = userRepository.get(userId, refresh)

    /**
     *  Search for users in our data store.
     *
     *  @param query Search query, can't be empty.
     *  @param offset Offset of the result set.
     *  @param limit Maximum result set size.
     */
    fun search(query: String, offset: Int = 0, limit: Int = 25): Single<List<User>> =
        userRepository.search(query, offset, limit)

    /**
     *  Get all users that a user is following itself.
     *
     *  @param userId The follow request requesting user.
     *  @param forceRefresh Force a cache update if any caching is used.
     */
    fun getFollowing(userId: UUID, refresh: Boolean): Single<List<User>> =
        userRepository.getFollowing(userId, refresh)
}
