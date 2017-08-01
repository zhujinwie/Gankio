package com.navgnss.gankio.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhuJinWei on 2017/4/27.
 */

public class RobotData {
    @SerializedName("error_code")
    @Expose
    public int error_code; //错误码
    @SerializedName("reason")
    @Expose
    public String reason; //错误原因
    @SerializedName("result")
    @Expose
    public Result result; //返回查询结果

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
       @SerializedName("code")
       @Expose
       public int code;

       @SerializedName("text")
       @Expose
       public String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
