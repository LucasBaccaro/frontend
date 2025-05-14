package baccaro.tevisito.domain.usecase

import baccaro.tevisito.data.remote.dto.CategoryDto
import baccaro.tevisito.data.repository.ReferenceRepository

class GetCategoriesUseCase(private val repository: ReferenceRepository) {
    suspend operator fun invoke(): Result<List<CategoryDto>> = repository.getCategories()
} 