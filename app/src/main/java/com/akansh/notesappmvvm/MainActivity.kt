package com.akansh.notesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akansh.notesappmvvm.databinding.ActivityMainBinding
import com.akansh.notesappmvvm.mvvmArch.Note
import com.akansh.notesappmvvm.mvvmArch.NoteViewModel

class MainActivity : AppCompatActivity(),INotesAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For Data Binding
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        setContentView(R.layout.activity_main)

        val notesList = findViewById<RecyclerView>(R.id.notesList)
        notesList.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        notesList.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        // For Data Binding
        binding.viewModel = viewModel
    }

    override fun onItemDelete(note: Note) {
        viewModel.deleteNote(note)
    }
}