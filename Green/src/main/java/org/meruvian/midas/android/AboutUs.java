package org.meruvian.midas.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class AboutUs extends Activity {
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		button = (Button) findViewById(R.id.license);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Eula.show(AboutUs.this);
			}
		});
	}
}
