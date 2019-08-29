package com.mds.wanandroid.ui.information.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/14 11:18
 */
public class MyJobIntentService extends JobIntentService {

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyJobIntentService.class, 123, work);
    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }
}
