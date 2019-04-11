package com.kaixugege.latte_core.permissions;

import android.Manifest;
import android.content.Context;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Example {
    private void main() {

        Director director = new Director(new PerBuilder())
                .addPer(new Per(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .addPer(new Per(Manifest.permission.ACCESS_WIFI_STATE))
                .addPer(new Per(Manifest.permission.READ_PHONE_STATE))
                .addPer(new Per(Manifest.permission.ACCESS_FINE_LOCATION))
                .addPer(new Per(Manifest.permission.ACCESS_COARSE_LOCATION))
                .addPer(new Per(Manifest.permission.CHANGE_WIFI_STATE))
                .addPer(new Per(Manifest.permission.SYSTEM_ALERT_WINDOW))
                .addPer(new Per(Manifest.permission.INTERNET))
                .addPer(new Per(Manifest.permission.CALL_PHONE))
                .addPer(new Per(Manifest.permission.CAMERA));

    }
}
