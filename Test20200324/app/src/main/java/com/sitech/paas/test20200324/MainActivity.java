package com.sitech.paas.test20200324;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        System.out.println("运营商编码：" + getOperatorCode(this));
        System.out.println("运营商名称：" + getOperatorName(this));
        tv= findViewById(R.id.tv);
        tv.setText("编码："+getOperatorCode(this)+"     名称："+getOperatorName(this)+"    getNetworkOperator:"+getOperatorCode1(this));
    }
    public static String getOperatorName(Context context) {
        /*
         * getSimOperatorName()就可以直接获取到 运营商的名字
         * 也可以使用IMSI获取，getSimOperator()，然后根据返回值判断，例如"46000"为移动
         * IMSI相关链接：http://baike.baidu.com/item/imsi
         */
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // getSimOperatorName                               就可以直接获取到运营商的名字

        if (TextUtils.isEmpty(telephonyManager.getSimOperatorName())) {
            return "-1";
        }
        return telephonyManager.getSimOperatorName();
    }

    public static String getOperatorCode(Context context) {
        /*
         * getSimOperatorName()就可以直接获取到 运营商的名字
         * 也可以使用IMSI获取，getSimOperator()，然后根据返回值判断，例如"46000"为移动
         * IMSI相关链接：http://baike.baidu.com/item/imsi
         */
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimOperator();
    }

    public static String getOperatorCode1(Context context) {
        /*
         * getSimOperatorName()就可以直接获取到 运营商的名字
         * 也可以使用IMSI获取，getSimOperator()，然后根据返回值判断，例如"46000"为移动
         * IMSI相关链接：http://baike.baidu.com/item/imsi
         */
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getNetworkOperator();
    }

//    public void getType(){
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        int type = telephonyManager.getPhoneType();
//        if (type == TelephonyManager.PHONE_TYPE_CDMA) {
//            System.out.println("telephonyManager.getPhoneType() === PHONE_TYPE_CDMA , " + type);
//        } else if (type == TelephonyManager.PHONE_TYPE_GSM) {
//            System.out.println("telephonyManager.getPhoneType() === PHONE_TYPE_GSM , " + type);
//        } else if (type == TelephonyManager.PHONE_TYPE_SIP) {
//            System.out.println("telephonyManager.getPhoneType() === PHONE_TYPE_SIP , " + type);
//        } else if (type == TelephonyManager.PHONE_TYPE_NONE) {
//            System.out.println("telephonyManager.getPhoneType() === PHONE_TYPE_NONE , " + type);
//        }
//        int simState = telephonyManager.getSimState();
//        if (simState == TelephonyManager.SIM_STATE_UNKNOWN) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_UNKNOWN , " + simState);
//        } else if (simState == TelephonyManager.SIM_STATE_ABSENT) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_ABSENT , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_PIN_REQUIRED) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_PIN_REQUIRED , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_PUK_REQUIRED) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_PUK_REQUIRED , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_NETWORK_LOCKED) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_NETWORK_LOCKED , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_READY) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_READY , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_NOT_READY) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_NOT_READY , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_PERM_DISABLED) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_PERM_DISABLED , " + simState);
//
//        }
//        if (simState == TelephonyManager.SIM_STATE_CARD_IO_ERROR) {
//            System.out.println("telephonyManager.getSimState() === SIM_STATE_CARD_
//    }}
}
