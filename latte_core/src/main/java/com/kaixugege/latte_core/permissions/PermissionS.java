package com.kaixugege.latte_core.permissions;

import android.content.Context;

import java.util.ArrayList;

import androidx.appcompat.view.menu.MenuWrapperFactory;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class PermissionS {

    final public ArrayList<Permission> permissions = new ArrayList<>();

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PermissionS(Context context) {
        this.context = context;
    }
    public PermissionS() {
    }
    private Context context;



}
