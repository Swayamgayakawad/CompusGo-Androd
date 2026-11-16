package com.example.compusgo.ui

import com.example.compusgo.R

data class Books(
    val title: String,
    val price: Int,
    val image: Int
)

val campusBooks = listOf(
    Book("Record Book", 65, R.drawable.record_book),
    Book("Observation Book", 18, R.drawable.observation_book),
    Book("Assignment Book", 12, R.drawable.assignment_book),
    Book("Pen", 10, R.drawable.pen),
    Book("Pencil", 5, R.drawable.pencil),
    Book("ID Card Frame", 30, R.drawable.id_card_frame),
    Book("ID Card Tag", 30, R.drawable.id_card_tag)
)
