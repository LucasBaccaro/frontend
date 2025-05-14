package baccaro.tevisito.com.authentication.data.remote.api

import baccaro.tevisito.com.authentication.data.remote.dto.APIResponse
import baccaro.tevisito.com.authentication.data.remote.dto.LoginDataDto
import baccaro.tevisito.com.authentication.data.remote.dto.LoginRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.UserDto

interface AuthApi {
    suspend fun login(request: LoginRequestDto): APIResponse<LoginDataDto>
    suspend fun registerClient(request: RegisterClientRequestDto): APIResponse<UserDto>
    suspend fun registerWorker(request: RegisterWorkerRequestDto): APIResponse<UserDto>
} 