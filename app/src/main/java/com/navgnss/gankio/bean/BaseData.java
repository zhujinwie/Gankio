package com.navgnss.gankio.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhuJinWei on 2017/4/26.
 */

public class BaseData {
    @SerializedName("error")
    @Expose
    public boolean error;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
