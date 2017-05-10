package com.zxb.xmap.remote.basic.map;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapFragment;

/**
 * 基本地图（MapFragment）实现
 */
public class BaseMapFragmentActivity extends Activity {
	private AMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.zxb.xmap.remote.R.layout.basemap_fragment_activity);
		setUpMapIfNeeded();

		setTitle("基本地图（MapFragment）");
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}

	/**
	 * 获取Amap 对象
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(com.zxb.xmap.remote.R.id.map)).getMap();
		}
	}

}
