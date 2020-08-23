package com.mage.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btnInnerPath;
    private Button btnOuterPath;
    private TextView tvInnerPath, tvOuterPath;
    private Button btnOutPackagePath;
    private TextView tvOutPackagePath;
    private EditText etSaveToExternal;
    private Button btnSaveToExternal;
    private Button btnReadExternal;
    private TextView tvReadExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btnInnerPath = findViewById(R.id.btn_inner_path);
        btnOuterPath = findViewById(R.id.btn_outer_path);
        btnOutPackagePath = findViewById(R.id.btn_outer_packagepath);

        tvInnerPath = findViewById(R.id.tv_inner_path);
        tvOuterPath = findViewById(R.id.tv_outer_path);
        tvOutPackagePath = findViewById(R.id.tv_outer_packagepath);
        etSaveToExternal = findViewById(R.id.et_saveto_external);
        btnSaveToExternal = findViewById(R.id.btn_saveto_external);
        btnReadExternal = findViewById(R.id.btn_read_externalfile);
        tvReadExternal = findViewById(R.id.tv_read_externalfile);
    }

    private void initListener() {
        btnInnerPath.setOnClickListener(v -> {
            String absolutePath = getFilesDir().getAbsolutePath();
            tvInnerPath.setText("getFilesDir().getAbsolutePath()\n" + absolutePath);
        });
        btnOuterPath.setOnClickListener(v -> {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String path = Environment.getExternalStorageDirectory().getPath();
            String text = "Environment.getExternalStorageDirectory().getAbsolutePath()=\n" + absolutePath + "\n\n" +
                    "Environment.getExternalStorageDirectory().getPath()=\n" + path;
            tvOuterPath.setText(text);
        });

        btnOutPackagePath.setOnClickListener(v -> {
            String absolutePath = getExternalCacheDir().getAbsolutePath();
            tvOutPackagePath.setText("外部存储包路径为：\n" + absolutePath);
        });

        btnSaveToExternal.setOnClickListener(v -> {
            String content = etSaveToExternal.getText().toString();
            FileUtils.writeExternal("aaaaa", "content.txt", content);
        });

        btnReadExternal.setOnClickListener(v -> {
            String content = FileUtils.readExternal("aaaaa", "content.txt");
            tvReadExternal.setText("读取到的文件内容是:\n" + content);
        });
    }
}
