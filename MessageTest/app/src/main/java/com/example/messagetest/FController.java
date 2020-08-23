package com.example.messagetest;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.widget.Toast;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

public class FController {
    /* 自定义ACTION常数，作为广播的Intent Filter识别常数 */
    public static String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
    public static String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
    String strDestAddress;
    String strMessage;
    private Activity activity;

    private FController(Activity context) {
        this.activity = context;
    }

    public static void sendMsg(Activity activity, String strDestAddress, String strMessage) {
        FController fController = new FController(activity);
        fController.connect(strDestAddress, strMessage);
    }

    public void connect(String strDestAddress, String strMessage) {
        this.strDestAddress = strDestAddress;
        this.strMessage = strMessage;
        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            android.support.v4.app.FragmentTransaction transactionV4 = fragmentActivity.getSupportFragmentManager().beginTransaction();
            DefaultSMSV4Fragment defaultV4Fragment = new DefaultSMSV4Fragment();
            defaultV4Fragment.setTarget(this);
            transactionV4.add(defaultV4Fragment, defaultV4Fragment.getClass().getName()).commit();
        } else {
            FragmentManager manager = activity.getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            DefaultSMSFragment fragment = new DefaultSMSFragment();
            fragment.setTarget(this);
            fragmentTransaction.add(fragment, fragment.getClass().getName()).commit();
        }
    }

    public void requestPermission(Activity activity) {
        /* 建立SmsManager对象 */
        final SmsManager smsManager = SmsManager.getDefault();

        /* 建立自定义Action常数的Intent(给PendingIntent参数之用) */
        Intent itSend = new Intent(SMS_SEND_ACTIOIN);
        Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);

        /* sentIntent参数为传送后接受的广播信息PendingIntent */
        final PendingIntent mSendPI = PendingIntent.getBroadcast(activity.getApplicationContext(), 0, itSend, 0);

        /* deliveryIntent参数为送达后接受的广播信息PendingIntent */
        final PendingIntent mDeliverPI = PendingIntent.getBroadcast(activity.getApplicationContext(), 0, itDeliver, 0);
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            /* 发送SMS短信，注意倒数的两个PendingIntent参数 */
                            smsManager.sendTextMessage(strDestAddress, null, strMessage, mSendPI, mDeliverPI);
                            return;
                        }
                        if (permission.shouldShowRequestPermissionRationale) {
                            return;
                        }
                    }
                });
    }

    /* 自定义mServiceReceiver重写BroadcastReceiver监听短信状态信息 */
    public static class MServiceReceiver extends BroadcastReceiver {
        private Activity activity;

        public MServiceReceiver(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FController.SMS_SEND_ACTIOIN)) {
                try {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            mMakeTextToast
                                    (
                                            "发送成功",
                                            true
                                    );
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            /* 发送短信失败 */
                            mMakeTextToast
                                    (
                                            "发送失败",
                                            true
                                    );
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            break;
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (intent.getAction().equals(FController.SMS_DELIVERED_ACTION)) {
                try {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            /* 短信 */
                            mMakeTextToast
                                    (
                                            "接收成功",
                                            true
                                    );
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            /* 短信未送达 */
                            mMakeTextToast
                                    (
                                            "接收失败",
                                            true
                                    );
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            break;
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }

        public void mMakeTextToast(String str, boolean isLong) {
            if (isLong == true) {
                Toast.makeText(activity, str, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
