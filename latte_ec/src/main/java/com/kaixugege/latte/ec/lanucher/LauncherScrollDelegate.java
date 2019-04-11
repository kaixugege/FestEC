package com.kaixugege.latte.ec.lanucher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte_core.app.AccountManage;
import com.kaixugege.latte_core.app.IUserChecker;
import com.kaixugege.latte_core.delegates.BaseDelegate;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.ui.launcher.ILauncherListener;
import com.kaixugege.latte_core.ui.launcher.LauncherHolderCreator;
import com.kaixugege.latte_core.ui.launcher.OnLauncherFinshTag;
import com.kaixugege.latte_core.ui.launcher.ScollLauncherTag;
import com.kaixugege.latte_core.util.Storage.LattePreference;

import java.util.ArrayList;

/**
 * @Author: KaixuGege
 * Time:           2019/3/27
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private static final String TAG = "LauncherScrollDelegate";

    private ConvenientBanner<Integer> mConvenientBanner = null;

    private ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        //业务中，尽量用图片代替代码，框架中，尽量用代码代替图片
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }


    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);//启动栈的模式
        } else {
            //检查用户是否已经登陆了App
        }
    }

    @Override
    public void onItemClick(int position) {

        //如果点的是最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference
                    .setAppFlag(ScollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            Log.d(TAG, "点击的式最后一个" + "这是第 " + position + "个");
            //检查用户是否已经登陆

            AccountManage.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    mILauncherListener.onLauncherFinsh(OnLauncherFinshTag.SINGED);
                }

                @Override
                public void onNotSignIn() {
                    mILauncherListener.onLauncherFinsh(OnLauncherFinshTag.NOT_SINGED);
                }
            });
        } else {
            Log.d(TAG, "不是最后一个" + "这是第 " + position + "个");
        }

    }
}
