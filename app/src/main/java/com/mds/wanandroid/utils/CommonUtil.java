package com.mds.wanandroid.utils;

import android.widget.Toast;

import com.mds.wanandroid.common.APP;


/***
 * toast
 */
public class CommonUtil {
    private static Toast toast;

    public static void showToast(
            String content) {
        if (toast == null) {
            toast = Toast.makeText(APP.getContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
