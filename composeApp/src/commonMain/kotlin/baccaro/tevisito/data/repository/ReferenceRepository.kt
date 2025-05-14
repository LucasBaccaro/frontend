package baccaro.tevisito.data.repository

import baccaro.tevisito.data.remote.dto.LocationDto
import baccaro.tevisito.data.remote.dto.CategoryDto

interface ReferenceRepository {
    suspend fun getLocations(): Result<List<LocationDto>>
    suspend fun getCategories(): Result<List<CategoryDto>>
}

class ReferenceRepositoryImpl(private val api: baccaro.tevisito.data.remote.ReferenceApi) : ReferenceRepository {
    override suspend fun getLocations(): Result<List<LocationDto>> =
        runCatching { api.getLocations() }

    override suspend fun getCategories(): Result<List<CategoryDto>> =
        runCatching { api.getCategories() }
} 