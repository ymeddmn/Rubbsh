#include <jni.h>
#include <string>
#include<android/log.h>

#define  LOG_TAG    "jnitag"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

//返回字符串给原生
//extern "C" JNIEXPORT jstring JNICALL
//Java_com_example_demo_jni_MainActivity_getStringStr(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "这里显示的值是jni返回字符串";
//    LOGE("hello jni");
//    return env->NewStringUTF(hello.c_str());
//}
//
////原生传参数给jni并且打印
//extern "C" JNIEXPORT void JNICALL
//Java_com_example_demo_jni_MainActivity_sendParamsToJni(
//        JNIEnv *env,
//        jobject, jstring jstr) {
//    const char *str = env->GetStringUTFChars(jstr, NULL);
//    LOGE("%s", str);
//}
//
////获取打印java传入的字符串长度
//extern "C" JNIEXPORT void JNICALL
//Java_com_example_demo_jni_MainActivity_getJniStrLength(
//        JNIEnv *env,
//        jobject, jstring jstr) {
//    int length = env->GetStringLength(jstr);
//    LOGE("%s", env->GetStringUTFChars(jstr, NULL));
//    LOGE("字符串的长度=%d", length);
//}
//
////向jni层传入int类型数组
//extern "C" JNIEXPORT void JNICALL
//Java_com_example_demo_jni_MainActivity_sendIntArray(
//        JNIEnv *env,
//        jobject, jintArray array) {
////    jint *intArray = env->GetIntArrayElements(a, NULL);
//    int len = env->GetArrayLength(array);
//    for (int i = 0; i < len; i++) {
//        LOGE("值等于%d", array[i]);
//    }
//
//
//
//
//
//}
//extern "C"
//JNIEXPORT jstring JNICALL
//Java_com_example_demo_jni_EncryptClass_keyFromJNI(JNIEnv *env, jobject instance) {
//
//
//
//
//    return env->NewStringUTF(returnValue);
//}