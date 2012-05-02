package org.meruvian.midas.android;

import org.meruvian.midas.android.util.AppSettings;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Registration extends Activity {
	private WebView webview;
	private Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registration);
		bundle = getIntent().getExtras();
		webview = (WebView) findViewById(R.id.webview2);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(AppSettings.SERVER_HOSTNAME+"backend/user/log_in/json");
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onCloseWindow(WebView window) {
				moveTaskToBack(false);
				finish();
				super.onCloseWindow(window);
			}
		});
	}
}
