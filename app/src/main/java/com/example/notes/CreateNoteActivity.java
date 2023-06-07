package com.example.notes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText noteTitleEditText, noteBodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        DBHelper dbHelper = new DBHelper(this);

        noteTitleEditText = findViewById(R.id.title_edit_text);
        noteBodyEditText = findViewById(R.id.content_edit_text);
        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.back_button);

        saveButton.setOnClickListener(v -> {
            String title = noteTitleEditText.getText().toString();
            String body = noteBodyEditText.getText().toString();

            if (title.isEmpty()) {
                noteTitleEditText.setError("������� ���������");
                return;
            } else if (body.isEmpty()) {
                noteBodyEditText.setError("������� ����� �������");
                return;
            }

            boolean isInserted = dbHelper.addNote(title, body);

            if (isInserted) {
                Toast.makeText(this, "������� ���������", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "������� �����������", Toast.LENGTH_SHORT).show();
            }

            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}