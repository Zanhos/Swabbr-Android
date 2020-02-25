package com.laixer.swabbr.domain.usecase

import com.laixer.swabbr.domain.repository.FollowRepository
import com.laixer.swabbr.followRequest
import com.laixer.swabbr.user
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class FollowUseCaseTest {

    private lateinit var usecase: FollowUseCase
    private val mockRepository: FollowRepository = mock()
    private val userId = user.id

    @Before
    fun setUp() {
        usecase = FollowUseCase(mockRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        whenever(mockRepository.getFollowStatus(userId)).thenReturn(Single.just(followRequest))
        // when
        val test = usecase.getFollowRequest(userId).test()
        // then
        verify(mockRepository).getFollowStatus(userId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(followRequest)
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        whenever(mockRepository.getFollowStatus(userId)).thenReturn(Single.error(throwable))
        // when
        val test = usecase.getFollowRequest(userId).test()
        // then
        verify(mockRepository).getFollowStatus(userId)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
