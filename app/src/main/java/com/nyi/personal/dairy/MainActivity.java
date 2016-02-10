package com.nyi.personal.dairy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nyi.personal.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHandler(this);


        CardView cardView=(CardView) findViewById(R.id.card_view);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, edit.class);
                startActivity(in);
            }
        });

        //FloatinActionButton
        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OpenInputDialogBox();
            }
        });

        //This is for card view
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardViewAdapter(db.getAllNoteTitle());
        //mAdapter = new CardViewAdapter(getDataSet());

        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    public void onResume() {
        super.onResume();
        /*((CardViewAdapter) mAdapter).setOnItemClickListener(new CardViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Click", " Clicked on Item " + position);
                String title = ((CardViewAdapter) mAdapter).getNoteTitleList().get(position);
                Intent in = new Intent(MainActivity.this, Note.class);
                in.putExtra("Title",title);
                startActivity(in);
            }
        });*/




    }
}
