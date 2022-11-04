package practice.effective.chooseyourhero.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
    @Json(name = "data")
    val data: DataDto
)
