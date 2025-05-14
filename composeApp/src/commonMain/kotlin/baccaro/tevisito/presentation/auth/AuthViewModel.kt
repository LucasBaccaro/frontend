package baccaro.tevisito.presentation.auth

import androidx.lifecycle.ViewModel
import baccaro.tevisito.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.data.remote.dto.RegisterWorkerRequestDto
import baccaro.tevisito.domain.usecase.LoginUseCase
import baccaro.tevisito.domain.usecase.RegisterClientUseCase
import baccaro.tevisito.domain.usecase.RegisterWorkerUseCase
import baccaro.tevisito.domain.usecase.GetLocationsUseCase
import baccaro.tevisito.domain.usecase.GetCategoriesUseCase
import baccaro.tevisito.data.remote.dto.LocationDto
import baccaro.tevisito.data.remote.dto.CategoryDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

sealed class ReferenceState<out T> {
    object Loading : ReferenceState<Nothing>()
    data class Success<T>(val data: List<T>) : ReferenceState<T>()
    data class Error(val message: String) : ReferenceState<Nothing>()
}

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerClientUseCase: RegisterClientUseCase,
    private val registerWorkerUseCase: RegisterWorkerUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _locationsState = MutableStateFlow<ReferenceState<LocationDto>>(ReferenceState.Loading)
    val locationsState: StateFlow<ReferenceState<LocationDto>> = _locationsState.asStateFlow()

    private val _categoriesState = MutableStateFlow<ReferenceState<CategoryDto>>(ReferenceState.Loading)
    val categoriesState: StateFlow<ReferenceState<CategoryDto>> = _categoriesState.asStateFlow()

    init {
        loadLocations()
        loadCategories()
    }

    fun login(username: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = loginUseCase(username, password)
            _authState.value = when {
                result.isSuccess -> AuthState.Success
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    fun registerClient(request: RegisterClientRequestDto) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = registerClientUseCase(request)
            _authState.value = when {
                result.isSuccess -> AuthState.Success
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    fun registerWorker(request: RegisterWorkerRequestDto) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = registerWorkerUseCase(request)
            _authState.value = when {
                result.isSuccess -> AuthState.Success
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    fun loadLocations() {
        _locationsState.value = ReferenceState.Loading
        viewModelScope.launch {
            val result = getLocationsUseCase()
            _locationsState.value = result.fold(
                onSuccess = { ReferenceState.Success(it) },
                onFailure = { ReferenceState.Error(it.message ?: "Error al cargar locaciones") }
            )
        }
    }

    fun loadCategories() {
        _categoriesState.value = ReferenceState.Loading
        viewModelScope.launch {
            val result = getCategoriesUseCase()
            _categoriesState.value = result.fold(
                onSuccess = { ReferenceState.Success(it) },
                onFailure = { ReferenceState.Error(it.message ?: "Error al cargar categorías") }
            )
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
} 