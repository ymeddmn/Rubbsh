package haha;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.haha.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPackage();
    }

    private void getPackage() {
        PackageManager pckMan = getPackageManager();
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();

        List<PackageInfo> packageInfo = pckMan.getInstalledPackages(0);
        for (PackageInfo info : packageInfo) {
            if(Build.VERSION.SDK_INT>=28){
                Log.e("包信息",info.packageName+info.getLongVersionCode()+"哈哈");
            }else {
                Log.e("包信息",info.packageName+info.versionName);
            }
        }

    }
}
