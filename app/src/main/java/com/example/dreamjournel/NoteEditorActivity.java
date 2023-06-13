package com.example.dreamjournel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText edittext = (EditText) findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        if(noteId != -1){

            edittext.setText(Add.notes.get(noteId));
        } else {
            Add.notes.add("");
            noteId = Add.notes.size() - 1;
            Add.arrayAdapter.notifyDataSetChanged();
        }

        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Add.notes.set(noteId, String.valueOf(charSequence));
                Add.arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
