package com.p2pinvest1020.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.p2pinvest1020.command.AppNetConfig;

/**
 * Created by Administrator on 2017/3/11.
 */

public class LoadNet {

    public static void getDataPost(String url, final LoadNetHttp http) {

        AsyncHttpClient httpClient = new AsyncHttpClient();

        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);

                if (http != null) {
                    http.success(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (http != null) {
                    http.failure(content);
                }

            }
        });
    }


}
