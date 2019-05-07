package com.example.student.charactersheet5e;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeaponRecAdapter extends RecyclerView.Adapter <WeaponRecAdapter.WeaponsViewHolder> {
    private ArrayList<WeaponsRecItem> weaponsList;

    public static class WeaponsViewHolder extends RecyclerView.ViewHolder{
        public TextView weaponName;
        public TextView weaponDamage;
        public TextView weaponWeight;
        public TextView weaponProperties;
        public WeaponsViewHolder (View itemView)
        {
            super(itemView);
            weaponName = itemView.findViewById(R.id.weaponName);
            weaponDamage = itemView.findViewById(R.id.WeaponDamage);
            weaponWeight = itemView.findViewById(R.id.WeaponWeight);
            weaponProperties = itemView.findViewById(R.id.WeaponProperties);
        }
    }
    public WeaponRecAdapter(ArrayList<WeaponsRecItem> newWeaponList){
        weaponsList = newWeaponList;
    }

    @NonNull
    @Override
    public WeaponsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.default_weapons_card, viewGroup, false);
       WeaponsViewHolder wVH = new WeaponsViewHolder(v);
        return wVH;
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponsViewHolder weaponsViewHolder, int i) {
        WeaponsRecItem currentItem = weaponsList.get(i);
        weaponsViewHolder.weaponName.setText(currentItem.getName());
        weaponsViewHolder.weaponDamage.setText(currentItem.getDamage());
        weaponsViewHolder.weaponWeight.setText(currentItem.getWeight());
        weaponsViewHolder.weaponProperties.setText(currentItem.getProperties());
    }

    @Override
    public int getItemCount() {
        return weaponsList.size();
    }
}
