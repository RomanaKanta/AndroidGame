package com.example.romana.simplegame.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.romana.simplegame.R;
import com.example.romana.simplegame.tilepuzzle.TilePuzzleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.screen_title)
    TextView title;

    @OnClick(R.id.tic_tac_toe)
    public void playTicTacToe(){
        Intent i = new Intent(MainActivity.this, TicTacToeActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.tile_puzzle)
    public void playTilePuzzle(){
        Intent i = new Intent(MainActivity.this, TilePuzzleActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.pair_matching)
    public void playPairMatching(){
        Intent i = new Intent(MainActivity.this, PairMatchingActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setAppBar();
    }

    private void setAppBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        title.setText("Menu");
    }
}
