package com.application.runoobapp.views.amap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.application.runoobapp.R;
import com.application.runoobapp.base.action.ToastAction;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class AmapDemoActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,
        ToastAction {

    private final static String TAG = AmapDemoActivity.class.getSimpleName();

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    private TextView locationResultTextView;

    private TextView addressResultTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        this.locationResultTextView = findViewById(R.id.locate_result);
        this.addressResultTextView = findViewById(R.id.address_result);
    }

    public void getLocation(View view) {
        MapUtil.getInstance().initLocation(this, new MapUtil.LocateCallback() {
            @Override
            public void onLocateSuccess(AMapLocation result) {
                AmapDemoActivity.this.runOnUiThread(() -> {
                    StringBuilder locationResult = new StringBuilder("纬度: ").append(result.getLatitude())
                                    .append(" 经度: ").append(result.getLongitude())
                                    .append(" cityCode: ").append(result.getCityCode())
                                    .append(" adCode: ").append(result.getAdCode());
                    StringBuilder addressResult = new StringBuilder("地址: ").append(result.getCountry())
                                    .append(result.getProvince()).append(result.getCity())
                                    .append(result.getDistrict()).append(result.getStreet())
                                    .append(result.getStreetNum());

                    if (!addressResult.toString().endsWith("号")){
                        addressResult.append("号");
                    }

                    locationResultTextView.setText(locationResult.toString());
                    addressResultTextView.setText(addressResult.toString());

                    locationResultTextView.setVisibility(View.VISIBLE);
                    addressResultTextView.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onLocationFail(int errCode, String errMsg) {
                AmapDemoActivity.this.runOnUiThread(() -> {
                    toast("定位失败：" + errMsg);
                });

            }
        });
        MapUtil.getInstance().startLocation();
    }

    // 3D:8A:A6:D0:21:02:E6:C5:B4:B4:C1:6F:81:F7:2F:7E:15:49:8A:E9
    public void getAppSha(View view) {
        String sha1 = MapUtil.sHA1(this);
        Log.i(TAG, "getAppSha: " + sha1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MapUtil.getInstance().stopLocation();
    }

    public void requestPermission(View view) {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "you have got location permission", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "需要定位权限以便提供服务",
                    LOCATION_PERMISSION_REQUEST_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果传递给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    // 在需要的时候，可以重写下面的方法来处理权限请求结果
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        // 用户授予了权限
        Toast.makeText(this, "you have got location permission", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // 用户拒绝了权限
        Toast.makeText(this, "you have rejected location permission", Toast.LENGTH_LONG).show();

    }
}
