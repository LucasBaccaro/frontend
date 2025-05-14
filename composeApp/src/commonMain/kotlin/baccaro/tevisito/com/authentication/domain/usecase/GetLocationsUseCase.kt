package baccaro.tevisito.com.authentication.domain.usecase

import baccaro.tevisito.com.authentication.data.remote.dto.LocationDto
import baccaro.tevisito.com.authentication.domain.ReferenceRepository

class GetLocationsUseCase(private val repository: ReferenceRepository) {
    suspend operator fun invoke(): Result<List<LocationDto>> = repository.getLocations()
} 