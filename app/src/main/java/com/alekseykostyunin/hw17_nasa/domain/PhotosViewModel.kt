package com.alekseykostyunin.hw17_nasa.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alekseykostyunin.hw17_nasa.entity.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhotosViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val pagedPhoto: Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { PhotosPagingSource() }
    ).flow.cachedIn(viewModelScope)
}