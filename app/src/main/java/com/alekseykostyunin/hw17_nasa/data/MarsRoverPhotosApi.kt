package com.alekseykostyunin.hw17_nasa.data

import com.alekseykostyunin.hw17_nasa.entity.PagedPhotosList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov"
private const val API_KEY = "Ddl3WT1h3veDAIJlAdYjOtYoQSidthzj0s4vgRaW"

object Retrofit {
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val marsRoverPhotosApi: MarsRoverPhotosApi = retrofit.create(MarsRoverPhotosApi::class.java)
}

interface MarsRoverPhotosApi {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("/mars-photos/api/v1/rovers/curiosity/photos?&sol=1000&api_key=$API_KEY")
    suspend fun getPhotos(@Query("page") page: Int): PagedPhotosList

}
