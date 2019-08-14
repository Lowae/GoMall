package com.hao.gomall.mall.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DBManager {

    private static final String DB_NAME = "gomall.db";

    private DaoSession mDaoSession;
    private UserProfileDao mDao;

    private DBManager() {

    }

    public DBManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class DBManagerHolder {
        private static final DBManager INSTANCE = new DBManager();
    }

    private void initDao(Context context) {
        final DbOpenHelper helper = new DbOpenHelper(context, DB_NAME);
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    public final UserProfileDao getDao(){
        return mDao;
    }
}
