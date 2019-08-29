package com.mds.wanandroid.http;


import com.mds.wanandroid.utils.MyLogger;
import com.mds.wanandroid.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            //解析Cookie
            for (String header : originalResponse.headers("Set-Cookie")) {

                stringBuilder.append(header);


                if(header.contains("JSESSIONID")){

                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
                    SpUtils.SetConfigString("cookie", cookie);
                    MyLogger.dLog().d("cookie---qzs   "+cookie);


                }
            }
            MyLogger.dLog().d("cookie全部-----   " + stringBuilder.toString());
        }

        return originalResponse;

    }
}
