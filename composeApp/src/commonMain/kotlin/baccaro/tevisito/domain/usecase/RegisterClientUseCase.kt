package baccaro.tevisito.domain.usecase

import baccaro.tevisito.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.data.remote.dto.UserDto
import baccaro.tevisito.data.repository.AuthRepository

class RegisterClientUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: RegisterClientRequestDto): Result<UserDto> {
        return repository.registerClient(request)
    }
}