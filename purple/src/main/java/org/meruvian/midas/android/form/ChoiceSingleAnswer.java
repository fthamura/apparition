package org.meruvian.midas.android.form;

import org.meruvian.midas.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;

public class ChoiceSingleAnswer extends Activity {
	private Button c;
	private Spinner spinner;
	private String[] spinnerval;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choice_answer_dropdown);
		final Bundle bundle = getIntent().getExtras();
		spinnerval = getResources().getStringArray(R.array.listArray);
		spinner = (Spinner) findViewById(R.id.spinner1);
		c = (Button) findViewById(R.id.c);
		c.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(ChoiceSingleAnswer.this,
						NameAddress.class);
				intent.putExtra("no1", bundle.getString("no1"));
				intent.putExtra("no2", bundle.getString("no2"));
				intent.putExtra("no3", spinnerval[spinner.getSelectedItemPosition()]);
				startActivity(intent);
				finish();
			}
		});
	}
}
