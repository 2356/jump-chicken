package net.baisoft.jump.control;

import java.util.ArrayList;
import java.util.List;

import net.baisoft.jump.GameView;
import net.baisoft.jump.cfg.CFG;
import net.baisoft.jump.entities.Camera;
import net.baisoft.jump.entities.Footlock;
import net.baisoft.jump.entities.FootlockFactory;
import net.baisoft.jump.entities.Jumper;
import net.baisoft.jump.msg.ScoreMassage;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

public class Controller implements Runnable, SensorEventListener{

	private Jumper jumper;
	
	private Camera camera;
	
	private List<Footlock> footlocks;
	
	private GameView gameView;

	private Handler handler;

	private ScoreMassage scoreMassage;

	public Controller() {
		handler = new Handler();
	}
	
	@Override
	public void run() {
		handler.postDelayed(this, CFG.DELAY_TIME);
		jumper.moveVerticality();
		camera.follow(jumper);
		checkOutOfSight();
		if(scoreMassage.getScore() < jumper.getLocation().y){
			scoreMassage.setScore((int) jumper.getLocation().y);
		}
		gameView.redraw();
	}
	
	/**
	 * check footlock out <br>
	 * if footlock out then new footlock
	 */
	private void checkOutOfSight() {
		List<Footlock> outFootlock = new ArrayList<Footlock>();
		
		for (Footlock footlock : footlocks){
			if (footlock.outOfSight(camera)){
				outFootlock.add(footlock);
			}
		}
		
		if (!outFootlock.isEmpty()){
			footlocks.removeAll(outFootlock);
			for(int i = 0; i < outFootlock.size(); i++){
				footlocks.add(FootlockFactory.getFootlock(camera));
			}
		}
	}

	public void newGame(){
		
		//new jumper
		jumper = new Jumper();
		jumper.setController(this);
		jumper.setLocation(new PointF(CFG.INIT_JUMPER_X, CFG.INIT_JUMPER_Y));
		
		//new footlocks
		footlocks = FootlockFactory.getInitFootlocks(CFG.INIT_FOOTLOCK_COUNT);
		
		//new camera
		camera = new Camera();
		camera.setHeight(CFG.SCREEN_HEIGHT);
		camera.setWidth(CFG.SCREEN_WIDTH);
		
		//new scoreMessage
		scoreMassage = new ScoreMassage();
		scoreMassage.setLocation(new PointF(10, 20));
		startGame();
	}
	
	public void startGame() {
		handler.removeCallbacks(this);
		handler.post(this);
	}
		
	public void drawAll(Canvas canvas) {
		jumper.drawMe(canvas, camera);
		for (Footlock footlock : footlocks){
			footlock.drawMe(canvas, camera);
		}
		scoreMassage.drawMe(canvas);
	}
	
	
	
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float x = -arg0.values[SensorManager.DATA_X];
		jumper.moveHorizontal(x);
	}
	
	
	
	
	//*********************** getter and setter ****************
	public Jumper getJumper() {
		return jumper;
	}

	public void setJumper(Jumper jumper) {
		this.jumper = jumper;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public List<Footlock> getFootlocks() {
		return footlocks;
	}

	public void setFootlocks(List<Footlock> footlocks) {
		this.footlocks = footlocks;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	


}
