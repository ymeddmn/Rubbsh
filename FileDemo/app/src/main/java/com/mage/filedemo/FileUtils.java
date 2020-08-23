package com.mage.filedemo;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * author  :mayong
 * function:
 * date    :2019-09-27
 **/
public class FileUtils {
    /**
     * 写文件
     * @param path
     * @param fileName
     * @param content
     */
    public static void writeExternal(String path, String fileName, String content) {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path;
        File file = new File(absolutePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(absolutePath + File.separator + fileName));
            outputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读文件
     * @param path
     * @param fileName
     * @return
     */
    public static String readExternal(String path, String fileName) {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path + File.separator + fileName;
        File file = new File(absolutePath);
        InputStream inputStream = null;
        StringBuffer sb = new StringBuffer();
        try {
            inputStream = new FileInputStream(file);
            byte buffer[] = new byte[128];
            int len = inputStream.read(buffer);

            while (len > 0) {
                String temp = new String(buffer, 0, len);
                sb.append(temp);
                len= inputStream.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
