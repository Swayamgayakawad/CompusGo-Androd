
package com.example.compusgo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.compusgo.ViewModel.FoodItem

data class CartItem(
    val food: FoodItem,
    val quantity: Int
)

class CartViewModel : ViewModel() {

    val cartItems = mutableStateListOf<CartItem>()

    fun addToCart(food: FoodItem) {

        val index = cartItems.indexOfFirst {
            it.food.name == food.name
        }

        if (index != -1) {

            val item = cartItems[index]

            cartItems[index] = item.copy(
                quantity = item.quantity + 1
            )

        } else {

            cartItems.add(
                CartItem(food, 1)
            )
        }
    }

    fun increaseQuantity(food: FoodItem) {

        addToCart(food)
    }


    fun decreaseQuantity(food: FoodItem) {

        val index = cartItems.indexOfFirst {
            it.food.name == food.name
        }

        if (index != -1) {

            val item = cartItems[index]

            if (item.quantity > 1) {

                cartItems[index] = item.copy(
                    quantity = item.quantity - 1
                )

            } else {

                cartItems.removeAt(index)
            }
        }
    }

    fun getTotalPrice(): Int {

        return cartItems.sumOf {
            it.food.price * it.quantity
        }
    }

    // Clear cart
    fun clearCart() {
        cartItems.clear()
    }
}

