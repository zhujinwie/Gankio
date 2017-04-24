package com.navgnss.gankio.util;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ZhuJinWei on 2017/4/21.
 */

public interface GankApi {

    @GET("data/福利/"+"10/"+"{page}")
    Observable<String> getFuLi(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<String> getDaily(@Path("year") int year,@Path("month") int month,@Path("day") int day);




}
