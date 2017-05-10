package com.zxb.xmap.remote.basic;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;

public class BaseMapSupportFragmentActivity extends FragmentActivity {
	private AMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.zxb.xmap.remote.R.layout.basemap_support_fragment_activity);
		setUpMapIfNeeded();

		setTitle("基本地图（SupportMapFragment）");
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
	 * 获取 AMap 对象
	 */
	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(com.zxb.xmap.remote.R.id.map)).getMap();
		}
	}

}
