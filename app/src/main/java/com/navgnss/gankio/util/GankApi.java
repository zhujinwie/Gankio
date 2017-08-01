package com.navgnss.gankio.util;



import com.navgnss.gankio.bean.AndroidData;
import com.navgnss.gankio.bean.DailyData;
import com.navgnss.gankio.bean.FuLiData;
import com.navgnss.gankio.bean.GankData;
import com.navgnss.gankio.bean.IosData;
import com.navgnss.gankio.bean.VideoData;

import io.reactivex.Flowable;

import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.navgnss.gankio.bean.Constants.AndroidSize;
import static com.navgnss.gankio.bean.Constants.FuliSize;
import static com.navgnss.gankio.bean.Constants.IosSize;
import static com.navgnss.gankio.bean.Constants.VideoSize;
import static com.navgnss.gankio.bean.Constants.ZiYuanSize;


/**
 * Created by ZhuJinWei on 2017/4/21.
 */

public interface GankApi {

    @GET("data/福利/"+FuliSize+"/"+"{page}")
    Flowable<FuLiData> getFuLi(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Flowable<DailyData> getDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/Android/"+AndroidSize+"/{page}")
    Flowable<AndroidData> getAndroid(@Path("page") int page);

    @GET("data/iOS/"+IosSize+"/{page}")
    Flowable<IosData> getIos(@Path("page") int page);

    @GET("data/休息视频/"+VideoSize+"/{page}")
    Flowable<VideoData> getVideo(@Path("page") int page);

    @GET("data/扩展资源/"+ZiYuanSize+"/{page}")
    Flowable<GankData> getZiYuan(@Path("page") int page);

    @GET("data/前端/"+ZiYuanSize+"/{page}")
    Flowable<GankData> getQianDuan(@Path("page") int page);




/*    //链接http://op.juhe.cn/robot/index？info=&dtype=&loc=&lon=&lat=&userid=&key=94a26f7b9e10a64f59c625a20bba2f4a
    @GET("index")
    Flowable<RobotData> getInfo(@QueryMap Map<String,String> options);*/

}
