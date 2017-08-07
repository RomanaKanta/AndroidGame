package com.example.romana.simplegame.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.romana.simplegame.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicTacToeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.screen_title)
    TextView title;

    @Bind(R.id.playerx_count)
    TextView playerX;

    @Bind(R.id.playero_count)
    TextView playerO;

    @Bind(R.id.button1)
    Button button1;

    @Bind(R.id.button2)
    Button button2;

    @Bind(R.id.button3)
    Button button3;

    @Bind(R.id.button4)
    Button button4;

    @Bind(R.id.button5)
    Button button5;

    @Bind(R.id.button6)
    Button button6;

    @Bind(R.id.button7)
    Button button7;

    @Bind(R.id.button8)
    Button button8;

    @Bind(R.id.button9)
    Button button9;

    private String start_game = "X";
    private int xCount = 0;
    private int oCount = 0;


    @OnClick(R.id.button1)
    public void setButton1(){
        buttonClick(button1);
    }

    @OnClick(R.id.button2)
    public void setButton2(){
        buttonClick(button2);
    }

    @OnClick(R.id.button3)
    public void setButton3(){
        buttonClick(button3);
    }

    @OnClick(R.id.button4)
    public void setButton4(){
        buttonClick(button4);
    }

    @OnClick(R.id.button5)
    public void setButton5(){
        buttonClick(button5);
    }

    @OnClick(R.id.button6)
    public void setButton6(){
        buttonClick(button6);
    }

    @OnClick(R.id.button7)
    public void setButton7(){
        buttonClick(button7);
    }

    @OnClick(R.id.button8)
    public void setButton8(){
        buttonClick(button8);
    }

    @OnClick(R.id.button9)
    public void setButton9(){
        buttonClick(button9);
    }

    @OnClick(R.id.buttonexit)
    public void exit(){
        finish();
    }

    @OnClick(R.id.buttonreset)
    public void reset(){
        resetButton(button1);
        resetButton(button2);
        resetButton(button3);
        resetButton(button4);
        resetButton(button5);
        resetButton(button6);
        resetButton(button7);
        resetButton(button8);
        resetButton(button9);

    }

    private void resetButton(Button btn){
        btn.setText("");
        btn.setBackgroundResource(R.drawable.tile_bg);
    }

    private void buttonClick(Button btn){

        if (btn.getText().equals("")) {
            btn.setText(start_game);

        if (start_game.equals("X")){
            btn.setTextColor(Color.GREEN);
            start_game = "O";
        }else {
            btn.setTextColor(Color.BLUE);
            start_game = "X";
        }
            winningGame();

        }

    }


    private void winningGame(){

        // ===
        setGameLogic(button1, button2, button3, "X");
        setGameLogic(button4, button5, button6, "X");
        setGameLogic(button7, button8, button9, "X");
        setGameLogic(button1, button2, button3, "O");
        setGameLogic(button4, button5, button6, "O");
        setGameLogic(button7, button8, button9, "O");

        // |||
        setGameLogic(button1, button4, button7, "X");
        setGameLogic(button2, button5, button8, "X");
        setGameLogic(button3, button6, button9, "X");
        setGameLogic(button1, button4, button7, "O");
        setGameLogic(button2, button5, button8, "O");
        setGameLogic(button3, button6, button9, "O");

        // /\
        setGameLogic(button1, button5, button9, "X");
        setGameLogic(button3, button5, button7, "X");
        setGameLogic(button1, button5, button9, "O");
        setGameLogic(button3, button5, button7, "O");

    }


    private void setGameLogic(Button btn1,Button btn2, Button btn3, String player)
    {

        if(btn1.getText().equals(player) && btn2.getText().equals(player) && btn3.getText().equals(player)){

            btn1.setBackgroundColor(Color.GRAY);
            btn2.setBackgroundColor(Color.GRAY);
            btn3.setBackgroundColor(Color.GRAY);

            if(player.equalsIgnoreCase("X")){
                xCount++;
            }else{
                oCount++;
            }

            showWinningAlart(player);

        }
    }

    private void gameScore(){
        playerX.setText(String.valueOf(xCount));
        playerO.setText(String.valueOf(oCount));
    }

    private void showWinningAlart(String player){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                TicTacToeActivity.this);

        // set title
        alertDialogBuilder.setTitle("Tic Tac Toe");

        // set dialog message
        alertDialogBuilder
                .setMessage("Player " + player + " Win!")
                .setCancelable(false)
                .setNeutralButton("Okey",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        gameScore();
                        reset();
                        dialog.cancel();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        ButterKnife.bind(this);
        setAppBar();
        reset();
    }

    private void setAppBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        title.setText("Tic Tac Toe");
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
