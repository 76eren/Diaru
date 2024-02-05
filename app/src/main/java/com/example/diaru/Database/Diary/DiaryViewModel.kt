package com.example.diaru.database.diary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.diaru.Database.Diary.DiaryEntity

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DiaryRepository = DiaryRepository(application)
    val allEntries: LiveData<List<DiaryEntity>>

    init {
        allEntries = repository.getAllEntries()
    }

    fun insert(diaryEntity: DiaryEntity) = repository.insert(diaryEntity)
    fun update(diaryEntity: DiaryEntity) = repository.update(diaryEntity)
    fun delete(diaryEntity: DiaryEntity) = repository.delete(diaryEntity)
    fun deleteAllEntries() = repository.deleteAllEntries()
}
