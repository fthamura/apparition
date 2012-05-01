package org.meruvian.midas.android;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.midas.android.adapter.Adapter;
import org.meruvian.midas.android.adapter.MenuAdapter;
import org.meruvian.midas.android.form.MultipleAnswerBullet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
	private ListView list;
	private TextView loginStatus;
	private Menu menu;
	private Dialog dialogHelp;
	private SharedPreferences.Editor editor;
	private SharedPreferences prefs;

	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		loginStatus = (TextView) findViewById(R.id.loginStatus);
		if (!prefs.getString("userKey", "").equals("")) {
			loginStatus.setText("Login as: " + prefs.getString("username", ""));
		} else {
			loginStatus.setText("Not logged in");
		}
		dialogHelp = new Dialog(this);
		dialogHelp.setTitle("Help");
		dialogHelp.setContentView(R.layout.help);
		list = (ListView) findViewById(R.id.ListViewMain);
		List<Adapter> listOfPhonebook = new ArrayList<Adapter>();
		listOfPhonebook.add(new Adapter("Start Test",
				R.drawable.aqua_ic_menu_forward));
		MenuAdapter adapter = new MenuAdapter(this, listOfPhonebook);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivity(new Intent(Main.this, MultipleAnswerBullet.class));
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		this.menu = menu;
		addRegularMenuItems(menu);
		return true;
	}

	private void addRegularMenuItems(Menu menu) {
		int base = Menu.FIRST; // value is 1
		MenuItem addChild;
		if (!prefs.getString("userKey", "").equals("")) {
			addChild = menu.add(base, base, base, "Logout");
		} else {
			addChild = menu.add(base, base, base, "Login");
		}
		addChild = menu.add(base + 1, base + 1, base + 1, "Settings");
		addChild = menu.add(base + 2, base + 2, base + 2, "About Us");
		addChild = menu.add(base + 3, base + 3, base + 3, "Help");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			if (!prefs.getString("userKey", "").equals("")) {
				if (item.getItemId() == 1) {
					editor = prefs.edit();
					editor.putString("userKey", "");
					editor.commit();
					loginStatus.setText("Not loged in");
					startActivity(new Intent(Main.this, Login.class));
					finish();
				}
			} else {
				startActivity(new Intent(Main.this, Login.class));
				finish();
			}
		} else if (item.getItemId() == 2) {
			startActivity(new Intent(Main.this, Preference.class));
		} else if (item.getItemId() == 3) {
			startActivity(new Intent(Main.this, AboutUs.class));
		} else {
			dialogHelp.show();
		}
		return true;
	}
}