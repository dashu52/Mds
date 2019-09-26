package com.mds.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mds.wanandroid.common.APP;
import com.mds.wanandroid.ui.information.activity.ArticleActivity;


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

    public  static  void  startArticleDetail(Context context, String title, String url){

        Intent intent =new Intent(context, ArticleActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);

    }

}
