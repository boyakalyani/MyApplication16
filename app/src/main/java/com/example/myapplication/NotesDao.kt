package com.example.myapplication



import androidx.lifecycle.LiveData
import androidx.room.*

// annotation for dao class.
@Dao
interface NotesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)


    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Update
    suspend fun update(noteEntity: NoteEntity)

}

