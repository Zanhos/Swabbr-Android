@file:Suppress("IllegalIdentifier")

package com.laixer.swabbr.datasource.cache

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.laixer.cache.ReactiveCache
import com.laixer.swabbr.domain.model.User
import com.laixer.swabbr.user
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AuthCacheDataSourceImplTest {

    private lateinit var dataSource: AuthCacheDataSourceImpl

    private val mockCache: ReactiveCache<Pair<String, User>> = mock()

    val key = "Authorized user"

    private val cacheItem = Pair("token", user)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = AuthCacheDataSourceImpl(mockCache)
    }

    @Test
    fun `get authorized user cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheItem))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheItem)
    }

    @Test
    fun `get authorized user cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertError(throwable)
    }
}
