package baccaro.tevisito.data.repository

import baccaro.tevisito.data.datastore.TokenDataStore
import baccaro.tevisito.data.remote.AuthApi
import baccaro.tevisito.data.remote.dto.LoginRequestDto
import baccaro.tevisito.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.data.remote.dto.UserDto

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun registerClient(request: RegisterClientRequestDto): Result<UserDto>
    suspend fun registerWorker(request: RegisterWorkerRequestDto): Result<UserDto>
    suspend fun getToken(): String?
    suspend fun logout()
}

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {
    override suspend fun login(username: String, password: String): Result<Unit> {
        return try {
            val response = api.login(LoginRequestDto(username, password))
            if (response.success && response.data != null) {
                tokenDataStore.saveToken(response.data.accessToken)
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.error?.message ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerClient(request: RegisterClientRequestDto): Result<UserDto> {
        return try {
            val response = api.registerClient(request)
            if (response.success && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.error?.message ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerWorker(request: RegisterWorkerRequestDto): Result<UserDto> {
        return try {
            val response = api.registerWorker(request)
            if (response.success && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.error?.message ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getToken(): String? = tokenDataStore.getToken()

    override suspend fun logout() {
        tokenDataStore.clearToken()
    }
} 