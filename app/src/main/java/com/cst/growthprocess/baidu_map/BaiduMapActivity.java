package com.cst.growthprocess.baidu_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;

import java.util.List;

public class BaiduMapActivity extends AppCompatActivity implements OnGetRoutePlanResultListener {

    private MapView mMapView;

    private BaiduMap mBaiduMap;

    private RoutePlanSearch mSearch;

    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        initView();

    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        PlanNode startPlace = PlanNode
                .withLocation(new LatLng(29.574099, 106.426578));
        PlanNode endPlace = PlanNode
                .withLocation(new LatLng(29.583137, 106.425011));
        //步行路径规划
        // mSearch.walkingSearch(new WalkingRoutePlanOption().from(startPlace).to(endPlace));
        //驾车路径规划
        mSearch.drivingSearch(new DrivingRoutePlanOption().from(startPlace).to(endPlace));

        mLatLng = new LatLng(29.574099, 106.426578);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(mLatLng);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.a_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(mLatLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

        //构建Marker图标
        BitmapDescriptor bitmap2 = BitmapDescriptorFactory
                .fromResource(R.mipmap.b_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option2 = new MarkerOptions()
                .position(new LatLng(29.583137, 106.425011))
                .icon(bitmap2);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option2);

        mBaiduMap.animateMapStatus(mapStatusUpdate);
        mBaiduMap.setMapStatus(
                MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(20).build()));
    }


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        if (walkingRouteResult == null
                || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            ToastDiy.Show(this, "抱歉，未找到结果");
            return;
        }
        Log.i("onGetWalkingRouteResult", "线路条数：" + walkingRouteResult.getRouteLines().size());
        List<WalkingRouteLine> routeLines = walkingRouteResult.getRouteLines();

        WalkingRouteLine aLine = routeLines.get(0);
        int distance = aLine.getDistance();// 单位：米
        int duration = aLine.getDuration();// 单位：秒

        // 在地图上标注出路线
        WalkingRouteOverlay overlay = new WalkingRouteOverlay(
                mBaiduMap);
        overlay.setData(aLine);
        overlay.addToMap();
        overlay.zoomToSpan();

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

        if (drivingRouteResult == null
                || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            ToastDiy.Show(this, "抱歉，未找到结果");
            return;
        }
        Log.i("onGetWalkingRouteResult", "线路条数：" + drivingRouteResult.getRouteLines().size());
        List<DrivingRouteLine> routeLines = drivingRouteResult.getRouteLines();

        DrivingRouteLine aLine = routeLines.get(0);
        int distance = aLine.getDistance();// 单位：米
        int duration = aLine.getDuration();// 单位：秒
//
//        // 在地图上标注出路线
        DrivingRouteOverlay overlay = new DrivingRouteOverlay(
                mBaiduMap);
        overlay.setData(aLine);
        overlay.addToMap();
        overlay.zoomToSpan();

        List<LatLng> list = aLine.getAllStep().get(0).getWayPoints();
        for (LatLng latlng : list) {
            Log.v("MMMMMMMMMMMM", latlng.latitude + "------" + latlng.longitude);
        }


    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
