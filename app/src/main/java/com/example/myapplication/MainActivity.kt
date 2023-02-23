package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)

        notesRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(noteEntity: NoteEntity) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", noteEntity.noteTitle)
        intent.putExtra("noteDescription", noteEntity.noteDescription)
        intent.putExtra("noteId", noteEntity.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(noteEntity: NoteEntity) {
        viewModal.deleteNote(noteEntity)
        Toast.makeText(this, "${noteEntity.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}
