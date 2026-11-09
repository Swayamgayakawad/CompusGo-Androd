package ViweModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Status {
    var isLoggedIn by mutableStateOf(false)
}