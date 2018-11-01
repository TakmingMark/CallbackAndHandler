package com.example.markwang.callbackandhandler;

import android.os.Looper;
import android.util.Log;

import java.util.jar.JarEntry;

public class NetwrokdDownloadService {
    private NetworkDownloadServiceListener networkDownloadServiceListener;

    public  NetwrokdDownloadService(){

    }

    public void setNetworkDownloadServiceListener(NetworkDownloadServiceListener networkDownloadServiceListener) {
        this.networkDownloadServiceListener = networkDownloadServiceListener;
    }

    public void startDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    networkDownloadServiceListener.onComplete("download finish");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
