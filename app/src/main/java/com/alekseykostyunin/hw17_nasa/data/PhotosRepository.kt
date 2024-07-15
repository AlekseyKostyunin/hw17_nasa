package com.alekseykostyunin.hw17_nasa.data

import com.alekseykostyunin.hw17_nasa.entity.Photo
import kotlinx.coroutines.delay

class PhotosRepository {

    suspend fun getPhotos(page: Int): List<Photo> {
        delay(2000)
        return Retrofit.marsRoverPhotosApi.getPhotos(page).photos
    }
}