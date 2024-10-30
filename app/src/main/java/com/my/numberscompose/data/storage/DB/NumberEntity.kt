package com.my.numberscompose.data.storage.DB

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "history", indices = [Index(value = ["number"], unique = true)])
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val text: String,
    val number: String
)