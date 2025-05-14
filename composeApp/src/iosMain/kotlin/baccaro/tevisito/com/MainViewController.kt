package baccaro.tevisito.com

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import baccaro.tevisito.di.appModule
import baccaro.tevisito.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(
                appModule,
            )
        }
    }
) { App() }