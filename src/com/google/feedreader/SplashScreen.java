package com.google.feedreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.cloud.backend.android.R;

public class SplashScreen extends Activity{

	protected void onCreate(Bundle bundleInstance){
	super.onCreate(bundleInstance);
	setContentView(R.layout.splashscreen);
        Thread timer = new Thread(){
			public void run(){
			try{
				sleep(1000);
			} catch(InterruptedException e){
				e.printStackTrace();
			} finally {
				startActivity(new Intent(
	                      SplashScreen.this,MenuActivity.class));
				finish();
	    	  }
			}
			
		};
		timer.start();
		
	}
}
