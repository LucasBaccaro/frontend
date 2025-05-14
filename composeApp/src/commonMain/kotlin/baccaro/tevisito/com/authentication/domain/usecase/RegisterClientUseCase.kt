package baccaro.tevisito.com.authentication.domain.usecase

import baccaro.tevisito.com.authentication.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.UserDto
import baccaro.tevisito.com.authentication.domain.AuthRepository

class RegisterClientUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: RegisterClientRequestDto): Result<UserDto> {
        return repository.registerClient(request)
    }
}