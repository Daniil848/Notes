package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notesList;
    private NotesListAdapter notesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateNote = findViewById(R.id.btn_create_note);
        ListView lvNotesList = findViewById(R.id.lv_notes_list);

        notesList = new ArrayList<>();
        notesListAdapter = new NotesListAdapter(this, R.layout.note_list_item, notesList);
        lvNotesList.setAdapter(notesListAdapter);

        lvNotesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
            intent.putExtra("note", notesList.get(position));
            intent.putExtra("noteId", notesListAdapter.getItem(position).getId());
            intent.putExtra("noteTitle", notesListAdapter.getItem(position).getTitle());
            intent.putExtra("noteBody", notesListAdapter.getItem(position).getBody());
            startActivity(intent);
        });

        btnCreateNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        notesList.clear();
        notesList.addAll(Note.getAllNotes(this));
        notesListAdapter.notifyDataSetChanged();
    }
}