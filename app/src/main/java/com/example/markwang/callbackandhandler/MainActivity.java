package com.example.markwang.callbackandhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * callback是程序员们写代码的一种艺术，handler是android消息通信机制，可以做线程切换
 *
 * 在下载完成时，可能需要做一些其它事情，如保存数据库，这个不是操作UI且耗时，所以在callback中进行
 *
 */


public class MainActivity extends AppCompatActivity implements  NetworkDownloadServiceListener {

    TextView textView;
    Button button;
    NetwrokdDownloadService netwrokdDownloadService;
    MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initObject();
        initListener();
    }

    private void initObject() {
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        netwrokdDownloadService = new NetwrokdDownloadService();
        mHandler=new MyHandler(MainActivity.this);

    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick",Thread.currentThread().getId()+"");

                /**
                 * 如果netwrokdDownloadService有多种情况需要回调，就要用原来的写法
                 * netwrokdDownloadService有若干个方法都有回调，就不要一个个方法里面传个Cabllback
                 */
                netwrokdDownloadService.startDownload(MainActivity.this);
            }
        });
    }

    public void updateTextViewText(String str) {
        textView.setText(str);
    }


    /**
     * avoid out of memory
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
    @Override
    public void onComplete(String str) {
        Message message = new Message();
        Bundle bundle=new Bundle();
        message.what = 1;
        bundle.putString("text",str);
        message.setData(bundle);
        mHandler.sendMessage(message);
        Log.e("onComplete",Thread.currentThread().getId()+"");
    }

    @Override
    public void onFail(String str) {
        Message message = new Message();
        Bundle bundle=new Bundle();
        message.what = 0;
        bundle.putString("text",str);
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    /**
     * avoid out of memory
     */

    private static class MyHandler extends Handler{
        private final WeakReference<MainActivity> weakActivity;

        public MyHandler(MainActivity mainActivity){
            weakActivity=new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity mainActivity=weakActivity.get();
            if(mainActivity!=null){
                String  str="";
                if(msg.what==0){
                    str=msg.getData().getString("text");
                }
                else if(msg.what==1){
                    str=msg.getData().getString("text");
                }
                mainActivity.updateTextViewText( str);
            }
            Log.e("handleMessage",Thread.currentThread().getId()+"");
        }
    }
}
