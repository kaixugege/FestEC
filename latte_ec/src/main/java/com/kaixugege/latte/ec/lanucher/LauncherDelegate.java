package com.kaixugege.latte.ec.lanucher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte.ec.R2;
import com.kaixugege.latte_core.app.Latte;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.ui.launcher.ScollLauncherTag;
import com.kaixugege.latte_core.util.Storage.LattePreference;
import com.kaixugege.latte_core.util.timer.BaseTimerTask;
import com.kaixugege.latte_core.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * @Author: KaixuGege
 * Time:           2019/3/26
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    private int mCount = 5;

    @BindView(R2.id.tv_launcher_timer)
    public TextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
    }

    private Timer mTimer = null;

    private void initTimer() {

        Log.d("LauncherDelegate", "开始初始化 Timer");
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mTvTimer = rootView.findViewById(R.id.tv_launcher_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    Log.d("LauncherDelegate", "定时结束，开始检查是否启动轮播界面");
                    checkIsShowScroll();
                }
            }
        });
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIsShowScroll();
            }
        });
        initTimer();
    }



    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate());
        }else {
            Log.d("LauncherDelegate", "用户是第一次进入 app");
            //  检查用户是否登陆了 app
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("LauncherDelegate", "开始timer事件" + mCount);
                if (mTvTimer != null) {
                    try {
                        mTvTimer.setText(MessageFormat.format( "跳过\n{0}s",mCount));
                    } catch (Exception ex) {
                        Log.d("LauncherDelegate", "" + ex.getMessage());
                    }
                    Log.d("LauncherDelegate", "执行到这里来准备减1" + mCount);
                    mCount = mCount - 1;
                    Log.d("LauncherDelegate", "执行到这里来了" + mCount);
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            Log.d("LauncherDelegate", "定时结束，开始检查是否启动轮播界面");
                            checkIsShowScroll();
                        }
                    }
                } else {
                    Log.d("LauncherDelegate", "mTvTimer = null");
                }
            }
        });


    }
}
