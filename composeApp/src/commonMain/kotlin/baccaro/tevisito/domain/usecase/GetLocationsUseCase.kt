package baccaro.tevisito.domain.usecase

import baccaro.tevisito.data.remote.dto.LocationDto
import baccaro.tevisito.data.repository.ReferenceRepository

class GetLocationsUseCase(private val repository: ReferenceRepository) {
    suspend operator fun invoke(): Result<List<LocationDto>> = repository.getLocations()
} 