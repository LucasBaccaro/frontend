package baccaro.tevisito.com.authentication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import baccaro.tevisito.com.authentication.data.remote.dto.CategoryDto
import baccaro.tevisito.com.authentication.data.remote.dto.LocationDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterClientRequestDto
import baccaro.tevisito.com.authentication.data.remote.dto.RegisterWorkerRequestDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: AuthViewModel, onRegisterSuccess: () -> Unit) {
    var isWorker by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf<LocationDto?>(null) }
    var selectedCategory by remember { mutableStateOf<CategoryDto?>(null) }

    val authState by viewModel.authState.collectAsState()
    val locationsState by viewModel.locationsState.collectAsState()
    val categoriesState by viewModel.categoriesState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onRegisterSuccess()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            TextButton(onClick = { isWorker = false }) {
                Text(
                    "Cliente",
                    color = if (!isWorker) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
            TextButton(onClick = { isWorker = true }) {
                Text(
                    "Trabajador",
                    color = if (isWorker) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // ComboBox de Location
        when (locationsState) {
            is ReferenceState.Loading -> {
                Text("Cargando ubicaciones...", modifier = Modifier.fillMaxWidth())
            }

            is ReferenceState.Error -> {
                Text(
                    (locationsState as ReferenceState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is ReferenceState.Success -> {
                val locations = (locationsState as ReferenceState.Success<LocationDto>).data
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedLocation?.name ?: "Seleccionar ubicación",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Ubicación") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        locations.forEach { location ->
                            DropdownMenuItem(
                                text = { Text(location.name) },
                                onClick = {
                                    selectedLocation = location
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (isWorker) {
            // ComboBox de Category solo para worker
            when (categoriesState) {
                is ReferenceState.Loading -> {
                    Text("Cargando categorías...", modifier = Modifier.fillMaxWidth())
                }

                is ReferenceState.Error -> {
                    Text(
                        (categoriesState as ReferenceState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is ReferenceState.Success -> {
                    val categories = (categoriesState as ReferenceState.Success<CategoryDto>).data
                    var expanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedCategory?.name ?: "Seleccionar categoría",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Categoría") },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category.name) },
                                    onClick = {
                                        selectedCategory = category
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección (opcional)") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (isWorker) {
                    if (selectedLocation != null && selectedCategory != null) {
                        viewModel.registerWorker(
                            RegisterWorkerRequestDto(
                                email = email,
                                password = password,
                                firstName = firstName,
                                lastName = lastName,
                                dni = dni,
                                phoneNumber = phoneNumber,
                                locationId = selectedLocation!!.id,
                                categoryId = selectedCategory!!.id,
                                address = address.ifBlank { null }
                            )
                        )
                    }
                } else {
                    if (selectedLocation != null) {
                        viewModel.registerClient(
                            RegisterClientRequestDto(
                                email = email,
                                password = password,
                                firstName = firstName,
                                lastName = lastName,
                                dni = dni,
                                phoneNumber = phoneNumber,
                                address = address,
                                locationId = selectedLocation!!.id
                            )
                        )
                    }
                }
            },
            enabled = authState !is AuthState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
            } else {
                Text("Registrarme")
            }
        }
        if (authState is AuthState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = (authState as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
} 