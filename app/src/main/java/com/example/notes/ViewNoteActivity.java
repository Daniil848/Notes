package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewNoteActivity extends AppCompatActivity {

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        TextView tvNoteTitle = findViewById(R.id.tv_note_title);
        TextView tvNoteBody = findViewById(R.id.tv_note_body);
        Button btnEditNote = findViewById(R.id.btn_edit_note);
        Button btnDeleteNote = findViewById(R.id.btn_delete_note);

        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");

        tvNoteTitle.setText(note.getTitle());
        tvNoteBody.setText(note.getBody());

        btnEditNote.setOnClickListener(v -> {
            Intent intent1 = new Intent(ViewNoteActivity.this, EditNoteActivity.class);
            intent1.putExtra("note", note);
            startActivity(intent1);
        });

        btnDeleteNote.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            note.delete(this);
            finish();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
