
package com.example.compusgo.ViewModel

import com.example.compusgo.R

data class FoodItem(
    val name: String,
    val price: Int,
    val image: Int
)

val foods = listOf(
    FoodItem("Idli", 30, R.drawable.idli),
    FoodItem("Gobi Manchurian", 60, R.drawable.gobi_manchurian),
    FoodItem("Gobi Rasa", 50, R.drawable.gobi_rasa),
    FoodItem("Fried Rice", 60, R.drawable.veg_fried_rice),
    FoodItem("Full Meals", 80, R.drawable.full_meals),
    FoodItem("Parotta Bhaji", 50, R.drawable.parotta_bhaji),
    FoodItem("Set Dosa", 40, R.drawable.set_dosa),
    FoodItem("Butter Dosa", 50, R.drawable.butter_dosa),
    FoodItem("Masala Dosa", 60, R.drawable.masala_dosa),
    FoodItem("Idli Vada", 40, R.drawable.idli_vada),
    FoodItem("French Fries", 50, R.drawable.french_fries)
)
