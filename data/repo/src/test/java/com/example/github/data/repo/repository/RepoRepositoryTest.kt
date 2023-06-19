package com.example.github.data.repo.repository

import com.example.github.data.repo.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class RepoRepositoryTest {

    @Mock
    private lateinit var repository: RepoRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getUser should return user when api return success`() = runTest {
        whenever(repository.getUser(any())).thenReturn(flowOf(User.EXAMPLE))

        val user = repository.getUser("igoliveira96").first()
        Assert.assertEquals(User.EXAMPLE.id, user.id)
    }

    @Test(expected = Throwable::class)
    fun `getUser should return throwable when api return error`() = runTest {
        whenever(repository.getUser(any())).thenThrow()

        repository.getUser("igoliveira96")
    }

}