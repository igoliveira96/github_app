package com.example.github.data.repo.datasource

import androidx.paging.PagingSource
import com.example.github.data.repo.model.User

internal abstract class UserDataSource : PagingSource<Int, User>()