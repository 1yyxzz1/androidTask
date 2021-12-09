package com.example.AndroidTask.MainFram.TakePhotoFram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.AndroidTask.MainFram.viewpageFram.EnterMainFram;
import com.example.cq_1014_task.R;

public class OpenMap extends AppCompatActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private TextView myLocation = null;
    TextView textView=null;
    Button finishLoc=null;
    private final int ReturnLocation=1;//返回地址定位

    StringBuilder currentPosition;
    // 是否是第一次定位
    private boolean isFirstLocate = true;
    // 当前定位模式
    private MyLocationConfiguration.LocationMode locationMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.mymap);
        finishLoc=findViewById(R.id.but_finishLoc);
        finishLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = currentPosition.toString().trim();
                Intent intent=new Intent(OpenMap.this, EnterMainFram.class);
                Bundle bundle=new Bundle();
                bundle.putString("returnLocation",location);
                intent.putExtras(bundle);
                setResult(ReturnLocation, intent);
                finish();
            }
        });
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        mMapView=findViewById(R.id.mapView);
        mBaiduMap=mMapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);
        myLocation=findViewById(R.id.location);




        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location.getLocType()==BDLocation.TypeNetWorkLocation||location.getLocType()==BDLocation.TypeGpsLocation){
                navigateTo(location);
                currentPosition = new StringBuilder();
                currentPosition.append(location.getProvince());
                currentPosition.append(location.getCity());
                currentPosition.append(location.getDistrict());
                currentPosition.append(location.getStreet());
                myLocation.setText("当前位置信息："+location.getProvince()+","+location.getCity()+","+location.getDistrict()+","+location.getStreet());
               // Toast.makeText(getApplicationContext(),location.getLatitude()+","+location.getAltitude(),Toast.LENGTH_LONG).show();
            }

        }
    }
    public void navigateTo(BDLocation location){
        if (isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(20f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        mBaiduMap.setMyLocationEnabled(true);
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mMapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);

    }
}
