package com.example.student.charactersheet5e;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoadAdapter extends RecyclerView.Adapter<LoadAdapter.CharacterViewHolder> {
    private ArrayList<CharacterCardView> mCharacetrList;

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{
        public ImageView mPortraitImageView;
        public TextView mNameTextView;
        public TextView mRaceTextView;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            mPortraitImageView = itemView.findViewById(R.id.character_portrait);
            mNameTextView = itemView.findViewById(R.id.character_name);
            mRaceTextView = itemView.findViewById(R.id.character_race);
        }
    }

    public LoadAdapter(ArrayList<CharacterCardView> characterList)
    {
        mCharacetrList = characterList;

    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        CharacterViewHolder cvh = new CharacterViewHolder(v);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        CharacterCardView currentItem = mCharacetrList.get(i);

        characterViewHolder.mPortraitImageView.setImageResource(currentItem.getPortrait());
        characterViewHolder.mNameTextView.setText(currentItem.getName());
        characterViewHolder.mRaceTextView.setText(currentItem.getRace());

    }

    @Override
    public int getItemCount() {
        return mCharacetrList.size();
    }
}
