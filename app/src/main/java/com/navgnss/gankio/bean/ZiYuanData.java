package com.navgnss.gankio.bean;

import android.support.design.widget.AppBarLayout;

import com.google.gson.annotations.SerializedName;
import com.navgnss.gankio.bean.base.ResultData;

import java.util.List;

/**
 * Created by ZhuJinWei on 2017/8/1.
 */

public class ZiYuanData {

    @SerializedName("error")
    boolean isError;

    @SerializedName("results")
    List<ResultData> resultDatas;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<ResultData> getResultDatas() {
        return resultDatas;
    }

    public void setResultDatas(List<ResultData> resultDatas) {
        this.resultDatas = resultDatas;
    }
}
