package com.example.test;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
	private Button btn_start,btn_exit;
	private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        tv = (TextView) findViewById(R.id.tv);
        btn_start.setOnClickListener(this);
        btn_exit.setOnClickListener(this);       
    }
 
    @Override
    public void onClick(View v){
    	if(v == btn_start){
    		tv.setText("-游戏开始-");
    		Intent intent = new Intent();
			intent.setClass(MainActivity.this, GameActivity.class);
			startActivity(intent);
    	}else if(v == btn_exit){
    		tv.setText("-游戏退出-");
//    		Toast.makeText(MainActivity.this,"退出程序", Toast.LENGTH_SHORT).show();
    		if((System.currentTimeMillis()-exitTime) > 2000){  
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
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

    public void finish(){
    	System.exit(0);
    }
    
    
}
