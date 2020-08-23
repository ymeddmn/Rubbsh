//
// Created by 马勇 on 2020-03-31.
//
#include "yearjudge.h"
int judge(int year) {
    int result = 0;
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            // // 这里如果被 400 整数是闰年
            if (year % 400 == 0) {
//                cout << year << " 是闰年";
                result = 5;
            } else {
                result = 6;
//                cout << year << " 不是闰年";
            }
        } else {

//            cout << year << " 是闰年";
            result = 5;
        }
    } else {
        result = 6;
//        cout << year << " 不是闰年";
    }

    return result;
}