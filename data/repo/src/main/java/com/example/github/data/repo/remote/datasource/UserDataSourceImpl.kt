package com.example.github.data.repo.remote.datasource

import androidx.paging.PagingState
import com.example.github.data.repo.datasource.UserDataSource
import com.example.github.data.repo.model.User
import com.example.github.data.repo.remote.RepoService
import com.example.github.data.repo.remote.mapper.UserMapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val service: RepoService
) : UserDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val result = UserMapper.toDomain(
                service.users(
                    page = params.key ?: STARTING_PAGE_INDEX,
                    perPage = 10
                )
            )
            LoadResult.Page(
                data = result,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

}