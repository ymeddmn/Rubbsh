package com.qr.demo;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import com.qr.demo.vo.PrintUtil;
import com.qr.demo.vo.Waybill;
import com.qr.print.*;


//import printpp.printpp_yt.PrintPP_CPCL;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private PrintPP_CPCL printPP_cpcl;
    private static final boolean D = true;
    private boolean isConnected = false;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private String address = "";
    private String name = "";
    private BluetoothAdapter mBluetoothAdapter = null;
    // Layout Views
    private TextView mTitle;
    private Button mSendButton, mZtoSendButton, mKbztoSendButton, mStoSendButton,mBeescmButton;
    private EditText sampleEdit;
    private EditText intervalsEdit;
    private int sampleNumber;
    private int interval;
    private boolean isSending = false;
    private ImageButton searchButton;

    public static final String Author = "zhougf(edivista@vip.qq.com)" +
            "微信：edivista" +
            "QQ: 77175792";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);

        mTitle = (TextView) findViewById(R.id.title_left_text);
        mTitle.setText(R.string.app_name);
        mTitle = (TextView) findViewById(R.id.title_right_text);

        sampleEdit = (EditText) findViewById(R.id.sampleEdit);
        sampleEdit.setText("1");

        //obtain the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);

        printPP_cpcl = new PrintPP_CPCL();

        searchButton = (ImageButton) findViewById(R.id.search);
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
            }
        });


        mKbztoSendButton = (Button) findViewById(R.id.button_kbztosend);

        mKbztoSendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    KBPrinter kbp = new KBPrinter(printPP_cpcl);
                                    kbp.printZTContnet();
                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });

        mStoSendButton = (Button) findViewById(R.id.button_stosend);

        mStoSendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    KBPrinter kbp = new KBPrinter(printPP_cpcl);
                                    kbp.printStoContent();
                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });

        mZtoSendButton = (Button) findViewById(R.id.button_ztosend);

        mZtoSendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    ZTOPrintLabel zto = new ZTOPrintLabel(printPP_cpcl);
                                    zto.doPrint();
                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });
        findViewById(R.id.btn_beescm_waybill).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    BeescmPrint pl = new BeescmPrint();
                                    Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo_small);
//                                    Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.mipmap.location);
                                    pl.printWaybill(printPP_cpcl, PrintUtil.getWaybillSimple(),bmp);
//                                    Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cainiao_logo);
//                                    pl.label2(printPP_cpcl, rawBitmap);

                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });
        mBeescmButton = findViewById(R.id.btn_beescm);
        mBeescmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    BeescmPrint pl = new BeescmPrint();
                                    Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
                                    Bitmap location= BitmapFactory.decodeResource(getResources(), R.mipmap.location);
                                    pl.transportTicket(printPP_cpcl,PrintUtil.getTicketSimple(),bmp,location);
//                                    Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cainiao_logo);
//                                    pl.label2(printPP_cpcl, rawBitmap);

                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });
        mSendButton = (Button) findViewById(R.id.button_send);
        mSendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sampleEdit.getText().toString().trim().equals("")) {
                    sampleNumber = 1;
                } else {
                    sampleNumber = Integer.valueOf(sampleEdit.getText().toString().trim());
                }

                if (!isSending) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < sampleNumber; i++) {
                                Log.e(TAG, String.valueOf(i));
                                isSending = true;
                                if (isConnected) {
                                    YTPrintLabel pl = new YTPrintLabel();
                                	pl.Lable(printPP_cpcl);
//                                    Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cainiao_logo);
//                                    pl.label2(printPP_cpcl, rawBitmap);

                                }

                                try {
                                    interval = 0;
                                    Thread.sleep(interval);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (i == (sampleNumber - 1)) {
                                    isSending = false;
                                }

                            }
                        }
                    }).start();
                }
            }
        });

    }




    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled
        // setupChat() will then be called during onActivityRe//sultsetupChat
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D) {
            Log.d(TAG, "onActivityResult " + resultCode);
        }

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    if (isConnected & (printPP_cpcl != null)) {
                        printPP_cpcl.disconnect();
                        isConnected = false;
                    }
                    String sdata = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    address = sdata.substring(sdata.length() - 17);
                    name = sdata.substring(0, (sdata.length() - 17));
                    if (!isConnected) {
                        // if (printPP_cpcl.connect("QR-380A", "00:0C:BF:0A:E0:2D")) {
                        //	 if (printPP_cpcl.connect("QR-380A", "8C:DE:52:F3:1A:03")) {
                        // if (printPP_cpcl.connect(name, address)) {
                        // if (printPP_cpcl.connect("QR-386A", "DC:1D:30:00:02:49")) {
                        if (printPP_cpcl.connect(name, address)) {
                            isConnected = true;
                            mTitle.setText(R.string.title_connected_to);
                            mTitle.append(name);

                        } else {

                            isConnected = false;

                        }

                    }

                }
                break;
            case REQUEST_ENABLE_BT:

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan:
                Intent serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                return true;
            case R.id.discoverable:
                ensureDiscoverable();
                return true;
        }
        return false;
    }


    private void ensureDiscoverable() {
        if (D) {
            Log.d(TAG, "ensure discoverable");
        }
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    public void connect(View view) {
        if (!isConnected) {
            // if (printPP_cpcl.connect("QR-380A", "00:0C:BF:0A:E0:2D")) {
            //	 if (printPP_cpcl.connect("QR-380A", "8C:DE:52:F3:1A:03")) {
            if (printPP_cpcl.connect("QR-386A", address)) {
                // if (printPP_cpcl.connect("QR-386A", "DC:1D:30:00:02:49")) {

                isConnected = true;

            } else {

                isConnected = false;

            }

        }
    }

    public void testPrinter(View view) {
        if (isConnected) {
            YTPrintLabel pl = new YTPrintLabel();
            pl.Lable(printPP_cpcl);
            printPP_cpcl.disconnect();
            isConnected = false;


//            //宽度、高度自行设置
//            String status = printPP_cpcl.printerStatus();
//            Log.e(TAG,"the status is "+ status);
//            printPP_cpcl.pageSetup(576, 570);
//            //画边框
//            printPP_cpcl.drawBox(1, 0, 0, 150, 20);
//
//            //画线
//            printPP_cpcl.drawLine(1, 0, 30, 100, 30, true);
//
//            //画虚线
//            printPP_cpcl.drawLine(1, 0, 40, 100, 40, false);
//
//
//            //画文字
//            printPP_cpcl.drawText(0, 110, "菜鸟测试", 2,
//                    0, 0, false, false);
//
//            //画条形码
//            printPP_cpcl.drawBarCode( 0, 50,"123456789", 1, 0, 2, 50);
//
//            //画二维码
//            printPP_cpcl.drawQrCode( 0, 160, "菜鸟测试",2, 6, 2);
//
//            //画图片
//            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
//
//            printPP_cpcl.drawGraphic(0,300,bitmap1.getWidth(), bitmap1.getHeight(),bitmap1);
//
//            printPP_cpcl.print(0,0);
//            printPP_cpcl.feed();

        }


    }

    public void disconnect(View view) {
        isConnected = false;
        printPP_cpcl.disconnect();
    }

}


