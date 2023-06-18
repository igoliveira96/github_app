package com.example.github.data.repo.datasource

import androidx.paging.PagingSource
import com.example.github.data.repo.model.Repo

internal abstract class RepositoriesDataSource : PagingSource<Int, Repo>()