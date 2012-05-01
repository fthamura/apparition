package org.meruvian.midas.android.form;

import org.meruvian.midas.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

public class SingleAnswerBullet extends Activity{
	private Button b;
	private RadioButton r1,r3,r2;
	private String hasil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.single_answer_bullets);
		final Bundle bundle = getIntent().getExtras();
		r1 = (RadioButton) findViewById(R.id.r1);
		r2 = (RadioButton) findViewById(R.id.r2);
		r3 = (RadioButton) findViewById(R.id.r3);
		b = (Button) findViewById(R.id.b);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(r1.isChecked()){
					hasil = r1.getText().toString();
				}else if(r2.isChecked()){
					hasil = r2.getText().toString();
				}else if(r3.isChecked()){
					hasil = r3.getText().toString();
				}
				Intent intent = new Intent(SingleAnswerBullet.this,ChoiceSingleAnswer.class);
				intent.putExtra("no1", bundle.getString("no1"));
				intent.putExtra("no2", hasil);
				startActivity(intent);
				finish();
			}
		});
	}
}
