package com.example.github.data.repo.remote.datasource

import androidx.paging.PagingState
import com.example.github.data.repo.datasource.RepositoriesDataSource
import com.example.github.data.repo.model.Repo
import com.example.github.data.repo.remote.RepoService
import com.example.github.data.repo.remote.mapper.RepoMapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class RepositoriesDataSourceImpl @Inject constructor(
    private val user: String,
    private val service: RepoService
) : RepositoriesDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val result = RepoMapper.toDomain(
                service.repositories(
                    user = user,
                    page = params.key ?: STARTING_PAGE_INDEX,
                    perPage = 10
                )
            )

            val nextKey = if (result.isEmpty()) {
                null
            } else {
                params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            }

            LoadResult.Page(
                data = result,
                prevKey = params.key,
                nextKey = nextKey
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

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition
    }

}