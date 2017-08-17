/*******************************************************************************
 * Copyright 2013 Marian Schedenig
 *
 * This file is part of n Tile Puzzle.
 *
 * n Tile Puzzle is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * n Tile Puzzle is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * n Tile Puzzle. If not, see http://www.gnu.org/licenses/.
 *******************************************************************************/
package com.example.romana.simplegame.tilepuzzle;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.romana.simplegame.R;
import com.example.romana.simplegame.activity.TicTacToeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TilePuzzleActivity extends AppCompatActivity
{

	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Bind(R.id.screen_title)
	TextView title;

	@Bind(R.id.content)
	RelativeLayout content;

	@OnClick(R.id.tileexit)
	public void exit(){
		finish();
	}

	@OnClick(R.id.tilereset)
	public void reset(){
		setTilePuzzle();
	}

	protected static final int DEFAULT_SIZE = 3;
	
	private PuzzleView view;
	private Puzzle puzzle;
	private int puzzleWidth = 1;
	private int puzzleHeight = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tile_puzzle);
		ButterKnife.bind(this);
		setAppBar();
		setTilePuzzle();

	}

	private void setTilePuzzle(){
		puzzle = new Puzzle();

		view = new PuzzleView(this, puzzle);

		content.addView(view);

		scramble();

		setPuzzleSize(DEFAULT_SIZE, true);

		setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.newton));
	}


	private void scramble()
	{
		puzzle.init(puzzleWidth, puzzleHeight);
		puzzle.scramble();
		view.invalidate();
	}

	private void setBitmap(Bitmap bitmap)
	{
		view.setBitmap(bitmap);
		setPuzzleSize(Math.min(puzzleWidth, puzzleHeight), true);
	}


	private float getImageAspectRatio()
	{
		Bitmap bitmap = view.getBitmap();
		
		if(bitmap == null)
		{
			return 1;
		}
		
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		
		return width / height;
	}

	protected void setPuzzleSize(int size, boolean scramble)
	{
		float ratio = getImageAspectRatio();
		
		if(ratio < 1)
		{
			ratio = 1f /ratio;
		}
		
		int newWidth;
		int newHeight;

			newWidth = size;
			newHeight = (int) (size * ratio); 

		if(scramble || newWidth != puzzleWidth || newHeight != puzzleHeight)
		{
			puzzleWidth = newWidth;
			puzzleHeight = newHeight;
			scramble();
		}
	}
	
public void onFinish(){

	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			TilePuzzleActivity.this);

	// set title
	alertDialogBuilder.setTitle("Puzzle");

	// set dialog message
	alertDialogBuilder
			.setMessage("You Win!")
			.setCancelable(false)
			.setPositiveButton("Okey",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					reset();
					dialog.cancel();
				}
			});

	// create alert dialog
	AlertDialog alertDialog = alertDialogBuilder.create();

	// show it
	alertDialog.show();

}

	public PuzzleView getView()
	{
		return view;
	}


	private void setAppBar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("");
		title.setText("Pair Matching");
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
