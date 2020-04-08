package com.xpxcoder.xpxDroid.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xpsoft on 2018/1/13.
 */

public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqliteXpxDemo.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private OrmLiteDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
//            TableUtils.createTable(connectionSource, LocalGroupMessage.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*catch (java.sql.SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            if(newVersion==3&&oldVersion<3) {
                //因为没建索引，然后当前只是测试阶段。所以直接删除重新建
//                TableUtils.dropTable(connectionSource,LocalGroupMessage.class,true);
//                TableUtils.createTable(connectionSource, LocalGroupMessage.class);
//                getDao(LocalGroupMessage.class).executeRawNoArgs("ALTER  TABLE 'tb_GroupMessage' ADD COLUMN msgType INTEGER DEFAULT 0;");
//                getDao(LocalGroupMessage.class).executeRawNoArgs("ALTER  TABLE 'tb_GroupMessage' ADD COLUMN hasRead INTEGER DEFAULT 0;");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        /*catch (java.sql.SQLException e2) {
            e2.printStackTrace();
        }*/



    }

    private static OrmLiteDatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized OrmLiteDatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (OrmLiteDatabaseHelper.class) {
                if (instance == null)
                    instance = new OrmLiteDatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
                daos.put(className, dao);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
