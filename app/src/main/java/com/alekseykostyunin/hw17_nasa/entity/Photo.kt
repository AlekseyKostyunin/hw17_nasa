package com.alekseykostyunin.hw17_nasa.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PagedPhotosList(
    @Json(name = "photos") val photos : List<Photo>
)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id") val id : Int,
    @Json(name = "sol") val sol : Int,
    @Json(name = "rover") val rover : Rover,
    @Json(name = "camera") val camera : Camera,
    @Json(name = "img_src") val imgSrc : String,
    @Json(name = "earth_date") val earthDate : String,
)

@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name = "name") val name : String
)

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "name") val name : String
)