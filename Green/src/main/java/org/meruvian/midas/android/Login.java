package org.meruvian.midas.android;

import org.meruvian.midas.android.util.AppSettings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements Runnable {
	private Button cancelButton, signInButton;
	private ProgressDialog pd;
	private EditText username, password;
	SharedPreferences.Editor editor;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		bundle = getIntent().getExtras();
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(this);
		editor = settings.edit();

		cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Main.class);
				startActivity(intent);
			}
		});
		signInButton = (Button) findViewById(R.id.signInButton);
		signInButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				pd = ProgressDialog.show(Login.this, "Working..", "Sign in..",
						true, false);
				Thread thread = new Thread(Login.this);
				thread.start();
			}
		});
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
			}

	public void run() {
		String isLogin = AppSettings.doLogin(username.getText().toString(),
				password.getText().toString(), Login.this);
		if (isLogin == "true") {
			Looper.prepare();
			pd.dismiss();
			Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG)
					.show();
			boolean isForResult = false;
			try {
				isForResult = bundle.getBoolean("isForResult");
			} catch (Exception e) {

			}
			if (isForResult) {
				moveTaskToBack(false);
			} else {
				startActivity(new Intent(Login.this, Main.class));
			}
			editor.putString("username", username.getText().toString());
			editor.putString("password", password.getText().toString());
			editor.commit();
			finish();
			Looper.loop();
		} else if (isLogin == "invalid") {
			Looper.prepare();
			pd.dismiss();
			Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG)
					.show();
			Looper.loop();
		} else {
			Looper.prepare();
			pd.dismiss();
			Toast.makeText(Login.this, "Connection Timeout", Toast.LENGTH_LONG)
					.show();
			Looper.loop();
		}

		handler.sendEmptyMessage(0);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
		}
	};
}
