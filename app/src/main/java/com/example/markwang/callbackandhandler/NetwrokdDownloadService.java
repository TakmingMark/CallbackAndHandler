package com.example.markwang.callbackandhandler;

import android.os.Looper;
import android.util.Log;

import java.util.jar.JarEntry;

public class NetwrokdDownloadService {

    public void startDownload(final NetworkDownloadServiceListener callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.e("startDownload",Thread.currentThread().getId()+"");
                    Thread.sleep(2000);
                    callback.onComplete("download finish");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
