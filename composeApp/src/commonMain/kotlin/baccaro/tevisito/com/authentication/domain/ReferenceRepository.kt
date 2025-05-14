package baccaro.tevisito.com.authentication.domain

import baccaro.tevisito.com.authentication.data.remote.api.ReferenceApi
import baccaro.tevisito.com.authentication.data.remote.dto.CategoryDto
import baccaro.tevisito.com.authentication.data.remote.dto.LocationDto

interface ReferenceRepository {
    suspend fun getLocations(): Result<List<LocationDto>>
    suspend fun getCategories(): Result<List<CategoryDto>>
}

class ReferenceRepositoryImpl(private val api: ReferenceApi) :
    ReferenceRepository {
    override suspend fun getLocations(): Result<List<LocationDto>> =
        runCatching { api.getLocations() }

    override suspend fun getCategories(): Result<List<CategoryDto>> =
        runCatching { api.getCategories() }
} 