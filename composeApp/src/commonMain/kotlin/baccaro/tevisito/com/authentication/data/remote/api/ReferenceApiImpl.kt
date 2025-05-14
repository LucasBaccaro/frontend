package baccaro.tevisito.com.authentication.data.remote.api

import baccaro.tevisito.com.authentication.data.remote.dto.APIResponse
import baccaro.tevisito.com.authentication.data.remote.dto.CategoryDto
import baccaro.tevisito.com.authentication.data.remote.dto.LocationDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class ReferenceApiImpl(private val client: HttpClient) : ReferenceApi {
    private val baseUrl = "https://backend-aeo4.onrender.com/api/v1/references"

    override suspend fun getLocations(): List<LocationDto> {
        val response = client.get("$baseUrl/locations")
        val body = response.bodyAsText()
        // El backend responde con APIResponse<List<LocationDto>>
        val apiResponse =
            Json { ignoreUnknownKeys = true }.decodeFromString<APIResponse<List<LocationDto>>>(body)
        return apiResponse.data ?: emptyList()
    }

    override suspend fun getCategories(): List<CategoryDto> {
        val response = client.get("$baseUrl/categories")
        val body = response.bodyAsText()
        val apiResponse =
            Json { ignoreUnknownKeys = true }.decodeFromString<APIResponse<List<CategoryDto>>>(body)
        return apiResponse.data ?: emptyList()
    }
} 