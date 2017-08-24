package com.example.romana.simplegame.pairmatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.romana.simplegame.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Romana on 8/24/2017.
 */

public class GridViewFragment  extends Fragment {

    private GridViewAdapter mGridViewAdapter;
    private GridView mGridView;

    public static GridViewFragment newInstance(){
        return new GridViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState){
        super.onCreateView(inflater, container, savedState);

        View v = inflater.inflate(R.layout.fragment_gridview, container, false);

        mGridView = (GridView)v.findViewById(R.id.fragment_gridview_gridview);

            setGridItem();


        return v;
    }

    public void setGridItem() {
        ArrayList<GridViewItem> mList = new ArrayList<>();

        mList.add(new GridViewItem("0", R.drawable.newton));
        mList.add(new GridViewItem("1", R.drawable.cactus));
        mList.add(new GridViewItem("2", R.drawable.rose));
        mList.add(new GridViewItem("3", R.drawable.white));
        mList.add(new GridViewItem("4", R.drawable.whitered));
        mList.add(new GridViewItem("5", R.drawable.yellow));

        mGridView.setNumColumns(3);

        mGridViewAdapter = new GridViewAdapter(getActivity(), getImageArray(mList));

        mGridView.setAdapter(mGridViewAdapter);
    }



    private  ArrayList<GridViewItem> getImageArray( ArrayList<GridViewItem> listArray){
        ArrayList<GridViewItem> newlist = new ArrayList<>();
        for (int i=0; i<2; i++){
            newlist.addAll(listArray);
        }

        Collections.shuffle(newlist);

        return newlist;
    }

}

