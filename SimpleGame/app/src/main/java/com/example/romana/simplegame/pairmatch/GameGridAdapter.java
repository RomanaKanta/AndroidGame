package com.example.romana.simplegame.pairmatch;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.romana.simplegame.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Romana on 8/20/2017.
 */

public class GameGridAdapter extends RecyclerView.Adapter<GameGridAdapter.ViewHolder> {

    public ArrayList<pairmodel> items;
    Context context;
    private ItemClickListener clickListener;
    int clickedChildPosition = -1;
    boolean isMatch = false;
    int prevItemPosition = -1;
    int currentItemPosition = -1;


    public GameGridAdapter(Context context, ArrayList<pairmodel> item) {
        this.items = item;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pair_grid_row, parent, false);
        return new ViewHolder(view);
    }

    public ArrayList<pairmodel> getList(){
        return items;
    }

    public void setList(ArrayList<pairmodel> arrayList){
        this.items = arrayList;
        notifyDataSetChanged();
    }

    public void resetGrid(){
        clickedChildPosition = -1;
        notifyDataSetChanged();
    }

    public void setClickedChildPosition(int prevPos, int selectedPos){
        prevItemPosition = prevPos;
        clickedChildPosition = selectedPos;
        notifyDataSetChanged();
    }

    public void gridMatch(int prevPos, int currentPos){
        isMatch = true;
        prevItemPosition = prevPos;
        currentItemPosition = currentPos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final pairmodel item = items.get(position);

        holder.txtID.setText("" + item.getId());
        holder.image.setImageResource(item.getImage());
        if (position == clickedChildPosition || position==prevItemPosition) {
            holder.image.setBackgroundColor(Color.GREEN);
        } else {
            holder.image.setBackgroundColor(Color.BLUE);
        }

        if (item.isVisible){
            holder.itemView.setVisibility(View.VISIBLE);
    }else {
            holder.itemView.setVisibility(View.INVISIBLE);
        }

    if (isMatch){

        if(position==prevItemPosition) {
            item.setVisible(false);
            holder.itemView.setVisibility(View.INVISIBLE);
            prevItemPosition=-1;

        }

        if (position==currentItemPosition){
            item.setVisible(false);
            holder.itemView.setVisibility(View.INVISIBLE);
            currentItemPosition=-1;
        }

        if (prevItemPosition==-1 && currentItemPosition==-1)
        {
            isMatch = false;
        }
    }


    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.image_id)
        TextView txtID;
        @Bind(R.id.gird_image)
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}