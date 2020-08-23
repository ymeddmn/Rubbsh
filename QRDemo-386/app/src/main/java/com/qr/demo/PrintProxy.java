package com.qr.demo;

import android.graphics.Bitmap;

import com.qr.print.PrintPP_CPCL;

/**
 * 打印伪代理 返回高度
 * Created by zhangshaofang on 2017/10/30.
 */

public class PrintProxy {
    private PrintPP_CPCL iPrinter;

    public PrintProxy(PrintPP_CPCL iPrinter) {
        this.iPrinter = iPrinter;
    }

    public int drawBox(int lineWidth, int top_left_x, int top_left_y, int bottom_right_x, int bottom_right_y) {
        iPrinter.drawBox(lineWidth, top_left_x, top_left_y, bottom_right_x, bottom_right_y);
        return bottom_right_y;
    }

    public int drawLine(int lineWidth, int start_x, int start_y, int end_x, int end_y, boolean fullline) {
        iPrinter.drawLine(lineWidth, start_x, start_y, end_x, end_y, fullline);
        return end_y;
    }

    public int drawText(int text_x, int text_y, String text, int fontSize, int rotate, int bold, boolean reverse, boolean underline) {
        iPrinter.drawText(text_x, text_y, text, fontSize, rotate, bold, reverse, underline);
//        if (fontSize == 1) {
//            return 24;
//        }
        int size = fontSize == 2 ? 32 : 44;
        return text_y + 32;
    }

    public int drawText(int text_x, int text_y, int width, int height, String text, int fontSize, int rotate, int bold, boolean underline, boolean reverse) {
        iPrinter.drawText(text_x, text_y, width, height, text, fontSize, rotate, bold, underline, reverse);
        return text_y + 32;
    }

    public int drawBarCode(int start_x, int start_y, String text, int type, int rotate, int linewidth, int height) {
        iPrinter.drawBarCode(start_x, start_y, text, type, rotate, linewidth, height);
        return start_y + height;
    }

    public void drawQrCode(int start_x, int start_y, String text, int rotate, int ver, int lel) {
        iPrinter.drawQrCode(start_x, start_y, text, rotate, ver, lel);
    }

    public int drawGraphic(int start_x, int start_y, int bmp_size_x, int bmp_size_y, Bitmap bmp) {
        iPrinter.drawGraphic(start_x, start_y, bmp_size_x, bmp_size_y, bmp);
        return start_y + bmp_size_y;
    }

    public int drawGraphic2(int start_x, int start_y, int bmp_size_x, int bmp_size_y, Bitmap bmp) {
        iPrinter.drawGraphic2(start_x, start_y, bmp_size_x, bmp_size_y, bmp);
        return start_y + bmp_size_y;
    }
}
