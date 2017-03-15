package com.p2pinvest1020.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.p2pinvest1020.command.AppNetConfig;

import java.util.Map;

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

    public static void getDataPost(String url, Map<String,String> map, final LoadNetHttp http) {

        AsyncHttpClient httpClient = new AsyncHttpClient();

        if (map != null && !map.isEmpty()){
            RequestParams params = new RequestParams();

            //map.keySet()返回的是所有key的值
            for (String key : map.keySet()) {
                //得到每个key多对用value的值
                String value = map.get(key);
                params.put(key,value);
            }

            httpClient.post(url,params,new AsyncHttpResponseHandler() {
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
        }else{
            getDataPost(url,http);
        }



    }


}
