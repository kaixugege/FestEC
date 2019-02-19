package com.kaixugege.latte_core.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatDialog;
import com.kaixugege.latte_core.R;
import com.kaixugege.latte_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @Author: KaixuGege
 * Time:           2019/2/18
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LatteLoader {
    //设置一个缩放比例,根据屏幕的大小调整自己本身的大小
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    //    创建一个集合统一管理所有的loader
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();//当不需要这些loader 只需要遍历 list  一一关闭即可

    //默认的 loading 样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context,type.name());
    }

    /**
     * 传入当前的context
     */
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCrator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int devicewidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = devicewidth / LOADER_SIZE_SCALE+ deviceHeight / devicewidth;
            layoutParams.height = deviceHeight / LOADER_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeight / LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }


    //这个是重载
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    //
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                dialog.cancel();//用这个方法取消会执行 cancel方法的一些回调
//                dialog.dismiss();//只是单纯的消失掉它

            }
        }
    }

}
