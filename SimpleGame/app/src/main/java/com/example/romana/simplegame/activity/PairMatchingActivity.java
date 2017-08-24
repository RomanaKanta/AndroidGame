package com.example.romana.simplegame.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.romana.simplegame.R;
import com.example.romana.simplegame.pairmatch.GameGridAdapter;
import com.example.romana.simplegame.pairmatch.GameGridFragment;
import com.example.romana.simplegame.pairmatch.GridViewFragment;
import com.example.romana.simplegame.pairmatch.pairmodel;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PairMatchingActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.screen_title)
    TextView title;

    @OnClick(R.id.pairexit)
    public void exit(){
        finish();
    }

    @OnClick(R.id.pairreset)
    public void reset(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_matching);
        ButterKnife.bind(this);
        setAppBar();
        setGame();

    }

    private void setAppBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        title.setText("Pair Matching");
    }

    private void setGame(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, new GameGridFragment());
        fragmentTransaction.commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
//                overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
