package net.baisoft.jump.entities;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.PointF;

import net.baisoft.jump.cfg.CFG;

public class FootlockFactory {

	public static Footlock getFootlock(Camera camera){
		Footlock footlock = new Footlock();
		if (Math.random() < 0.9){
			footlock.setColor(Color.BLUE);
			footlock.setForce(CFG.FOOTLOCK_FORCE);
		} else {
			footlock.setColor(Color.RED);
			footlock.setForce(CFG.FOOTLOCK_FORCE * CFG.FOOTLOCK_FORCE_TIMES);
		}
		footlock.setLength(CFG.FOOTLOCK_LENGTH);
		int tmpX = (int) (Math.random() * (CFG.SCREEN_WIDTH - CFG.FOOTLOCK_LENGTH) - CFG.SCREEN_WIDTH / 2);
		footlock.setLocation(new PointF(tmpX, camera.location.y + camera.getHeight() / 2 + (int)(Math.random() * 5)));
		return footlock;
	}
	
	public static List<Footlock> getInitFootlocks(int count){
		ArrayList<Footlock> footlocks = new ArrayList<Footlock>();
		
		for (int i = 0; i < CFG.INIT_FOOTLOCK_COUNT; i++){
			Footlock footlock = new Footlock();
			footlock.setForce(CFG.FOOTLOCK_FORCE);
			footlock.setLength(CFG.SCREEN_WIDTH);
			footlock.setColor(Color.GREEN);
			footlock.setLocation(new PointF(-CFG.SCREEN_WIDTH / 2 , i * CFG.SCREEN_HEIGHT / CFG.INIT_FOOTLOCK_COUNT));
			footlocks.add(footlock);
		}
		return footlocks;
	}
}
