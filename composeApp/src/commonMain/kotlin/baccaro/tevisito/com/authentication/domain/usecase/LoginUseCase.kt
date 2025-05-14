package baccaro.tevisito.com.authentication.domain.usecase

import baccaro.tevisito.com.authentication.domain.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return repository.login(username, password)
    }
}