package net.baisoft.jump.msg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;


public class ScoreMassage {

	/**
	 * Î»ÖÃ
	 */
	private PointF location;
	
	/**
	 * score
	 */
	private int score = 0;
	
	/**
	 * »­×Ô¼º
	 * 
	 * @param canvas
	 */
	public void drawMe(Canvas canvas){
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.YELLOW);
		
		canvas.drawText("" + score, location.x, location.y, paint);
	}

	public PointF getLocation() {
		return location;
	}

	public void setLocation(PointF location) {
		this.location = location;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	
	
}
