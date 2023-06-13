package com.example.dreamjournel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Add extends AppCompatActivity {


    /*
    The ArrayList creates a Array that takes in a data type of strings (notes) and collects them
    within a list that will appear on the app.

    Array Adapter adapts the array we created onto the screen of the phone based on our XML layout.
    If we create a new note it will appear on the app.
     */

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);

            startActivity(intent);

            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ListView dreamlist = (ListView) findViewById(R.id.DreamList);


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes){

            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
                View view = super.getView(position, convertView, parent);


                view.setBackgroundColor(getResources().getColor(
                        android.R.color.white
                ));

                return view;
            }

        };

        dreamlist.setAdapter(arrayAdapter);

        dreamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);

            }
        });

        dreamlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(Add.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

}