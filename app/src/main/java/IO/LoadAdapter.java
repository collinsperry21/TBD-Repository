package IO;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.student.charactersheet5e.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import AppModels.CharacterCardView;

public class LoadAdapter extends RecyclerView.Adapter<LoadAdapter.CharacterViewHolder> {
    private ArrayList<CharacterCardView> mCharacetrList;
    private OnItemClickListener myListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        myListener = listener;

    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{
        public ImageView mPortraitImageView;
        public TextView mNameTextView;
        public TextView mRaceTextView;
        public TextView mClassTextView;
        public TextView mLvlTextView;



        public CharacterViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mPortraitImageView = itemView.findViewById(R.id.character_portrait);
            mNameTextView = itemView.findViewById(R.id.character_name);
            mRaceTextView = itemView.findViewById(R.id.character_race);
            mClassTextView = itemView.findViewById(R.id.character_class);
            mLvlTextView = itemView.findViewById(R.id.character_level);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
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
        CharacterViewHolder cvh = new CharacterViewHolder(v, myListener);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        CharacterCardView currentItem = mCharacetrList.get(i);

        characterViewHolder.mPortraitImageView.setImageResource(currentItem.getPortrait());
        characterViewHolder.mNameTextView.setText(currentItem.getName());
        characterViewHolder.mRaceTextView.setText(currentItem.getRace());
        characterViewHolder.mClassTextView.setText(currentItem.getmClass());
        characterViewHolder.mLvlTextView.setText(currentItem.getmLvl());

    }

    @Override
    public int getItemCount() {
        return mCharacetrList.size();
    }

}
