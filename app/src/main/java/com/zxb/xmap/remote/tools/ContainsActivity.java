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
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.zxb.xmap.remote.R;
import com.zxb.xmap.remote.util.Constants;

import java.util.ArrayList;
import java.util.List;

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
    List<LatLng> latLngs = new ArrayList<LatLng>();
    int addPointNum=0;

    boolean highUp=false;

    LatLng ZHONGGUANCUN = new LatLng(36.61532526, 116.97343647);// 北京市中关村经纬度

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

        aMap.reloadMap();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.61532526,
                116.97343647), 16));

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
                    addPointNum=0;
                    latLngs.clear();
                    aMap.reloadMap();
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.61532526,
                            116.97343647), 16));

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
        LatLng startPoint = new LatLng(36.61532526, 116.97343647);
        LatLng endPoint = new LatLng(36.61532526, 116.97343647);
//        LatLng left = new LatLng(39.983456, 116.3154950);
//        LatLng right = new LatLng(39.983456, 116.3154950);
//        double a1[] = new double[polygonNum];
//        double b1[] = new double[polygonNum];


//选择起始点
        double changelat = 0;
        double changelng = 0;

        changelat = polygon.getPoints().get(0).latitude;
        changelng = polygon.getPoints().get(0).longitude;

        for (int i = 1; i < polygonNum; i++)
        {
//            a1[i] = polygon.getPoints().get(i).latitude;
//            b1[i] = polygon.getPoints().get(i).longitude;

            if (polygon.getPoints().get(i).longitude < changelng)
            {
                changelat = polygon.getPoints().get(i).latitude;
                changelng = polygon.getPoints().get(i).longitude;
            }
        }
//        for (int i = 1; i < polygonNum; i++)
//        {
//
//            if (changelat>polygon.getPoints().get(i).latitude)
//            {
//              highUp=true;
//            }
//        }
//起始点开始记录点

//右移五米上下扫描

        latLngs.add(new LatLng(changelat, changelng));
        addPointNum++;

        addPoint(changelat,changelng+0.0005000);


        //boolean b1 = polygon.contains(arg0);
        // Toast.makeText(ContainsActivity.this, "是否在围栏里面：" + b1, Toast.LENGTH_SHORT).show();


    }
    private void addPoint(double changelat,double changelng)
    {
        latLngs.add(new LatLng(changelat, changelng));
        addPointNum++;
        while (polygon.contains(latLngs.get(addPointNum-1)))
        {
            if (highUp)
            {
                changelat-=0.0005000;
            }
            else
            {
                changelat+=0.0001000;
            }
            latLngs.add(new LatLng(changelat, changelng));
            addPointNum++;
        }
        highUp=!highUp;
        changelng+=0.0005000;

        double changelat1=changelat;
        if (highUp)
        {
            changelat1-=0.0001;
        }
        else
        {
            changelat1+=0.0005;
        }

        if (polygon.contains(new LatLng(changelat,changelng)))
        {
            addPoint(changelat,changelng);
        }
        else if (polygon.contains(new LatLng(changelat1,changelng)))
        {
            latLngs.remove(addPointNum-1);
            addPointNum--;
            addPoint(changelat1,changelng);
        }
        else
        {
            addPolylinesWithTexture();
        }
    }
    //绘制一条纹理线
    private void addPolylinesWithTexture()
    {
//        //四个点
//        LatLng A = new LatLng(Lat_A + 1, Lon_A + 1);
//        LatLng B = new LatLng(Lat_B + 1, Lon_B + 1);
//        LatLng C = new LatLng(Lat_C + 1, Lon_C + 1);
//        LatLng D = new LatLng(Lat_D + 1, Lon_D + 1);

        //用一个数组来存放纹理
        List<BitmapDescriptor> texTuresList = new ArrayList<BitmapDescriptor>();
        texTuresList.add(BitmapDescriptorFactory.fromResource(com.zxb.xmap.remote.R.drawable.map_alr));

        //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色
        List<Integer> texIndexList = new ArrayList<Integer>();
        texIndexList.add(0);//对应上面的第0个纹理


        PolylineOptions options = new PolylineOptions();
        options.width(15);//设置宽度

        //加入四个点
        for (int i=0;i<latLngs.size();i++)
        {
            options.add(latLngs.get(i));
        }

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);

        //设置纹理对应的Index
        options.setCustomTextureIndex(texIndexList);

        aMap.addPolyline(options);
    }

}
