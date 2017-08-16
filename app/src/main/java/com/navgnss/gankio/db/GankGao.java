package com.navgnss.gankio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navgnss.gankio.bean.GankData;
import com.navgnss.gankio.bean.base.ResultData;

import java.util.List;

/**
 * Created by ZhuJinWei on 2017/8/16.
 *
 * 数据库操作类
 *
 */

public class GankGao {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    private String[] allColums={DBHelper._ID,DBHelper.CREATEDAT,DBHelper.DESC,DBHelper.PUBLISHEDAT,
                            DBHelper.SOURCE,DBHelper.TYPE,DBHelper.URL,DBHelper.USED,DBHelper.WHO,DBHelper.IMAGES};

    public GankGao(Context context){
        this.context=context;
        dbHelper=new DBHelper(context);
    }

    /**
     * 初始化
     * */
    public void init(){
        db=dbHelper.getWritableDatabase();
    }

    /**
     * 向数据库插入数据
     * */
    public ResultData insertGank(ResultData data){

        ContentValues values=new ContentValues();
        values.put(DBHelper.CREATEDAT,data.getCreatedAt());
        values.put(DBHelper.DESC,data.getDesc());
        values.put(DBHelper.PUBLISHEDAT,data.getPublishedAt());
        values.put(DBHelper.SOURCE,data.getSource());
        values.put(DBHelper.TYPE,data.getType());
        values.put(DBHelper.URL,data.getUrl());
        values.put(DBHelper.USED,data.isUsed());
        values.put(DBHelper.WHO,data.getWho());

        long insertId=db.insert(DBHelper.TABLE_NAME,null,values);
        Cursor cursor=db.query(DBHelper.TABLE_NAME,allColums,DBHelper._ID+"="+insertId,null,null,null,null);
        cursor.moveToFirst();
        ResultData resultData=cursorToNewsList(cursor);
        cursor.close();
        return resultData;
    }

    /**
     * 根据id更新数据库
     * */
    public void updateResult(ResultData data){

        ContentValues values=new ContentValues();
        values.put(DBHelper.CREATEDAT,data.getCreatedAt());
        values.put(DBHelper.DESC,data.getDesc());
        values.put(DBHelper.PUBLISHEDAT,data.getPublishedAt());
        values.put(DBHelper.SOURCE,data.getSource());
        values.put(DBHelper.TYPE,data.getType());
        values.put(DBHelper.URL,data.getUrl());
        values.put(DBHelper.USED,data.isUsed());
        values.put(DBHelper.WHO,data.getWho());

        db.update(DBHelper.TABLE_NAME,values,DBHelper._ID+"="+data.getId(),null);

    }


    /**
     * 自动判断更新/插入 数据
     * */
    public void insertOrUpdateGank(ResultData data){



    }



    private ResultData cursorToNewsList(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            return new GsonBuilder().create().fromJson(cursor.getString(2),new TypeToken<ResultData>() {
            }.getType());
        } else {
            return null;
        }
    }



}
