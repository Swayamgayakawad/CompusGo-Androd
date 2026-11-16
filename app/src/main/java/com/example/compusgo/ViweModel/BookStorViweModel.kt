
package com.example.compusgo.ViweModel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class BookStorViweModel : ViewModel() {

    var quantities = mutableStateMapOf<String, Int>()
        private set

    fun increaseQuantity(title: String) {
        val current = quantities[title] ?: 0
        quantities[title] = current + 1
    }

    fun decreaseQuantity(title: String) {
        val current = quantities[title] ?: 0

        if (current > 0) {
            quantities[title] = current - 1
        }
    }

    fun getQuantity(title: String): Int {
        return quantities[title] ?: 0
    }
}
