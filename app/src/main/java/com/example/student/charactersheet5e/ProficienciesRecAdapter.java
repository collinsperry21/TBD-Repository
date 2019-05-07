package com.example.student.charactersheet5e;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProficienciesRecAdapter extends RecyclerView.Adapter <ProficienciesRecAdapter.ProficienciesViewHolder>{
    private ArrayList<ProficienciesRecItem> proficienciesDefaultList;

    public static class ProficienciesViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ProficienciesViewHolder (View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.defProfImage);
            mTextView1 = itemView.findViewById(R.id.text01);
            mTextView2 = itemView.findViewById(R.id.text02);
        }
    }

    public ProficienciesRecAdapter(ArrayList<ProficienciesRecItem> proficienciesList){
        proficienciesDefaultList = proficienciesList;
    }

    @NonNull
    @Override
    public ProficienciesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.default_proficiencies_card, viewGroup, false);
        ProficienciesViewHolder pVH = new ProficienciesViewHolder(v);
        return pVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ProficienciesViewHolder proficienciesViewHolder, int i) {
        ProficienciesRecItem currentItem = proficienciesDefaultList.get(i);
        //proficienciesViewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        proficienciesViewHolder.mTextView1.setText(currentItem.getmText1());
        proficienciesViewHolder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return proficienciesDefaultList.size();
    }
}
