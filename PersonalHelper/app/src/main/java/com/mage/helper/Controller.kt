package com.mage.helper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Message
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy
import com.baidu.tts.client.SpeechSynthesizer

/**
 * author  :mayong
 * function:
 * date    :2020-05-02
 **/
class Controller {
    private lateinit var listener: UiMessageListener
    private val ttsMode = IOfflineResourceConst.DEFAULT_SDK_TTS_MODE
    var mSpeechSynthesizer: SpeechSynthesizer? = null
    var mainHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.obj != null) {
                print(msg.obj.toString());
            }
        }
    }

     fun initTTs(context: Context) :SpeechSynthesizer?{
        LoggerProxy.printable(true) // 日志打印在logcat中
        val isSuccess: Boolean

        // 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance()
        mSpeechSynthesizer?.setContext(context)
        listener = UiMessageListener(mainHandler)
        // 2. 设置listener
        mSpeechSynthesizer?.setSpeechSynthesizerListener(listener);

        // 3. 设置appId，appKey.secretKey
        var result = mSpeechSynthesizer?.setAppId("19697379")
        result = mSpeechSynthesizer?.setApiKey(
            "aM4CUfFg8LM7yDTxLLryKf6t",
            "jcVDvXwvveRXySDKXVpxvsqs3QgQEDVP"
        )

        // 5. 以下setParam 参数选填。不填写则默认值生效
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer?.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0")
        // 设置合成的音量，0-15 ，默认 5
        mSpeechSynthesizer?.setParam(SpeechSynthesizer.PARAM_VOLUME, "9")
        // 设置合成的语速，0-15 ，默认 5
        mSpeechSynthesizer?.setParam(SpeechSynthesizer.PARAM_SPEED, "5")
        // 设置合成的语调，0-15 ，默认 5
        mSpeechSynthesizer?.setParam(SpeechSynthesizer.PARAM_PITCH, "5")

        // mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL); // 调整音频输出


        // x. 额外 ： 自动so文件是否复制正确及上面设置的参数
        val params = HashMap<String, String>()
        // 复制下上面的 mSpeechSynthesizer.setParam参数
        // 上线时请删除AutoCheck的调用
//        if (!isOnlineSDK) {
//            params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME)
//            params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME)
//        }

        // 检测参数，通过一次后可以去除，出问题再打开debug
        val initConfig = InitConfig(
            "19697379",
            "aM4CUfFg8LM7yDTxLLryKf6t",
            "jcVDvXwvveRXySDKXVpxvsqs3QgQEDVP",
            ttsMode,
            params,
            listener
        )
        AutoCheck.getInstance(context.applicationContext).check(initConfig, object : Handler() {
            /**
             * 开新线程检查，成功后回调
             */

            override fun handleMessage(msg: Message) {
                if (msg.what === 100) {
                    val autoCheck = msg.obj as AutoCheck
                    synchronized(autoCheck) {
                        val message = autoCheck.obtainDebugMessage()
                        print(message) // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                    }
                }
            }

        })

        // 6. 初始化
        result = mSpeechSynthesizer?.initTts(ttsMode)
        checkResult(result, "initTts")
        return mSpeechSynthesizer
    }

    private fun checkResult(result: Int?, method: String) {
        if (result != 0) {
            print("error code :$result method:$method")
        }
    }


}