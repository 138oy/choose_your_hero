package practice.effective.chooseyourhero.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDto(
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
    val extension: String,
)
