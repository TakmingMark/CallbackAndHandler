package com.example.markwang.callbackandhandler;


import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class MyHandler extends Handler {
    MainActivity mainActivity;
    public MyHandler(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    public void handleMessage(Message msg) {
        String  str="";
        if(msg.what==0){
            str=msg.getData().getString("text");
        }
        else if(msg.what==1){
            str=msg.getData().getString("text");
        }
        mainActivity.updateTextViewText( str);
    }
}
