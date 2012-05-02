package org.meruvian.midas.android.adapter;

import org.meruvian.midas.android.R;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuAdapterView extends LinearLayout {

	public MenuAdapterView(Context context, Adapter entry) {
		super(context);

		this.setOrientation(VERTICAL);
		this.setTag(entry);

		View v = inflate(context, R.layout.menu_row, null);

		TextView tvContact = (TextView) v.findViewById(R.id.name);
		tvContact.setText(entry.getName());

		ImageView tvMail = (ImageView) v.findViewById(R.id.icon);
		tvMail.setImageResource(entry.getIcon());

		addView(v);
	}

}
