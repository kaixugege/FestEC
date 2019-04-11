package com.kaixugege.latte_core.activites;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.ContentFrameLayout;
import com.kaixugege.latte_core.R;
import com.kaixugege.latte_core.app.ISignListener;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.ui.launcher.ILauncherListener;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @Author: KaixuGege
 * Time:           2019/1/7
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public abstract class ProxyActivity extends SupportActivity  implements
        ISignListener, ILauncherListener {
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    private void initContainer( Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);

        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.runFinalization();
    }
}
