package com.example.krealearn.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kelas_kreator")
data class KelasKreatorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val kategori: String,
    val deskripsi: String,
    val dibuatPada: Long = System.currentTimeMillis()
)