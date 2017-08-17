package com.example.romana.simplegame.HexGrid;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.romana.simplegame.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HexGridActivity extends Activity {

    @Bind(R.id.hexview)
    JHexedPhotoView hexView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.hexgrid_activity);
        ButterKnife.bind(this);

        setScreen(4);
    }

    private void setScreen(int rowNumber){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int rowWidth = (int)width/rowNumber;
        hexView.setHexaGrid(this,HexEngine.ORIENTATION_VERTICAL,rowWidth,rowNumber);

    }
}
