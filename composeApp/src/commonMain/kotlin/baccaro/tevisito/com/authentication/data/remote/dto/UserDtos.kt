package baccaro.tevisito.com.authentication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: String? = null,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("dni") val dni: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("role") val role: String,
    @SerialName("location_id") val locationId: String? = null,
    @SerialName("is_active") val isActive: Boolean? = null,
    @SerialName("is_verified") val isVerified: Boolean? = null,
    @SerialName("category_id") val categoryId: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("average_rating") val averageRating: Float? = null,
    @SerialName("ratings_count") val ratingsCount: Int? = null
)

@Serializable
data class APIErrorDetail(
    @SerialName("loc") val loc: List<String>,
    @SerialName("msg") val msg: String,
    @SerialName("type") val type: String
)

@Serializable
data class APIError(
    @SerialName("code") val code: String,
    @SerialName("message") val message: String,
    @SerialName("details") val details: List<APIErrorDetail>? = null
)

@Serializable
data class APIResponse<T>(
    @SerialName("success") val success: Boolean,
    @SerialName("data") val data: T? = null,
    @SerialName("error") val error: APIError? = null
) 