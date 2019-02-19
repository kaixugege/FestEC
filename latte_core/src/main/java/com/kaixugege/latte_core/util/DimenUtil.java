package com.kaixugege.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.kaixugege.latte_core.app.Latte;

/**
 * @Author: KaixuGege
 * Time:           2019/2/18
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class DimenUtil {
    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    /**
     * 获取屏幕的高
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
