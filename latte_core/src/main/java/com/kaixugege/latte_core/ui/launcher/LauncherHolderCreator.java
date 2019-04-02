package com.kaixugege.latte_core.ui.launcher;


import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @Author: KaixuGege
 * Time:           2019/3/27
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
