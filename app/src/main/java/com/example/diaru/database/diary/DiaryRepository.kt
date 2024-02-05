package com.example.diaru.database.diary

import android.app.Application
import androidx.lifecycle.LiveData

class DiaryRepository(application: Application) {
    private var diaryDao: DiaryDao
    private var allEntries: LiveData<List<DiaryEntity>>

    init {
        val database: DiaryDatabase = DiaryDatabase.getDatabase(application)
        diaryDao = database.diaryDao()
        allEntries = diaryDao.getAllEntries()
    }

    fun insert(diaryEntity: DiaryEntity) {
        Thread { diaryDao.insert(diaryEntity) }.start()
    }

    fun update(diaryEntity: DiaryEntity) {
        Thread { diaryDao.update(diaryEntity) }.start()
    }

    fun delete(diaryEntity: DiaryEntity) {
        Thread { diaryDao.delete(diaryEntity) }.start()
    }

    fun deleteAllEntries() {
        Thread { diaryDao.deleteAllEntries() }.start()
    }

    fun getAllEntries(): LiveData<List<DiaryEntity>> = allEntries
}
