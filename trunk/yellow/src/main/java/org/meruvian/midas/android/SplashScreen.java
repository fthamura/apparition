package org.meruvian.midas.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;

public class SplashScreen extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;
	private SharedPreferences prefs;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = null;
				if (!prefs.getString("userKey", "").equals("")) {
					mainIntent = new Intent(SplashScreen.this, Main.class);
				} else {
					mainIntent = new Intent(SplashScreen.this, Login.class);
				}
				SplashScreen.this.startActivity(mainIntent);
				SplashScreen.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}