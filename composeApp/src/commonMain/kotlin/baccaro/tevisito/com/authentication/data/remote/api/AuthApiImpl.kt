package baccaro.tevisito.com.authentication.data.remote.api

import baccaro.tevisito.com.authentication.data.remote.dto.APIResponse
import baccaro.tevisito.com.authentication.data.remote.dto.LoginDataDto
import baccaro.tevisito.com.authentication.data.remote.dto.LoginRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class AuthApiImpl(private val client: HttpClient) : AuthApi {
    private val baseUrl = "https://backend-aeo4.onrender.com/api/v1/auth"

    override suspend fun login(request: LoginRequestDto): APIResponse<LoginDataDto> {
        val response: HttpResponse = client.submitForm(
            url = "$baseUrl/token",
            formParameters = Parameters.build {
                append("grant_type", "password")
                append("username", request.username)
                append("password", request.password)
            }
        )
        val body = response.bodyAsText()
        return Json { ignoreUnknownKeys = true }.decodeFromString(body)
    }

    override suspend fun registerClient(request: RegisterClientRequestDto): APIResponse<UserDto> {
        val response: HttpResponse = client.post("$baseUrl/register/client") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val body = response.bodyAsText()
        return Json { ignoreUnknownKeys = true }.decodeFromString(body)
    }

    override suspend fun registerWorker(request: RegisterWorkerRequestDto): APIResponse<UserDto> {
        val response: HttpResponse = client.post("$baseUrl/register/worker") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val body = response.bodyAsText()
        return Json { ignoreUnknownKeys = true }.decodeFromString(body)
    }
} 