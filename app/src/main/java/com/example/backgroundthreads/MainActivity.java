package com.example.backgroundthreads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


//https://www.youtube.com/watch?v=IVFWC0rwfL4&t=758s&ab_channel=CodingPursuit
public class MainActivity extends AppCompatActivity {

    TextView  textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("MSG_KEY");
            textView.setText(message);
        }
    };
    public void buttonClicked(View v){
        //Message objMessage = handler.obtainMessage();
        Message objMessage = new Message();
        Bundle objBundle = new Bundle();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                objBundle.putString("MSG_KEY" , " I'm a message from background");
                objMessage.setData(objBundle);
                handler.sendMessage(objMessage);
            }
        };

        Thread objThread = new Thread(runnable );
        objThread.start();
        textView.setText("Button is clicked");
    }
}