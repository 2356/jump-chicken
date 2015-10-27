package net.baisoft.jump.entities;

import net.baisoft.jump.cfg.CFG;
import net.baisoft.jump.control.Controller;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Jumper extends GameObject implements IVisible {

	/**
	 * move velocity
	 */
	private float velocity;

	private Controller controller;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void moveVerticality() {

		// just now Y
		float lastY = this.location.y;
		velocity -= CFG.ADD_VELOCITY;
		this.location.y += velocity;

		if (velocity < 0) {// if falling
			for (Footlock footlock : controller.getFootlocks()) {

				// check collide
				if (lastY - CFG.JUMPER_CELL / 2 >= footlock.location.y
						&& this.location.y - CFG.JUMPER_CELL / 2 <= footlock.location.y) {
					if (this.location.x > footlock.location.x
							&& this.location.x <= footlock.location.x
									+ footlock.getLength()) {
						this.velocity = footlock.getForce();
						break;
					}
				}
			}
		}
	}

	public void moveHorizontal(float distance) {
		float xx = this.location.x + distance;
		if (xx < -CFG.SCREEN_WIDTH / 2) {
			return;
		}
		if (xx > CFG.SCREEN_WIDTH / 2) {
			return;
		}
		this.location.x = xx;
	}

	@Override
	public void drawMe(Canvas canvas, Camera camera) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		float cx = camera.getWidth() / 2
				- (camera.location.x - this.location.x);
		float cy = camera.getHeight() / 2
				+ (camera.location.y - this.location.y);
		canvas.drawCircle(cx, cy, CFG.JUMPER_CELL, paint);
	}

}
