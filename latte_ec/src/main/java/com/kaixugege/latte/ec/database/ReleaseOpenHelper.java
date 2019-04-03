package com.kaixugege.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @Author: KaixuGege
 * Time:           2019/4/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper{

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
