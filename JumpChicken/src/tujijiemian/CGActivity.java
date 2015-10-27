package tujijiemian;

import com.example.test.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class CGActivity extends Activity implements OnGestureListener{
	private MediaPlayer mediaPlayer01;
	private ViewFlipper flipper;
	private GestureDetector detector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cg);
		mediaPlayer01 = new MediaPlayer();
		play(R.raw.cg);
		
		detector = new GestureDetector(this);
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper1);

		flipper.addView(addTextView(R.drawable.chicken));
		flipper.addView(addTextView(R.drawable.jcc));
		flipper.addView(addTextView(R.drawable.jc2));
		flipper.addView(addTextView(R.drawable.ten));
	}
    
    private View addTextView(int id) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(id);
		return iv;
	}
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	return this.detector.onTouchEvent(event);
    }
    
    @Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
    
    @Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
			this.flipper.showNext();
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
			this.flipper.showPrevious();
			return true;
		}
		
		return false;
	}
    
    @Override
    public void onLongPress(MotionEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
    		float distanceY) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public void onShowPress(MotionEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
    	// TODO Auto-generated method stub
    	return false;
    }
	
	// ÒôÀÖ
	private void play(int resource) {
		try {
			mediaPlayer01.release();
			mediaPlayer01 = MediaPlayer.create(CGActivity.this, resource);
			mediaPlayer01.start();
			mediaPlayer01.setLooping(true);
		} catch (Exception e) {
			Toast.makeText(CGActivity.this, "·¢Éú´íÎóÁË:" + e.getMessage(),
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
