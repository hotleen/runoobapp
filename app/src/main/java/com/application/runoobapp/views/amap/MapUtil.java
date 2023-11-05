package com.application.runoobapp.views.amap;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class MapUtil {

    //声名单例
    private static MapUtil mapUtil = new MapUtil();

    private Activity activity;
    private TextView textView;

    private LocateCallback locateCallback;

    private MapUtil() {
    }

    public static MapUtil getInstance() {
        return mapUtil;
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;

    //初始化定位信息
    public void initLocation(Activity activity, LocateCallback locateCallback) {

        this.activity = activity;
        this.textView = textView;
        this.locateCallback = locateCallback;
        //初始化定位
        try {
            mLocationClient = new AMapLocationClient(activity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);


        setLocationMode();

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    //设置模式
    private void setLocationMode() {
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
    }

    //开启一次定位
    public void startLocation() {
        if (mLocationClient != null) {
            mLocationClient.startLocation();
        }
    }

    //停止定位服务
    public void stopLocation() {
        //停止定位后，本地定位服务并不会被销毁
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
    }

    public class MyAMapLocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    locateCallback.onLocateSuccess(amapLocation);
                    stopLocation();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    locateCallback.onLocationFail(amapLocation.getErrorCode(), amapLocation.getErrorInfo());
                }
            } else {
                locateCallback.onLocationFail(-1, "null result");
            }
        }
    }

    //销毁定位客户端，同时销毁本地定位服务。
    public void destroyService() {
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
    }

    public interface LocateCallback{
        void onLocateSuccess(AMapLocation result);

        void onLocationFail(int errCode, String errMsg);
    }

    //检查是否缺少权限
//    public static void checkPermission(Context context, Activity activity) {
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请ACCESS_COARSE_LOCATION权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
//        }
//
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请ACCESS_FINE_LOCATION权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
//        }
//
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
//        }
//
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请READ_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
//        }
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请READ_PHONE_STATE权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE},
//                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
//        }
//
//
//    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}