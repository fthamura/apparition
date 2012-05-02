package org.meruvian.midas.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Preference extends PreferenceActivity {
	private Menu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		android.preference.Preference clearPref = (android.preference.Preference) findPreference("clearPref");
		clearPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(
					android.preference.Preference preference) {
				new AlertDialog.Builder(Preference.this)
						.setTitle("Clear Cookies Data")
						.setMessage(
								"Do you really want to clear application cookies data?")
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setPositiveButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Preference.this.deleteDatabase("webview.db");
									    Preference.this.deleteDatabase("webviewCache.db");
									    //SessionStore.clear(Preference.this);
									}
								}).setNegativeButton(android.R.string.no, null)
						.show();
				return false;
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
		MenuItem addChild = menu.add(base, base, base, "Sign Up");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			Intent prefIntent = new Intent(Preference.this, Registration.class);
			startActivity(prefIntent);
		}
		return true;
	}
}
