package baccaro.tevisito.com.authentication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.systemBarsPadding(),
        ) {
            composable(
                route = "login?showRegistrationSuccess={showRegistrationSuccess}",
                arguments = listOf(
                    navArgument("showRegistrationSuccess") {
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { backStackEntry ->
                val viewModel = koinViewModel<AuthViewModel>()
                val showRegistrationSuccess =
                    backStackEntry.arguments?.getBoolean("showRegistrationSuccess") ?: false
                LoginScreen(
                    viewModel = viewModel,
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    showRegistrationSuccess = showRegistrationSuccess
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = { navController.navigate("register") }) {
                        Text("¿No tenés cuenta? Registrate")
                    }
                }
            }
            composable("register") {
                val viewModel = koinViewModel<AuthViewModel>()
                RegisterScreen(
                    viewModel = viewModel,
                    onRegisterSuccess = {
                        navController.navigate("login?showRegistrationSuccess=true") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = { navController.navigate("login?showRegistrationSuccess=false") }) {
                        Text("¿Ya tenés cuenta? Iniciá sesión")
                    }
                }
            }
            composable("home") {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            "¡Bienvenido! Login/Registro exitoso.",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}