package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal (application: Application) :AndroidViewModel(application) {


    val allNotes : LiveData<List<NoteEntity>>
    val repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote (noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(noteEntity)
    }

    fun addNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(noteEntity)
    }
}
