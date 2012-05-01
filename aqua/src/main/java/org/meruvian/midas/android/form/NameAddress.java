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

public class NameAddress extends Activity {
	private Button f;
	private EditText nm, em, com, cou, ci, st, zi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.name_adress_general);
		final Bundle bundle = getIntent().getExtras();

		nm = (EditText) findViewById(R.id.name);
		em = (EditText) findViewById(R.id.email);
		com = (EditText) findViewById(R.id.company);
		cou = (EditText) findViewById(R.id.country);
		ci = (EditText) findViewById(R.id.city);
		st = (EditText) findViewById(R.id.state);
		zi = (EditText) findViewById(R.id.zip);

		f = (Button) findViewById(R.id.f);
		f.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(NameAddress.this, OpenEnded.class);
				intent.putExtra("no1", bundle.getString("no1"));
				intent.putExtra("no2", bundle.getString("no2"));
				intent.putExtra("no3", bundle.getString("no3"));
				intent.putExtra("name", nm.getText().toString());
				intent.putExtra("email", em.getText().toString());
				intent.putExtra("company", com.getText().toString());
				intent.putExtra("country", cou.getText().toString());
				intent.putExtra("city", ci.getText().toString());
				intent.putExtra("state", st.getText().toString());
				intent.putExtra("zip", zi.getText().toString());
				startActivity(intent);
				finish();
			}
		});
	}
}
