package com.navgnss.gankio.bean;

import com.google.gson.annotations.SerializedName;
import com.navgnss.gankio.bean.base.ResultData;

import java.util.List;

/**
 * Created by ZhuJinWei on 2017/8/1.
 */

public class GankData {

    @SerializedName("error")
    boolean isError;

    List<ResultData> resultDataList;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<ResultData> getResultDataList() {
        return resultDataList;
    }

    public void setResultDataList(List<ResultData> resultDataList) {
        this.resultDataList = resultDataList;
    }
}
