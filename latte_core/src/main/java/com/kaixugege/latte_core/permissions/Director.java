package com.kaixugege.latte_core.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.kaixugege.latte_core.net.callback.IFailure;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Director {

    public Director(Builder builder) {
        this.builder = builder;
    }

    private Builder builder;


    public Director addPer(Per per) {
        builder.perProduct.pers.add(per);
        return this;
    }

    public Director delPer(Per per) {
        builder.perProduct.pers.remove(per);
        return this;
    }

    public Director clearPer(Per per) {
        builder.perProduct.pers.clear();
        return this;
    }

    public PerProduct build() {
        return builder.create();
    }

    public Director checkPermission(Context context) {
        for (Per per : builder.create().pers) {
            if (ContextCompat.checkSelfPermission(context, per.getPermission()) != PackageManager.PERMISSION_GRANTED) {
                per.setState(false);
            } else {
                per.setState(true);
            }
        }
        return this;
    }

    public Director startRequestPermission(Activity context) {
        ArrayList<String> pers = new ArrayList<>();
        for (Per per : builder.create().pers) {
            pers.add(per.getPermission());
        }
        String[] array = new String[pers.size()];
        String[] s = pers.toArray(array);
        ActivityCompat.requestPermissions(context, s, 321);
        return this;
    }


}
