package com.example.UI;

import game.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;

public class ReadyActivity extends Activity implements OnClickListener{
	private ImageButton btn_1,btn_2;
	private TextView tv2;
	private MediaPlayer mediaPlayer03;
	private MediaPlayer mediaPlayer04;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     setContentView(R.layout.activity_ready);
	     btn_1 = (ImageButton) findViewById(R.id.btn_1);
	     btn_2 = (ImageButton) findViewById(R.id.btn_2);
	     tv2 = (TextView) findViewById(R.id.tv2);
	     btn_1.setOnClickListener(this);
	     btn_2.setOnClickListener(this); 
	     mediaPlayer03 = new MediaPlayer();
	     mediaPlayer04 = new MediaPlayer();
	     play4(R.raw.ready);
	     
	     btn_1.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View view, MotionEvent motionEvent) {

					if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {					
						play3(R.raw.touch);
						btn_1.setImageResource(R.drawable.gs2);
					}
					if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
						btn_1.setImageResource(R.drawable.gs);
					}
					return false;
				}
			});
	     
	     btn_2.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View view, MotionEvent motionEvent) {

					if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {					
						play3(R.raw.touch);
						btn_2.setImageResource(R.drawable.eg2);
					}
					if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
						btn_2.setImageResource(R.drawable.eg);
					}
					return false;
				}
			});
	}
	
	// 音乐
		private void play3(int resource) {
			try {
				mediaPlayer03.release();
				mediaPlayer04.release();
				mediaPlayer03 = MediaPlayer.create(ReadyActivity.this, resource);
				mediaPlayer03.start();
				
			} catch (Exception e) {
				Toast.makeText(ReadyActivity.this, "发生错误了:" + e.getMessage(),
				Toast.LENGTH_SHORT).show();
			}
		}
		
		private void play4(int resource) {
			try {
				mediaPlayer04.release();
				mediaPlayer03.release();
				mediaPlayer04 = MediaPlayer.create(ReadyActivity.this, resource);
				mediaPlayer04.start();
				mediaPlayer04.setLooping(true);
			} catch (Exception e) {
				Toast.makeText(ReadyActivity.this, "发生错误了:" + e.getMessage(),
				Toast.LENGTH_SHORT).show();
			}
		}
		@Override
		protected void onDestroy() {
			super.onDestroy();
			if (mediaPlayer03 != null) {
				mediaPlayer03.release();
				mediaPlayer03 = null;
			}
			if (mediaPlayer04 != null) {
				mediaPlayer04.release();
				mediaPlayer04 = null;
			}

		}
	
	@Override
    public void onClick(View v){
    	if(v == btn_1){
    		tv2.setText("-游戏开始-");
    		Intent intent = new Intent();
			intent.setClass(ReadyActivity.this, GameActivity.class);
			startActivity(intent);
    	}

    	else if(v == btn_2){
    		tv2.setText("-特典游戏-");
    		Intent intent = new Intent();
			intent.setClass(ReadyActivity.this, ExtraActivity.class);
			startActivity(intent);
		}
    }

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ready, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
