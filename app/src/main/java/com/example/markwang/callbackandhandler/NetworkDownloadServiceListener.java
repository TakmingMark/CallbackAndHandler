package com.example.markwang.callbackandhandler;

public interface NetworkDownloadServiceListener {
   void onComplete(String str);
   void onFail(String str);
}
