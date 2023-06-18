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

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, User> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX

            val result = UserMapper.toDomain(
                service.users(
                    page = position,
                    perPage = 10
                )
            )

            val nextKey = if (result.isEmpty()) {
                null
            } else {
                result.last().id
            }

            LoadResult.Page(
                data = result,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1L
        private const val PAGE_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Long, User>): Long? {
        return state.anchorPosition?.toLong()
    }

}