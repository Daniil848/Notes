package com.example.notes;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class EditNoteActivity extends AppCompatActivity {

    private EditText etNoteTitle;
    private EditText etNoteBody;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etNoteTitle = findViewById(R.id.et_note_title);
        etNoteBody = findViewById(R.id.et_note_body);
        Button btnSaveNote = findViewById(R.id.btn_save_note);
        Button btnCancelEditing = findViewById(R.id.btn_cancel_editing);
        Button btnDeleteNote = findViewById(R.id.btn_delete_note);

        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");

        etNoteTitle.setText(note.getTitle());
        etNoteBody.setText(note.getBody());

        btnSaveNote.setOnClickListener(v -> saveNote());

        btnCancelEditing.setOnClickListener(v -> finish());

        btnDeleteNote.setOnClickListener(v -> showDeleteConfirmationDialog());
}

    private void saveNote() {
        String title = etNoteTitle.getText().toString().trim();
        String body = etNoteBody.getText().toString().trim();

        if (title.isEmpty()) {
            etNoteTitle.setError("Введите заголовок");
            return;
        } else if (body.isEmpty()) {
            etNoteBody.setError("Введите текст заметки");
            return;
        }

        note.setTitle(title);
        note.setBody(body);
        note.save(this);

        Toast.makeText(this, "Заметка сохранена", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Вы действительно хотите удалить заметку?");
        builder.setPositiveButton("Удалить", (dialog, which) -> {
            note.delete(this);
            finish();
        });
        builder.setNegativeButton("Закрыть", null);
        builder.show();
    }
}