package net.baisoft.jump.entities;

import net.baisoft.jump.cfg.CFG;
import android.graphics.PointF;

public class Camera extends GameObject {

	
	private float width;
	private float height;
	
	public Camera() {
		this.location = new PointF(0, 0);
	}
	public void follow(Jumper jumper){
		//this.location.x = jumper.location.x;
		if (this.location.y < jumper.location.y){
			this.location.y = jumper.location.y;
		}else if (this.location.y - jumper.location.y > CFG.IN_CAMERA_MAX_FALL){
			this.location.y = jumper.location.y + CFG.IN_CAMERA_MAX_FALL;
		}
	}
	
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	
}
