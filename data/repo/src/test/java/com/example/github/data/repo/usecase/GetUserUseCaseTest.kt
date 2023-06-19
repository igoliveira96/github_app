package com.example.github.data.repo.usecase

import com.example.github.data.repo.model.User
import com.example.github.data.repo.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class GetUserUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: RepoRepository

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getUserUseCase = GetUserUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @Test(expected = Throwable::class)
    fun `WHEN don't receive params MUST throw Exception`() = runTest {
        getUserUseCase.run()
    }

    @Test
    fun `getUser WHEN has get user MUST sent a user result`() = runTest {
        whenever(repository.getUser(any())).thenReturn(flowOf(User.EXAMPLE))

        getUserUseCase(
            params = GetUserUseCase.Params(login = "igoliveira96"),
            onSuccess = { result ->
                Assert.assertNotNull(result)
            },
            onError = { error ->
                throw error
            }
        )
    }

}