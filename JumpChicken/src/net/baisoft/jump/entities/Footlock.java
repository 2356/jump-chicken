package net.baisoft.jump.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Footlock extends GameObject implements IVisible {

	private float length;
	
	private float force;

	private int color;
	
	@Override
	public void drawMe(Canvas canvas, Camera camera) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(color);
		
		float startX = camera.getWidth() / 2 - (camera.location.x - this.location.x);
		float startY = camera.getHeight() / 2 + (camera.location.y - this.location.y);
		float stopX = startX + length;
		float stopY = startY;
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}
	
	public boolean outOfSight(Camera camera){
		if (location.y < camera.location.y - camera.getHeight() / 2){
			return true;
		}
		return false;
	}
	
	public float getForce() {
		return force;
	}

	public void setForce(float force) {
		this.force = force;
	}


	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	
}
