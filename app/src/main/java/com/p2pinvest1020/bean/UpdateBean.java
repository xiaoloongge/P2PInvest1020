package com.p2pinvest1020.bean;

/**
 * Created by Administrator on 2017/3/18.
 */

public class UpdateBean {
    /**
     * version : 1.2
     * apkUrl : http://192.168.191.1:8080/P2PInvest/app_new.apk
     * desc : 解决一些bug, 优化网络请求!
     */

    private String version;
    private String apkUrl;
    private String desc;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
