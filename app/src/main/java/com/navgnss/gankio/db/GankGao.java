package com.navgnss.gankio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navgnss.gankio.bean.base.ResultData;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
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

    private String INSERT_ARRAY="INSERT INTO iamges VALUES ";


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
        ResultData resultData=cursorToNewsGank(cursor);
        insertArray(data.getImageList(),db);
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

        updateArray(data.getImageList(),db);
        db.update(DBHelper.TABLE_NAME,values,DBHelper._ID+"="+data.getId(),null);

        db.close();
    }


    /**
     * 自动判断更新/插入 数据
     * */
    public void insertOrUpdateGank(ResultData data){

        String id=data.getId();
        if(newOfTheId(id)!=null){
            updateResult(data);
        }
        else{
            insertGank(data);
        }
    }

    /**
     * 根据id查询
     * **/
    private ResultData newOfTheId(String id) {

        Cursor cursor=db.query(DBHelper.TABLE_NAME,allColums,DBHelper._ID+" = "+id,null,null,null,null);
        cursor.moveToFirst();
        ResultData data = cursorToNewsGank(cursor);
        cursor.close();
        return data;

    }

    /**
     *
     * */
    private ResultData cursorToNewsGank(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            return new GsonBuilder().create().fromJson(cursor.getString(2),new TypeToken<ResultData>() {
            }.getType());
        } else {
            return null;
        }
    }
    /**
     * 保存 数组
     * */
    public void insertArray(List<String> strList,SQLiteDatabase db){

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(strList);
            oos.flush();

            byte data[] = baos.toByteArray();
            oos.close();
            baos.close();
            db.execSQL(INSERT_ARRAY,new Object[]{data});
            //db.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 更新数组
     * */
    public void updateArray(List<String> strList,SQLiteDatabase db){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(strList);
            oos.flush();

            byte data[] = baos.toByteArray();
            oos.close();
            baos.close();
            db.execSQL("UPDATE "+DBHelper.TABLE_NAME+" SET "+DBHelper.IMAGES,new Object[]{data});
           // db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
