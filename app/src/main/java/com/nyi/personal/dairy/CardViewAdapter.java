package com.nyi.personal.dairy;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IN-3442 on 12-Oct-15.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.KeyHolder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static ArrayList<String> noteTitleList;
    private static MyClickListener myClickListener;

    @Override
    public KeyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        KeyHolder keyHolder = new KeyHolder(view);
        return keyHolder;
    }

    @Override
    public void onBindViewHolder(KeyHolder holder, int position) {
        holder.character.setText(noteTitleList.get(position));
    }

    @Override
    public int getItemCount() {

        return noteTitleList.size();
    }
    /*
    public void addKey(CustomKey dataObj, int index) {
        keyArrayList.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteKey(int index) {
        keyArrayList.remove(index);
        notifyItemRemoved(index);
    }*/
    public void setOnItemClickListener(MyClickListener myClickListener) {
        CardViewAdapter.myClickListener = myClickListener;
    }

    public CardViewAdapter(ArrayList<String> noteTitleList) {
        CardViewAdapter.noteTitleList =noteTitleList;
    }

    public static class KeyHolder extends RecyclerView.ViewHolder
            implements OnClickListener{

        TextView character,text;
        ImageButton delete;

        public KeyHolder(View itemView) {
            super(itemView);

            character = (TextView) itemView.findViewById(R.id.textview_character_in_cardview);
            text = (TextView) itemView.findViewById(R.id.textview_text_in_cardview);

            Log.i(LOG_TAG, "Adding Listener aaa");
            //itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static ArrayList<String> getNoteTitleList() {
        return noteTitleList;
    }
}
