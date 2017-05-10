
package com.zxb.xmap.remote.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class FeatureView extends FrameLayout {

	public FeatureView(Context context) {
		super(context);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(com.zxb.xmap.remote.R.layout.feature, this);
	}

	public synchronized void setTitleId(int titleId) {
		((TextView) (findViewById(com.zxb.xmap.remote.R.id.title))).setText(titleId);
	}
	public synchronized void setTitleId(int titleId, boolean issub) {
		String title = this.getResources().getString(titleId);
		if (issub) {
			((TextView) (findViewById(com.zxb.xmap.remote.R.id.title))).setText("         "+title);
		} else{
			((TextView) (findViewById(com.zxb.xmap.remote.R.id.title))).setText(title);
		}

	}
//	public synchronized void setDescriptionId(int descriptionId) {
//		((TextView) (findViewById(R.id.description))).setText(descriptionId);
//	}



}
