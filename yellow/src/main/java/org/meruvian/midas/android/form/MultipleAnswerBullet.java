package org.meruvian.midas.android.form;

import org.meruvian.midas.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class MultipleAnswerBullet extends Activity{
	private Button a;
	private CheckBox c1,c2,c3;
	private StringBuilder hasil = new StringBuilder();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.multiple_answer_bullets);
		c1 = (CheckBox) findViewById(R.id.c1);
		c2 = (CheckBox) findViewById(R.id.c2);
		c3 = (CheckBox) findViewById(R.id.c3);
		a = (Button) findViewById(R.id.a);
		a.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(c1.isChecked()){
					hasil.append("\n"+c1.getText());
				}
				if(c2.isChecked()) {
					hasil.append("\n"+c2.getText());
				}
				if(c3.isChecked()) {
					hasil.append("\n"+c3.getText());
				}
				if(hasil.toString()==""){
					hasil.append("\n");
				}
				Intent intent = new Intent(MultipleAnswerBullet.this,SingleAnswerBullet.class);
				intent.putExtra("no1", hasil.toString().substring(1));
				startActivity(intent);
				hasil = new StringBuilder();
				finish();
			}
		});
	}
}
