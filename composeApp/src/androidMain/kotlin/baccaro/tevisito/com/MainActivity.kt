package baccaro.tevisito.com

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import baccaro.tevisito.com.authentication.presentation.App
import baccaro.tevisito.com.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@Application)
        }
    }
}