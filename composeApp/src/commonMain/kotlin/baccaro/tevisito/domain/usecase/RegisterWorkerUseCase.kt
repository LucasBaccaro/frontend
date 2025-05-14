package baccaro.tevisito.domain.usecase

import baccaro.tevisito.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.data.remote.dto.UserDto
import baccaro.tevisito.data.repository.AuthRepository

class RegisterWorkerUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: RegisterWorkerRequestDto): Result<UserDto> {
        return repository.registerWorker(request)
    }
} 