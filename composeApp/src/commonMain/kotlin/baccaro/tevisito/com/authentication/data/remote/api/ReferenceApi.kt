package baccaro.tevisito.com.authentication.data.remote.api

import baccaro.tevisito.com.authentication.data.remote.dto.CategoryDto
import baccaro.tevisito.com.authentication.data.remote.dto.LocationDto

interface ReferenceApi {
    suspend fun getLocations(): List<LocationDto>
    suspend fun getCategories(): List<CategoryDto>
} 