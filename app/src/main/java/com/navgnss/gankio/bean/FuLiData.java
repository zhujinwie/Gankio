package com.navgnss.gankio.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.navgnss.gankio.bean.base.ResultData;

import java.util.List;

/**
 * Created by ZhuJinWei on 2017/4/26.
 */

public class FuLiData {


    public List<ResultData> results;

    public List<ResultData> getResults() {
        return results;
    }

    @SerializedName("error")
    @Expose
    public boolean error;

    public boolean isError() {
        return error;
    }

}
