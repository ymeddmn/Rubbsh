package com.hivescm.tms.consignor.demo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;

/**
 * Created by mayong on 2018/7/10.
 */

public class AndToJsUtils {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void toJs(WebView webView, Object... obj) {
        webView.evaluateJavascript(getScript(obj), data -> {
            System.out.println("值"+data.toString());
        });
//        "javascript:test2('"+ jsonStr+"')"
    }

    private static String getScript(Object[] objs) {
        String params = "javascript:test2('";
        if (objs.length == 0) {
            params = "javascript:test2()";
        }else {
            for (Object o : objs) {
                params=params+o;
            }

            params += "')";
        }

        return params;
    }
}
