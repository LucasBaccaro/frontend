package baccaro.tevisito.com.authentication.data.datastore

import com.russhwolf.settings.Settings

class TokenDataStore {
    // Usando el constructor no-arg de Settings que funciona en todas las plataformas
    private val settings = Settings()

    companion object {
        const val TOKEN_KEY = "jwt_token"
    }

    /**
     * Guarda un token JWT
     */
    fun saveToken(token: String) {
        settings.putString(
            TOKEN_KEY,
            token
        )
    }

    /**
     * Obtiene el token JWT almacenado o null si no existe
     */
    fun getToken(): String? {
        return settings.getStringOrNull(TOKEN_KEY)
    }

    /**
     * Elimina el token JWT almacenado
     */
    fun clearToken() {
        settings.remove(TOKEN_KEY)
    }

    /**
     * Comprueba si hay un token JWT almacenado
     */
    fun hasToken(): Boolean {
        return settings.hasKey(TOKEN_KEY)
    }
}