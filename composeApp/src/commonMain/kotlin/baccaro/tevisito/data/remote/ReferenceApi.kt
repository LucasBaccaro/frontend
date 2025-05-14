package baccaro.tevisito.data.remote

import baccaro.tevisito.data.remote.dto.LocationDto
import baccaro.tevisito.data.remote.dto.CategoryDto

interface ReferenceApi {
    suspend fun getLocations(): List<LocationDto>
    suspend fun getCategories(): List<CategoryDto>
} 