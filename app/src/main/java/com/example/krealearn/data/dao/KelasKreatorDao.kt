package com.example.krealearn.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.krealearn.data.entity.KelasKreatorEntity

@Dao
interface KelasKreatorDao {
    @Query("SELECT * FROM kelas_kreator ORDER BY dibuatPada DESC")
    suspend fun getAll(): List<KelasKreatorEntity>

    @Insert
    suspend fun insert(kelas: KelasKreatorEntity)

    @Delete
    suspend fun delete(kelas: KelasKreatorEntity)
}