package com.sitech.paas.test1;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * author  :mayong
 * function:
 * date    :2020-08-12
 **/
public class IoUtils {

    public static void saveFile(String str) {
        String filePath = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd=HH:mm:ss");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sitechLog";
        filePath = path + File.separator + simpleDateFormat.format(System.currentTimeMillis()) + ".txt";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
