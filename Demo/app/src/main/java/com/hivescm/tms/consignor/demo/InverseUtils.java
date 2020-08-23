package com.hivescm.tms.consignor.demo;

import android.databinding.InverseBindingMethod;
import android.databinding.InverseMethod;

/**
 * Created by mayong on 2018/7/2.
 */

public class InverseUtils {

    @InverseMethod("flagToName")
    public static int nameToFlag(String name) {
        switch (name) {
            case "销售单":
                return 1;
            case "销退单":
                return 2;
        }
        return -1;
    }


    public static String flagToName(int flag) {
        switch (flag) {
            case 1:
                return "销售单";
            case 2:
                return "销退单";
        }
        return "未知";
    }
}
