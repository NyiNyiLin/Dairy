package com.nyi.personal.dairy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.nyi.personal.database.DatabaseHandler;

/**
 * Created by IN-3442 on 02-Nov-15.
 */
public class Note extends AppCompatActivity {
    String gettitle;
    DatabaseHandler db;
    EditText edit_title,edit_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);

        db=new DatabaseHandler(this);

        edit_title= (EditText) findViewById(R.id.edit_title);
        edit_text=(EditText) findViewById(R.id.edit_text);

        Bundle extras = getIntent().getExtras();
        gettitle=extras.getString("Title");
        String text=db.getNoteTextByTitle(gettitle);

        edit_title.setText(gettitle);
        edit_text.setText(text);



    }
}
