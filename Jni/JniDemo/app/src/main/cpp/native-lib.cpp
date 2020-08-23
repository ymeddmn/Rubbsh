#include <jni.h>
#include <string>
#include <zconf.h>
//#include "yearjudge.h"
extern "C" JNIEXPORT jbyte JNICALL
Java_com_sitech_paas_jnidemo_MainActivity_stringFromJNI(
        JNIEnv *env, jobject,jbyteArray jb) {
    jbyte * jbyte1=env->GetByteArrayElements(jb,JNI_FALSE);//通过GetByteArrayElements获得byte数组，
    jsize size = env->GetArrayLength(jb);

    return *(jbyte1);//返回byte数组中第一个数据
}
//extern "C" JNIEXPORT jbyte JNICALL
//Java_com_sitech_paas_jnidemo_MainActivity_stringFromJNI1(
//        JNIEnv *env, jobject,jint year) {
//    return (judge(year));//返回byte数组中第一个数据
//}
