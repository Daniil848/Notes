package com.example.notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Note implements Serializable {

    private long id;
    private String title;
    private String body;

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public Note(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public static List<Note> getAllNotes(Context context) {
        List<Note> notesList = new ArrayList<>();

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("db", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("_id"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String body = cursor.getString(cursor.getColumnIndex("body"));

            Note note = new Note(id, title, body);
            notesList.add(note);
        }

        cursor.close();
        db.close();

        return notesList;
    }

    public void save(Context context) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("body", body);

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (id == 0) {
            id = db.insert("db", null, values);
        } else {
            db.update("db", values, "_id=?", new String[] { Long.toString(id) });
        }
        db.close();
    }

    public void delete(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("db", "_id=?", new String[] { Long.toString(id) });
        db.close();
    }
}