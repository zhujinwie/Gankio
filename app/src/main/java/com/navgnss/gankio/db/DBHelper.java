package com.navgnss.gankio.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by ZhuJinWei on 2017/8/11.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="gankio_news_list";
    public static final String _ID="_id";
    public static final String CREATEDAT="createdAt";
    public static final String DESC="desc";
    public static final String PUBLISHEDAT="publishedAt";
    public static final String SOURCE="source";
    public static final String TYPE="type";
    public static final String URL="url";
    public static final String USED="used";
    public static final String WHO="who";
    public static final String IMAGES="images";

    public static final String DATABASE_NAME="gankio_news.db";
    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_CREATE="CREATE TABLE"+TABLE_NAME
            +"("+_ID+" INTEGER PRIMAY KEY AUTOINCREMENT, "
            +CREATEDAT+" TEXT NOT NULL, "
            +DESC+" TEXT NOT NULL, "
            +PUBLISHEDAT+" TEXT NOT NULL, "
            +SOURCE+" TEXT  NOT NULL, "
            +TYPE+" TEXT NUT NULL, "
            +URL+"  TEXT NOT NULL, "
            +USED+" NUMERIC NOT NULL, "
            +WHO+" TEXT NOT NULL, "
            +IMAGES+" BLOB);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
}
