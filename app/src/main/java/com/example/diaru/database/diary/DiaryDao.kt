package com.example.diaru.database.diary

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diaryEntity: DiaryEntity)

    @Update
    fun update(diaryEntity: DiaryEntity)

    @Delete
    fun delete(diaryEntity: DiaryEntity)

    @Query("DELETE FROM diary")
    fun deleteAllEntries()

    @Query("SELECT * FROM diary ORDER BY date DESC")
    fun getAllEntries(): LiveData<List<DiaryEntity>>
}