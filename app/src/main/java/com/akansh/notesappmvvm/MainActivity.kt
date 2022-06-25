package com.akansh.notesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),INotesAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesList = findViewById<RecyclerView>(R.id.notesList)
        notesList.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        notesList.adapter = adapter

        val addBtn = findViewById<Button>(R.id.button)
        addBtn.setOnClickListener {
            val noteText = findViewById<EditText>(R.id.input).text.toString()
            if(noteText.isNotEmpty()) {
                val note = Note(noteText)
                viewModel.insertNote(note)
            }
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })



    }

    override fun onItemDelete(note: Note) {
        viewModel.deleteNote(note)
    }
}