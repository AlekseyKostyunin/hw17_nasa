package com.alekseykostyunin.hw17_nasa.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alekseykostyunin.hw17_nasa.data.PhotosRepository
import com.alekseykostyunin.hw17_nasa.entity.Photo

class PhotosPagingSource : PagingSource<Int, Photo>() {

    private val repository = PhotosRepository()

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getPhotos(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private val FIRST_PAGE = 1
    }
}