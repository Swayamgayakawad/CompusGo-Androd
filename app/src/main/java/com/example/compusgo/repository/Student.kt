package com.example.compusgo.repository

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val USN: String,
    val phone: String,
    val sem: Int,
    val name: String,
    val email: String,
    val password: String
)



