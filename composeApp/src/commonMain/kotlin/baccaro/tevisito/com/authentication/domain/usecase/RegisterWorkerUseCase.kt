package baccaro.tevisito.com.authentication.domain.usecase

import baccaro.tevisito.com.authentication.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.UserDto
import baccaro.tevisito.com.authentication.domain.AuthRepository

class RegisterWorkerUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: RegisterWorkerRequestDto): Result<UserDto> {
        return repository.registerWorker(request)
    }
} 