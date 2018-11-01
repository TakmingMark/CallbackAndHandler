package com.example.markwang.callbackandhandler;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        mHandler=new MyHandler(this);

    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netwrokdDownloadService.startDownload();
            }
        });

        netwrokdDownloadService.setNetworkDownloadServiceListener(new NetworkDownloadServiceListener() {
            Message message = new Message();
            Bundle bundle=new Bundle();
            @Override
            public void onComplete(String str) {
                message.what = 1;
                bundle.putString("text",str);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }

            @Override
            public void onFail(String str) {
                message.what = 0;
                bundle.putString("text",str);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        });
    }

    public void updateTextViewText(String str) {
        textView.setText(str);
    }
}
