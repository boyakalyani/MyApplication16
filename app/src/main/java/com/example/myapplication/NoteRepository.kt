package com.example.myapplication


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.concurrent.Flow

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<NoteEntity>> = notesDao.getAllNotes()

    suspend fun insert(noteEntity: NoteEntity) {
        notesDao.insert(noteEntity)
    }


    suspend fun delete(noteEntity: NoteEntity){
        notesDao.delete(noteEntity)
    }


    suspend fun update(noteEntity: NoteEntity){
        notesDao.update(noteEntity)
    }
}
