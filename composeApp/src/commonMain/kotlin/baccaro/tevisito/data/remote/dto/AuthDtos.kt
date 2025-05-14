package baccaro.tevisito.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
)

@Serializable
data class RegisterClientRequestDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("dni") val dni: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("address") val address: String,
    @SerialName("location_id") val locationId: String,
)

@Serializable
data class RegisterWorkerRequestDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("dni") val dni: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("location_id") val locationId: String,
    @SerialName("category_id") val categoryId: String,
    @SerialName("address") val address: String? = null,
)