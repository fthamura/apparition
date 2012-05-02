package org.meruvian.midas.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuAdapter extends BaseAdapter {
	private Context context;
	private List<Adapter> listPhonebook;
	
	public MenuAdapter(Context context, List<Adapter> listPhonebook){
		this.context = context;
		this.listPhonebook = listPhonebook;
	}
	public int getCount() {
		return listPhonebook.size();
	}

	public Object getItem(int position) {
		return listPhonebook.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		Adapter entry = listPhonebook.get(position);
		MenuAdapterView adapterView = new MenuAdapterView(context, entry);
		return adapterView;
	}

}
