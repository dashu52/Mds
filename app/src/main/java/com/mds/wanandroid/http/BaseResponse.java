package com.mds.wanandroid.http;

import java.io.Serializable;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 20:36
 */
public class BaseResponse<T> implements Serializable {

    private static int SUCCESS_CODE = 0;//成功的code

    private int errorCode;
    public String errorMsg;
    public T data;
    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
    }

    public int getCode() {
        return errorCode;
    }

    public void setCode(int code) {
        this.errorCode = code;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
