package tujijiemian;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;

public class MainActivity extends Activity implements OnClickListener {
	private ImageButton btn_start, btn_exit, btn_cg;
	private TextView tv;
	private MediaPlayer mediaPlayer01;
	private MediaPlayer mediaPlayer02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		btn_start = (ImageButton) findViewById(R.id.btn_start);
		btn_cg = (ImageButton) findViewById(R.id.btn_cg);
		btn_exit = (ImageButton) findViewById(R.id.btn_exit);
		tv = (TextView) findViewById(R.id.tv);
		btn_start.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_cg.setOnClickListener(this);
		mediaPlayer01 = new MediaPlayer();
		mediaPlayer02 = new MediaPlayer();
		play(R.raw.main);

		btn_start.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {

				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					play(R.raw.touch);
					btn_start.setImageResource(R.drawable.play2);
				}
				if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					btn_start.setImageResource(R.drawable.play);
				}
				return false;
			}
		});

		btn_cg.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {

				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					play(R.raw.touch);
					btn_cg.setImageResource(R.drawable.cg2);
				}
				if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					btn_cg.setImageResource(R.drawable.cg);
				}
				return false;
			}
		});

		btn_exit.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {

				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					play(R.raw.touch);
					btn_exit.setImageResource(R.drawable.exit2);
				}
				if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					btn_exit.setImageResource(R.drawable.exit);
				}
				return false;
			}
		});
	}

	// 音乐
	private void play(int resource) {
		try {
			mediaPlayer01.release();
			mediaPlayer02.release();
			mediaPlayer01 = MediaPlayer.create(MainActivity.this, resource);
			mediaPlayer01.start();
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, "发生错误了:" + e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void play2(int resource) {
		try {
			mediaPlayer01.release();
			mediaPlayer02.release();
			mediaPlayer02 = MediaPlayer.create(MainActivity.this, resource);
			mediaPlayer02.start();
			mediaPlayer02.setLooping(true);
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, "发生错误了:" + e.getMessage(),
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
		if (mediaPlayer02 != null) {
			mediaPlayer02.release();
			mediaPlayer02 = null;
		}
	}

	//

	@Override
	public void onClick(View v) {
		if (v == btn_start) {
			tv.setText("-游戏选择-");
			Intent intent = new Intent(MainActivity.this, ReadyActivity.class);
			// intent.setClass;
			startActivity(intent);
		} else if (v == btn_cg) {
			tv.setText("-游戏CG-");
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, CGActivity.class);
			startActivity(intent);
		}

		else if (v == btn_exit) {
			tv.setText("-游戏退出-");
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
		}
	}

	private long exitTime = 0;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void finish() {
		System.exit(0);
	}

}
