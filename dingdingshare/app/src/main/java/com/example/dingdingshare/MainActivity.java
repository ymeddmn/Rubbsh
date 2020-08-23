package com.example.dingdingshare;

import com.android.dingtalk.share.ddsharemodule.DDShareApiFactory;
import com.android.dingtalk.share.ddsharemodule.IDDShareApi;
import com.android.dingtalk.share.ddsharemodule.message.DDImageMessage;
import com.android.dingtalk.share.ddsharemodule.message.DDMediaMessage;
import com.android.dingtalk.share.ddsharemodule.message.DDTextMessage;
import com.android.dingtalk.share.ddsharemodule.message.DDWebpageMessage;
import com.android.dingtalk.share.ddsharemodule.message.DDZhiFuBaoMesseage;
import com.android.dingtalk.share.ddsharemodule.message.SendAuth;
import com.android.dingtalk.share.ddsharemodule.message.SendMessageToDD;
import com.android.dingtalk.share.ddsharemodule.plugin.SignatureCheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.File;

/**
 * Created by hanhanliu on 15/6/11.
 */
public class MainActivity extends Activity {
    private IDDShareApi iddShareApi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Log.e("MainActivity","taskId:"+getTaskId());

        findViewById(R.id.ding_install_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInstalled = iddShareApi.isDDAppInstalled();
                Toast.makeText(MainActivity.this, "是否安装===》" + isInstalled, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.share_support_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSupport = iddShareApi.isDDSupportAPI();
                Toast.makeText(MainActivity.this ,"是否支持分享到好友===》"+isSupport ,Toast.LENGTH_SHORT ).show();
            }
        });

        findViewById(R.id.share_ding_support_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSupport = iddShareApi.isDDSupportDingAPI();
                Toast.makeText(MainActivity.this ,"是否支持分享到Ding===》"+isSupport ,Toast.LENGTH_SHORT ).show();
            }
        });

        findViewById(R.id.auth_support_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendAuth.Req req = new SendAuth.Req();
                boolean isSupport = req.getSupportVersion() <= iddShareApi.getDDSupportAPI();
                Toast.makeText(MainActivity.this ,"是否支持登录授权===》"+isSupport ,Toast.LENGTH_SHORT ).show();
            }
        });

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage(false);
            }
        });

        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendOnlineImage();
                showImageShareDialog(false);
            }
        });


        findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWebPageMessage(false);
            }
        });

        findViewById(R.id.auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAuth();
            }
        });

        findViewById(R.id.zfb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendZFBMessage(false);
            }
        });

        findViewById(R.id.ding_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage(true);
            }
        });

        findViewById(R.id.ding_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendOnlineImage();
                showImageShareDialog(true);
            }
        });


        findViewById(R.id.ding_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWebPageMessage(true);
            }
        });

        findViewById(R.id.ding_zfb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendZFBMessage(true);
            }
        });

        //todo: Constant.APP_ID 需要更新为用户测试的app_id
        iddShareApi = DDShareApiFactory.createDDShareApi(this, Constant.APP_ID, true);

        Log.d("lzc" , "pkg name=====>"+this.getPackageName());

    }

    private void sendAuth() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = SendAuth.Req.SNS_LOGIN;
        req.state = "test";
        if(req.getSupportVersion() > iddShareApi.getDDSupportAPI()){
            Toast.makeText(this, "钉钉版本过低，不支持登录授权", Toast.LENGTH_SHORT).show();
            return;
        }
        iddShareApi.sendReq(req);
    }

    private void sendTextMessage(boolean isSendDing) {

        String text = getString(R.string.share_text_default);

        //初始化一个DDTextMessage对象
        DDTextMessage textObject = new DDTextMessage();
        textObject.mText = text;

        //用DDTextMessage对象初始化一个DDMediaMessage对象
        DDMediaMessage mediaMessage = new DDMediaMessage();
        mediaMessage.mMediaObject = textObject;

        //构造一个Req
        SendMessageToDD.Req req = new SendMessageToDD.Req();
        req.mMediaMessage = mediaMessage;

        //调用api接口发送消息到钉钉
        if(isSendDing){

            iddShareApi.sendReqToDing(req);
        } else {
            iddShareApi.sendReq(req);
        }

//        checkSumConsistent("com.alibaba.android.rimet");
    }

    private static final String ONLINE_PACKAGE_NAME = "com.edus.test.ding.share.demo";//todo:将值替换为在钉钉开放平台上申请时的packageName
    private static final String ONLINE_APP_ID = "123445";//todo:将值替换为在钉钉开放平台上申请时平台生成的appId
    private static final String ONLINE_SIGNATURE = "334566";//todo:将值替换为在钉钉开放平台上申请时的signature


    private static final String CURRENT_USING_APP_ID = "123445";//todo:将值替换为你使用的APP_ID

    /**
     * 校验分享到钉钉的参数是否有效
     * @return
     */
    private boolean checkShareToDingDingValid() {
        if(!TextUtils.equals(ONLINE_PACKAGE_NAME, getPackageName())){
            Toast.makeText(this, "包名与线上申请的不匹配", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!TextUtils.equals(ONLINE_APP_ID, CURRENT_USING_APP_ID)){
            Toast.makeText(this, "APP_ID 与生成的不匹配", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!TextUtils.equals(ONLINE_SIGNATURE, SignatureCheck.getMD5Signature(this, getPackageName()))){
            Toast.makeText(this, "签名与线上生成的不符", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 分享网页消息
     */
    private void sendWebPageMessage(boolean isSendDing) {
        //初始化一个DDWebpageMessage并填充网页链接地址
        DDWebpageMessage webPageObject = new DDWebpageMessage();
        webPageObject.mUrl = "http://www.baidu.com";

        //构造一个DDMediaMessage对象
        DDMediaMessage webMessage = new DDMediaMessage();
        webMessage.mMediaObject = webPageObject;

        //填充网页分享必需参数，开发者需按照自己的数据进行填充
        webMessage.mTitle = "title";
        webMessage.mContent = null;
//        webMessage.mThumbUrl = "https://t.alipayobjects.com/images/rmsweb/T1vs0gXXhlXXXXXXXX.jpg";
//        webMessage.mThumbUrl = "http://ww2.sinaimg.cn/large/85cccab3gw1etdkm64h7mg20dw07tb29.gif";
        webMessage.mThumbUrl = "http://img.qdaily.com/uploads/20160606152752iqaH5t4KMvn18BZo.gif";
        webMessage.mThumbUrl = "http://static.dingtalk.com/media/lAHPBY0V4shLSVDMlszw_240_150.gif";
        // 网页分享的缩略图也可以使用bitmap形式传输
//         webMessage.setThumbImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        //构造一个Req
        SendMessageToDD.Req webReq = new SendMessageToDD.Req();
        webReq.mMediaMessage = webMessage;
//        webReq.transaction = buildTransaction("webpage");

        //调用api接口发送消息到支付宝
        if(isSendDing){
            iddShareApi.sendReqToDing(webReq);
        } else {

            iddShareApi.sendReq(webReq);
        }
//        finish();

    }

    /**
     * 通过二进制流方式分享图片消息
     */
    private void sendByteImage(boolean isSendDing) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

      /*  //初始化一个DDImageMessage
        byte[] bytes = new byte[2];
        bytes[0] = 1;
        bytes[1] = 2;*/
        DDImageMessage imageObject = new DDImageMessage(bmp);
        if (bmp != null)
            bmp.recycle();

        //构造一个DDMediaMessage对象
        DDMediaMessage mediaMessage = new DDMediaMessage();
        mediaMessage.mMediaObject = imageObject;

        //构造一个Req
        SendMessageToDD.Req req = new SendMessageToDD.Req();
        req.mMediaMessage = mediaMessage;
//        req.transaction = buildTransaction("image");

        //调用api接口发送消息到支付宝
        if(isSendDing){
            iddShareApi.sendReqToDing(req);
        } else {

            iddShareApi.sendReq(req);
        }
    }

    /**
     * 通过图片链接方式分享图片消息
     */
    private void sendOnlineImage(boolean isSendDing) {
        //图片Url，开发者需要根据自身数据替换该数据
//        String picUrl = "http://upfile.asqql.com/2009pasdfasdfic2009s305985-ts/2017-12/201712617475697622.gif";
//        String picUrl = "http://img.zcool.cn/community/010a1b554c01d1000001bf72a68b37.jpg@1280w_1l_2o_100sh.png";
        String picUrl = "https://img-download.pchome.net/download/1k1/ut/5a/ouzdgm-1dzc.jpg";
//        String picUrl = "http://img.qdaily.com/uploads/20160606152752iqaH5t4KMvn18BZo.gif-WebpWebW640";
//        String picUrl = "http://img.qdaily.com/uploads/20160606152752iqaH5t4KMvn18BZo.gif";
//        String picUrl = "http://static.dingtalk.com/media/lAHPBY0V4shLSVDMlszw_240_150.gif";

        //初始化一个DDImageMessage
        DDImageMessage imageObject = new DDImageMessage();
        imageObject.mImageUrl = picUrl;

        //构造一个mMediaObject对象
        DDMediaMessage mediaMessage = new DDMediaMessage();
        mediaMessage.mMediaObject = imageObject;

        //构造一个Req
        SendMessageToDD.Req req = new SendMessageToDD.Req();
        req.mMediaMessage = mediaMessage;
//        req.transaction = buildTransaction("image");

        //调用api接口发送消息到支付宝
        if(isSendDing){
            iddShareApi.sendReqToDing(req);
        } else {

            iddShareApi.sendReq(req);
        }
    }

    /**
     * 通过图片本地路径方式分享图片消息
     */
    private void sendLocalImage(boolean isSendDing) {
        //图片本地路径，开发者需要根据自身数据替换该数据
//        String path =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.png";
        String path =  "/storage/emulated/0/Android/data/com.alibaba.android.rimet/cache/outter_share/4AEC3689513A9FA35177B83009490937.jpg";

        File file = new File(path);
        if (!file.exists()) {
            String tip = "no pic";
            Toast.makeText(this, tip + " path = " + path, Toast.LENGTH_LONG).show();
            return;
        }

        //初始化一个DDImageMessage
        DDImageMessage imageObject = new DDImageMessage();
        imageObject.mImagePath = path;

        //构造一个mMediaObject对象
        DDMediaMessage mediaMessage = new DDMediaMessage();
        mediaMessage.mMediaObject = imageObject;

        //构造一个Req
        SendMessageToDD.Req req = new SendMessageToDD.Req();
        req.mMediaMessage = mediaMessage;
//        req.transaction = buildTransaction("image");

        //调用api接口发送消息到支付宝
        if(isSendDing){
            iddShareApi.sendReqToDing(req);
        } else {

            iddShareApi.sendReq(req);
        }
    }

    private void sendZFBMessage(boolean isSendDing){
        //初始化一个DDZhiFuBaoMesseage
        DDZhiFuBaoMesseage webPageObject = new DDZhiFuBaoMesseage();
        webPageObject.mUrl = "http://www.baidu.com";

        //构造一个DDMediaMessage对象
        DDMediaMessage webMessage = new DDMediaMessage();
        webMessage.mMediaObject = webPageObject;

        //填充网页分享必需参数，开发者需按照自己的数据进行填充
        webMessage.mTitle = "支付宝红包标题这是一个很长很长的标题";
        webMessage.mContent = "支付宝红包内容描述";
        webMessage.mThumbUrl = "https://t.alipayobjects.com/images/rmsweb/T1vs0gXXhlXXXXXXXX.jpg";
        // 网页分享的缩略图也可以使用bitmap形式传输
        // webMessage.setThumbImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        //构造一个Req
        SendMessageToDD.Req webReq = new SendMessageToDD.Req();
        webReq.mMediaMessage = webMessage;
//        webReq.transaction = buildTransaction("webpage");

        //调用api接口发送消息到支付宝
        if(isSendDing){
            iddShareApi.sendReqToDing(webReq);
        } else {

            iddShareApi.sendReq(webReq);
        }
    }

    /**
     * 选择分享图片格式
     */
    private void showImageShareDialog(final boolean isSendDing) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_share_image, null);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view).create();
        dialog.show();
        //图片本地路径分享
        view.findViewById(R.id.image_local_path).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendLocalImage(isSendDing);
            }
        });

        //图片二进制流分享
        view.findViewById(R.id.image_byte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendByteImage(isSendDing);
            }
        });

        //图片url分享
        view.findViewById(R.id.image_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendOnlineImage(isSendDing);
            }
        });
    }
}
