package com.mds.wanandroid.http;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.mds.wanandroid.utils.CommonUtil;
import com.mds.wanandroid.utils.MyLogger;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 20:48
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private Context mContext;


    //对应HTTP的状态码
    private static final int UNAU = 402;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    //出错提示
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> tBaseEntity) {
        MyLogger.dLog().e("tBaseEntity="+tBaseEntity);
        if (tBaseEntity.isSuccess()) {
            try {


                onSuccees(tBaseEntity.data);
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            try {
                MyLogger.dLog().d("3333333333333333333    "+tBaseEntity.getCode()+"   "+tBaseEntity.getMsg());
                if (tBaseEntity.getCode()==-1001){
                    MyLogger.dLog().d("777777777777777777777777777");
                    startLogin();

                }
                onCodeError(tBaseEntity.getCode());


                onError(new Throwable(tBaseEntity.getMsg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {

        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        String error = null;
        if (e instanceof ConnectException) {
            MyLogger.dLog().d( "ConnectException");
            error = connectMsg;
        } else if (e instanceof HttpException) {             //HTTP错误
            MyLogger.dLog().d( "HttpException");
            HttpException httpException = (HttpException) e;
            error=e.getLocalizedMessage()+"";
        } else if (e instanceof ApiException) {    //服务器返回的错误
            ApiException apiException = (ApiException) e;
            switch (apiException.getErrorCode()) {


                case "10007":
                    error = parseMsg;
                    break;
                case "10008":
                    error = cookieOutMsg;
                    //       IntentUtil.showLoginActivity(AppActivityManager.getInstance().getTopActivity());
                    break;
                case "11111":
                    error = cookieOutMsg;
                    //       IntentUtil.showLoginActivity(AppActivityManager.getInstance().getTopActivity());
                    break;
                default:
                    error = e.getLocalizedMessage();
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            MyLogger.dLog().d( "JsonParseException JSONException");
            error = parseMsg;
        } else if (e instanceof IOException) {
            MyLogger.dLog().d( "IOException");
            if (e instanceof SocketTimeoutException) {
                MyLogger.dLog().d( "SocketTimeoutException");
                error = connectOutMsg;
            } else {
                if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage()))
                    return;
                else
                    error = connectMsg;
            }
        } else {
            error = e.getLocalizedMessage();
        }

        try {
            CommonUtil.showToast(error);
            onFailure(error,false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        MyLogger.dLog().e("--------onComplete");
    }

    /**
     * 返回失败
     *
     * @param error
     * @param isNetWorkError 是否是网络错误
     */
    protected abstract void onFailure(String error, boolean isNetWorkError) throws Exception;
    public  static  void startLogin(){
    }

    /**
     * 返回成功了,但是code错误
     *
     * @param error
     */
    protected void onCodeError(int error) { }

    /**
     * 返回成功
     *
     * @param t
     */
    protected abstract void onSuccees(T t) throws Exception;
}
