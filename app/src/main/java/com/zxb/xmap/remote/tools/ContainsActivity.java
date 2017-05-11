package com.zxb.xmap.remote.tools;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.zxb.xmap.remote.R;
import com.zxb.xmap.remote.util.Constants;

/**
 * 点是否在多边形内 示例
 */
public class ContainsActivity extends Activity implements OnMapClickListener
{
    private AMap aMap;
    private MapView mapView;
    private Polygon polygon;
    private TextView Text;
    private Marker marker;

    PolygonOptions pOption = null;

    int polygonNum = 0;

    LatLng ZHONGGUANCUN = new LatLng(39.983456, 116.3154950);// 北京市中关村经纬度

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc_activity);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();

    }

    private void init()
    {
        if (aMap == null)
        {
            aMap = mapView.getMap();
            setUpMap();
        }
        Button button_start = (Button) findViewById(R.id.button_start);
        button_start.setOnClickListener(mylistener);

        Button button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(mylistener);
//        Text = (TextView) findViewById(R.id.info_text);
//        Text.setText("请单击地图");
    }

    private void setUpMap()
    {
        aMap.setOnMapClickListener(this);
        // 绘制一个长方形
//        PolygonOptions pOption = new PolygonOptions();
//        pOption.add(new LatLng(39.926516, 116.389366));
//        pOption.add(new LatLng(39.924870, 116.403270));
//        pOption.add(new LatLng(39.918090, 116.406274));
//        pOption.add(new LatLng(39.909466, 116.397863));
//        pOption.add(new LatLng(39.913021, 116.387134));
//        polygon = aMap.addPolygon(pOption.strokeWidth(4)
//                .strokeColor(Color.argb(50, 1, 1, 1))
//                .fillColor(Color.argb(50, 1, 1, 1)));
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.926516,
//                116.389366), 16));
//		
    }

    /**
     * 点击地图的回调
     */
    @Override
    public void onMapClick(LatLng arg0)
    {

        polygonNum++;
        pOption.add(arg0);
        marker = aMap.addMarker(new MarkerOptions().position(arg0));
//        boolean b1 = polygon.contains(arg0);
//        Toast.makeText(ContainsActivity.this, "是否在围栏里面：" + b1, Toast.LENGTH_SHORT).show();

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update)
    {

        aMap.moveCamera(update);

    }

    View.OnClickListener mylistener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch (v.getId())
            {
                /**
                 * 点击“去中关村”按钮响应事件
                 */
                case R.id.button_start:
                    pOption = new PolygonOptions();
                    polygonNum = 0;
                    if (pOption != null)
                    {
                        aMap.clear();
                    }
                    if (marker != null)
                    {
                        marker.remove();
                    }
                    aMap.reloadMap();
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.67453,
                            117.132008), 16));

                    break;

                /**
                 * 点击“去陆家嘴”按钮响应事件
                 */
                case R.id.button_ok:
                    polygon = aMap.addPolygon(pOption.strokeWidth(4)
                            .strokeColor(Color.argb(50, 1, 1, 1))
                            .fillColor(Color.argb(50, 1, 1, 1)));

                    AddFlyLine();
//                    changeCamera(
//                            CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                                    Constants.SHANGHAI, 18, 30, 0)));
//                    aMap.clear();
//                    aMap.addMarker(new MarkerOptions().position(Constants.SHANGHAI)
//                            .icon(BitmapDescriptorFactory
//                                    .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    break;

                default:
                    break;
            }
        }

    };

    private void AddFlyLine()
    {
        LatLng up = null;
        LatLng down = null;
        LatLng left = null;
        LatLng right = null;
        LatLng change = null;

        //取出来最小的点
        double a=polygon.getPoints().get(0).latitude;
        double b=polygon.getPoints().get(0).longitude;//jingdu
        for (int i = 1; i < polygonNum; i++)
        {
            double a1=polygon.getPoints().get(i).latitude;
            double b1=polygon.getPoints().get(i).longitude;//jingdu

            if (a1<a)
            {
                change=polygon.getPoints().get(i);
            }

        }

        //boolean b1 = polygon.contains(arg0);
       // Toast.makeText(ContainsActivity.this, "是否在围栏里面：" + b1, Toast.LENGTH_SHORT).show();


    }
}
