package org.meruvian.midas.android.adapter;

public class Adapter {
	private String name;
	private int icon;

	public Adapter(String name, int icon) {
		super();
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

}
