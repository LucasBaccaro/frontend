package baccaro.tevisito.data.remote

import baccaro.tevisito.data.remote.dto.APIResponse
import baccaro.tevisito.data.remote.dto.LoginRequestDto
import baccaro.tevisito.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.data.remote.dto.UserDto

interface AuthApi {
    suspend fun login(request: LoginRequestDto): APIResponse<LoginDataDto>
    suspend fun registerClient(request: RegisterClientRequestDto): APIResponse<UserDto>
    suspend fun registerWorker(request: RegisterWorkerRequestDto): APIResponse<UserDto>
} 