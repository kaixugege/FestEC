package com.kaixugege.latte_core.ui.launcher;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;


import com.bigkoo.convenientbanner.holder.Holder;

/**
 * @Author: KaixuGege
 * Time:           2019/3/27
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LauncherHolder implements Holder<Integer> {


    private AppCompatImageView compatImageView = null;

    @Override
    public View createView(Context context) {
        compatImageView = new AppCompatImageView(context);
        return compatImageView;
    }

    //每次更新ui需要滑动的东西
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        compatImageView.setBackgroundResource(data);

    }
}
