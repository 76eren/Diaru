package com.example.diaru.database.diary

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "diary")
data class DiaryEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var content: String,
    var date: Long, // Store date as seconds since the Unix epoch
    var feeling: Int
)
