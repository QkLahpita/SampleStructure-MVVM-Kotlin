package com.example.qklahpita.testmvvmwithapi.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey var title: String
)