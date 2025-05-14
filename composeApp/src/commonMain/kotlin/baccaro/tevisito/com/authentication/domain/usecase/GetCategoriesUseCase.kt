package baccaro.tevisito.com.authentication.domain.usecase

import baccaro.tevisito.com.authentication.data.remote.dto.CategoryDto
import baccaro.tevisito.com.authentication.domain.ReferenceRepository

class GetCategoriesUseCase(private val repository: ReferenceRepository) {
    suspend operator fun invoke(): Result<List<CategoryDto>> = repository.getCategories()
} 