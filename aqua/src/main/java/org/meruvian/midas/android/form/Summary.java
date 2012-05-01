package org.meruvian.midas.android.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.meruvian.midas.android.Main;
import org.meruvian.midas.android.R;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class Summary extends ExpandableListActivity {
	private TextView no1, no2, no3, no5;
	static final String colors[] = { "4. Name Address (General)" };
	private Button back;
	private String shades[][];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.summary);
		final Bundle bundle = getIntent().getExtras();
		no1 = (TextView) findViewById(R.id.no1);
		no2 = (TextView) findViewById(R.id.no2);
		no3 = (TextView) findViewById(R.id.no3);
		no5 = (TextView) findViewById(R.id.no5);
		no1.setText(bundle.getString("no1"));
		no2.setText(bundle.getString("no2"));
		no3.setText(bundle.getString("no3"));
		no5.setText(bundle.getString("no5"));
		shades = new String[][] { { "Name", bundle.getString("name"), "Email",
				bundle.getString("email"), "Company",
				bundle.getString("company"), "Country",
				bundle.getString("country"), "City", bundle.getString("city"),
				"State", bundle.getString("state"), "Zip",
				bundle.getString("zip") } };
		SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
				this, createGroupList(), // groupData describes the first-level
											// entries
				R.layout.group_row, // Layout for the first-level entries
				new String[] { "colorName" }, // Key in the groupData maps to
												// display
				new int[] { R.id.groupname }, // Data under "colorName" key goes
												// into this TextView
				createChildList(), // childData describes second-level entries
				R.layout.child_row, // Layout for second-level entries
				new String[] { "shadeName", "rgb" }, // Keys in childData maps
														// to display
				new int[] { R.id.childname, R.id.rgb } // Data under the keys
														// above go into these
														// TextViews
		);
		setListAdapter(expListAdapter);
		back = (Button) findViewById(R.id.h);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(Summary.this,Main.class));
				finish();
			}
		});
	}

	private List createGroupList() {
		ArrayList result = new ArrayList();
		for (int i = 0; i < colors.length; ++i) {
			HashMap m = new HashMap();
			m.put("colorName", colors[i]);
			result.add(m);
		}
		return (List) result;
	}

	private List createChildList() {
		ArrayList result = new ArrayList();
		for (int i = 0; i < shades.length; ++i) {
			ArrayList secList = new ArrayList();
			for (int n = 0; n < shades[i].length; n += 2) {
				HashMap child = new HashMap();
				child.put("shadeName", shades[i][n]);
				child.put("rgb", shades[i][n + 1]);
				secList.add(child);
			}
			result.add(secList);
		}
		return result;
	}
}
