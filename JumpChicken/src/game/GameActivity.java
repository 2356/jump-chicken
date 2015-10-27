package game;


import tujijiemian.CGActivity;

import com.example.test.R;

import net.baisoft.jump.GameView;
import net.baisoft.jump.control.Controller;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameActivity extends Activity {	
		private Controller controller;
		private MediaPlayer mediaPlayer01;
		private GameView gameView;
		private SensorManager sensorManager = null;
		private Sensor sensor = null;
		//private PowerManager.WakeLock mWakeLock;
	    
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //mWakeLock = new WakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GameActivity");
	        //PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);  
	       // mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GameActivity");
	        controller = new Controller();
	        gameView = new GameView(this);
	        controller.setGameView(gameView);
	        gameView.setController(controller);
			sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(controller, sensor,
					SensorManager.SENSOR_DELAY_GAME);
	        controller.newGame();
	        initActivity();
	        
	        mediaPlayer01 = new MediaPlayer();
			play(R.raw.game);
	        
	        setContentView(gameView);
	    }
	    
		// 音乐
		private void play(int resource) {
			try {
				mediaPlayer01.release();
				mediaPlayer01 = MediaPlayer.create(GameActivity.this, resource);
				mediaPlayer01.start();
				mediaPlayer01.setLooping(true);
			} catch (Exception e) {
				Toast.makeText(GameActivity.this, "发生错误了:" + e.getMessage(),
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
		
	    private void initActivity() {
			// 设置无标题
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			// 设置全屏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    }
	    
//	    @Override
//		protected void onPause() {
//			mWakeLock.release();
//			super.onPause();
//		}
//
//		@Override
//		protected void onResume() {
//			mWakeLock.acquire();
//			super.onResume();
//		}
	}
	


