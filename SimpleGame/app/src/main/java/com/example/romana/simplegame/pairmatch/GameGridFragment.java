package com.example.romana.simplegame.pairmatch;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.romana.simplegame.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Romana on 8/24/2017.
 */

public class GameGridFragment extends Fragment {

    @Bind(R.id.recycler_view)
    RecyclerView gameGrid;

    GameGridAdapter adapter;
    int clickCount = 0;
    String prevID = "";
    int prevPos = -1;

    public static GameGridFragment newInstance(){
        return new GameGridFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState){
        super.onCreateView(inflater, container, savedState);

        View v = inflater.inflate(R.layout.fragment_game_grid, container, false);

        ButterKnife.bind(this,v);

        ArrayList<pairmodel> listArray = new ArrayList<>();

        listArray.add(new pairmodel(1, R.drawable.newton, true));
        listArray.add(new pairmodel(2, R.drawable.cactus, true));
        listArray.add(new pairmodel(3, R.drawable.rose, true));
        listArray.add(new pairmodel(4, R.drawable.white, true));
        listArray.add(new pairmodel(5, R.drawable.whitered, true));
        listArray.add(new pairmodel(6, R.drawable.yellow, true));

        gameGrid.setLayoutManager(new GridLayoutManager(getContext(),4));
        final int gridMargin = getResources().getDimensionPixelOffset(R.dimen.margin_5);
        gameGrid.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(gridMargin, gridMargin, gridMargin, gridMargin);
            }
        });

        gameGrid.setPadding(gridMargin, gridMargin, gridMargin, gridMargin);

        adapter = new GameGridAdapter(getContext(),getImageArray(listArray));
        gameGrid.setAdapter(adapter);

        adapter.setClickListener(new GameGridAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, final int position) {



                TextView txtID = (TextView) view.findViewById(R.id.image_id);

                clickCount++;
                if (clickCount>1){
                    clickCount=0;

                    if ( prevID.equals(txtID.getText().toString())){
                        adapter.setClickedChildPosition(prevPos,position);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.gridMatch(prevPos,position);
                            }
                        }, 1000);

                    }else {
                        adapter.resetGrid();
                    }

                }else {
                    prevPos = position;
                    prevID = txtID.getText().toString();
                    adapter.setClickedChildPosition(-1,position);
                }
            }
        });

        return v;
    }

    private  ArrayList<pairmodel> getImageArray( ArrayList<pairmodel> listArray){
        ArrayList<pairmodel> newlist = new ArrayList<>();
        for (int i=0; i<2; i++){
            newlist.addAll(listArray);
        }

        Collections.shuffle(newlist);

        return newlist;
    }


}

