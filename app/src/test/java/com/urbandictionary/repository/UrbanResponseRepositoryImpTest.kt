package com.urbandictionary.repository

import androidx.lifecycle.MutableLiveData
import com.urbandictionary.data.repository.UrbanDictionaryRepositoryImp
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


class UrbanResponseRepositoryImpTest {

    @MockK
    lateinit var postsRepository: UrbanDictionaryRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getLocalDefine() = runBlocking {
        val posts = mockk<MutableLiveData<UrbanDictionaryResponse>>()
        every { runBlocking { postsRepository.getDefine("pepa") } } returns (posts)

        val result = postsRepository.getDefine("pepa")
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$posts] must be matches on each other!",
            result,
            CoreMatchers.`is`(posts)
        )
    }

    @Test
    fun getRemote() = runBlocking {
        val posts = mockk<MutableLiveData<UrbanDictionaryResponse>>()
        every { runBlocking { postsRepository.getFromRemoteDataSource("pepa") } } returns (posts)

        val result = postsRepository.getFromRemoteDataSource("pepa")
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$posts] must be matches on each other!",
            result,
            CoreMatchers.`is`(posts)
        )
    }


}