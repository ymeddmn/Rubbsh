


## 根目录build.gradle 和app目录下build.gradle增加相应的配置
## 这个配置必须加
 compileOptions {
           sourceCompatibility 1.8
           targetCompatibility 1.8
       }
## 增加相应的权限
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" /> <!-- 获取ip -->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>(没有会报错)
## 自己的application中增加配置  SitechApm.INSTANCE.initialize(this);

