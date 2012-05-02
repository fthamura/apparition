package org.meruvian.midas.android.form;

import org.meruvian.midas.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class OpenEnded extends Activity {
	private Button h;
	private EditText comt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.open_ended);
		final Bundle bundle = getIntent().getExtras();
		comt = (EditText) findViewById(R.id.comment);
		h = (Button) findViewById(R.id.h);
		h.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(OpenEnded.this, Summary.class);
				intent.putExtra("no1", bundle.getString("no1"));
				intent.putExtra("no2", bundle.getString("no2"));
				intent.putExtra("no3", bundle.getString("no3"));
				intent.putExtra("name", bundle.getString("name"));
				intent.putExtra("email", bundle.getString("email"));
				intent.putExtra("company", bundle.getString("company"));
				intent.putExtra("country", bundle.getString("country"));
				intent.putExtra("city", bundle.getString("city"));
				intent.putExtra("state", bundle.getString("state"));
				intent.putExtra("zip", bundle.getString("zip"));
				intent.putExtra("no5", comt.getText().toString());
				startActivity(intent);
				finish();
			}
		});
	}
}
