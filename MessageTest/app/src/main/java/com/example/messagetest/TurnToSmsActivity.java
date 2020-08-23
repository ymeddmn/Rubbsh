package com.example.messagetest;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TurnToSmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_to_sms);
        findViewById(R.id.btn_turnpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("10086帮我查一下话费");
//                query();
            }
        });
        getContentResolver().registerContentObserver(Uri.parse
                ("content://sms"), true, new SmsObserver(new Handler()));
    }

    private void sendSMS(String smsBody) {

        Uri smsToUri = Uri.parse("smsto:");

        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

        intent.putExtra("sms_body", smsBody);

        startActivity(intent);

    }

    class SmsObserver extends ContentObserver {

        public SmsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            //查询发送向箱中的短信
            super.onChange(selfChange);
            Log.e(getClass().getSimpleName(),"想发件箱中插入短信");
        }

    }

    private void query() {
        Cursor cursor = getContentResolver().query(Uri.parse(
                "content://sms/outbox"), null, null, null, null);
        //遍历查询结果获取用户正在发送的短信
        while (cursor.moveToNext()) {
            StringBuffer sb = new StringBuffer();
            //获取短信的发送地址
            sb.append("发送地址：" + cursor.getString(cursor.getColumnIndex("address")));
            //获取短信的标题
            sb.append("\n标题：" + cursor.getString(cursor.getColumnIndex("subject")));
            //获取短信的内容
            sb.append("\n内容：" + cursor.getString(cursor.getColumnIndex("body")));
            //获取短信的发送时间
            Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
            //格式化以秒为单位的日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
            sb.append("\n时间：" + sdf.format(date));
            System.out.println("查询到的正在发送的短信：" + sb.toString());
//                Toast.makeText(MonitorSms.this, sb.toString(), Toast.LENGTH_LONG).show();
//                txtView.setText(sb.toString());
        }
    }


}
