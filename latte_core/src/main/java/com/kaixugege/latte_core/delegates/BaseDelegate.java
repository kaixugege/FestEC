package com.kaixugege.latte_core.delegates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @Author: KaixuGege
 * Time:           2019/1/7
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public View rootView = null;

    public abstract Object setLayout();

    public abstract void onBindView( Bundle savedInstanceState, View rootView);//当绑定视图的时候


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) rootView;
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            this.rootView = rootView;
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.rootView = null;
        if (mUnbinder != null) mUnbinder.unbind();//解除绑定
    }
}
