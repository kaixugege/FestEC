package com.kaixugege.latte_core.permissions;

import android.widget.Button;

/**
 * @Author: KaixuGege
 * Time:           2019/4/9
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class PerBuilder implements Builder {




    @Override
    public PerProduct create() {
        return perProduct;
    }

    @Override
    public void addPer(Per per) {
        perProduct.pers.add(per);
    }

    @Override
    public void delPer(Per per) {
        perProduct.pers.remove(per);
    }

    @Override
    public void clearPer(Per per) {
        perProduct.pers.clear();
    }

}
