package com.example.UI;

import com.example.test.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class CGActivity extends Activity {
	private MediaPlayer mediaPlayer01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cg);
		mediaPlayer01 = new MediaPlayer();
		play(R.raw.cg);
		
	}
	
	// “Ù¿÷
	private void play(int resource) {
		try {
			mediaPlayer01.release();
			mediaPlayer01 = MediaPlayer.create(CGActivity.this, resource);
			mediaPlayer01.start();
			mediaPlayer01.setLooping(true);
		} catch (Exception e) {
			Toast.makeText(CGActivity.this, "∑¢…˙¥ÌŒÛ¡À:" + e.getMessage(),
			Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer01 != null) {
			mediaPlayer01.release();
			mediaPlayer01 = null;
		}

	}
	//

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cg, menu);
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
