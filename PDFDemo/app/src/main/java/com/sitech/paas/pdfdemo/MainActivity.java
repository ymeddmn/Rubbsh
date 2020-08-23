package com.sitech.paas.pdfdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PDFView pdfView = findViewById(R.id.pdfView);
        PDFView.Configurator configurator = pdfView.fromFile(new File("/sdcard/Download/tempfiles/998ee83c-dda8-395a-a5b7-d5c19bee7dab.pdf"));
        configurator.load();
    }
}
