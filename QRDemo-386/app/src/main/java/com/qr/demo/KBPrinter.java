package com.qr.demo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.text.TextUtils;


import com.qr.print.*;

//import printpp.printpp_yt.PrintPP_CPCL;

/**
 * Created by kuaibao on 2017/6/6.
 *
 * 启锐386型号打印机
 */

public class KBPrinter   {
	
	 protected static final double MULTIPLE = 8;
	    protected static final int page_width = (int) (70 * MULTIPLE);
	    protected static final int page_height = (int) (200 * MULTIPLE);
	    protected static final int page_left = (int) (0 * MULTIPLE);
	    protected static final int page_right = (int) (70 * MULTIPLE);
	    protected static final int first_pager_bottom = (int) (96 * MULTIPLE);
	    //    private static final int second_pager_bottom =(int) (50*MULTIPLE);
	    protected static final int second_pager_bottom = (int) (146 * MULTIPLE);
	    public static BluetoothAdapter myBluetoothAdapter;
	    protected final int first_horizontal_line1_x1 = page_left;
	    protected final int first_horizontal_line1_y1 = (int) (12 * MULTIPLE);
	    protected final int first_horizontal_line1_x2 = page_right;
	    protected final int first_horizontal_line1_y2 = first_horizontal_line1_y1;
	    protected final int first_horizontal_line2_x1 = page_left;
	    protected final int first_horizontal_line2_y1 = (int) (20 * MULTIPLE) + first_horizontal_line1_y1;
	    protected final int first_horizontal_line2_x2 = page_right;
	    protected final int first_horizontal_line2_y2 = first_horizontal_line2_y1;
	    protected final int first_horizontal_line3_x1 = page_left;
	    protected final int first_horizontal_line3_y1 = (int) (10 * MULTIPLE) + first_horizontal_line2_y1;
	    protected final int first_horizontal_line3_x2 = page_right;
	    protected final int first_horizontal_line3_y2 = first_horizontal_line3_y1;
	    protected final int first_horizontal_line4_x1 = page_left;
	    protected final int first_horizontal_line4_y1 = (int) (8 * MULTIPLE) + first_horizontal_line3_y1;
	    protected final int first_horizontal_line4_x2 = page_right;
	    protected final int first_horizontal_line4_y2 = first_horizontal_line4_y1;
	    protected final int first_horizontal_line5_x1 = page_left;
	    protected final int first_horizontal_line5_y1 = (int) (15 * MULTIPLE) + first_horizontal_line4_y1;
	    protected final int first_horizontal_line5_x2 = page_right;
	    protected final int first_horizontal_line5_y2 = first_horizontal_line5_y1;
	    protected final int first_horizontal_line6_x1 = page_left;
	    protected final int first_horizontal_line6_y1 = (int) (13 * MULTIPLE) + first_horizontal_line5_y1;
	    protected final int first_horizontal_line6_x2 = page_right;
	    protected final int first_horizontal_line6_y2 = first_horizontal_line6_y1;
	    protected final int first_vertical_line1_x1 = page_left + (int) (8 * MULTIPLE);
	    protected final int first_vertical_line1_y1 = first_horizontal_line4_y1;
	    protected final int first_vertical_line1_x2 = first_vertical_line1_x1;
	    protected final int first_vertical_line1_y2 = first_pager_bottom;
	    protected final int first_vertical_line2_x1 = page_left + (int) (35 * MULTIPLE);
	    protected final int first_vertical_line2_y1 = first_horizontal_line3_y1;
	    protected final int first_vertical_line2_x2 = first_vertical_line2_x1;
	    protected final int first_vertical_line2_y2 = first_horizontal_line4_y1;
	    protected final int first_vertical_line3_x1 = first_vertical_line2_x1;
	    protected final int first_vertical_line3_y1 = first_horizontal_line6_y1;
	    protected final int first_vertical_line3_x2 = first_vertical_line2_x1;
	    protected final int first_vertical_line3_y2 = first_pager_bottom;
	    //    private final int first_text1_size= 0;
	    protected final int first_text1_size = 32;
	    protected final int first_text1_x = page_left + (int) (35 * MULTIPLE);
	    protected final int first_text1_y = (int) ((12 * MULTIPLE - first_text1_size) / 8);
	    protected final int first_billcode_y = (int) ((12 * MULTIPLE - first_text1_size)/1.5);
	    protected final int first_text2_size = 32;
	    protected final int first_text2_x = page_left + (int) (16 * MULTIPLE);
	    protected final int first_text2_y = first_horizontal_line2_y1 - 48;
	    protected final int first_text3_size = 48;
	    //    protected final int first_text3_x = page_left+ (int) (10*MULTIPLE);
	    protected final int first_text3_y = first_horizontal_line2_y1 + (int) ((10 * MULTIPLE - first_text3_size) / 2);
	    protected final int first_text4_size = 32;
	    //    protected final int first_text4_x = page_left+ (int) (10*MULTIPLE);
	    protected final int first_text4_y = first_horizontal_line3_y1 + (int) ((8 * MULTIPLE - first_text4_size) / 2);
	    protected final int first_text5_size = 24;
	    //    protected final int first_text5_x = first_horizontal_line3_x1+ (int) (10*MULTIPLE);
	    protected final int first_text5_y = first_horizontal_line3_y1 + (int) ((8 * MULTIPLE - first_text5_size) / 2);
	    protected final int first_text6_size = 24;
	    protected final int first_text6_x = page_left + (int) ((8 * MULTIPLE - first_text6_size) / 2);
	    protected final int first_text6_y = first_horizontal_line4_y1 + (int) (15 * MULTIPLE / 2 - 2 * first_text6_size);
	    protected final String first_text6 = "收";
	    protected final int first_text7_size = first_text6_size;
	    protected final int first_text7_x = first_text6_x;
	    protected final int first_text7_y = first_horizontal_line4_y1 + (int) (15 * MULTIPLE / 2 - first_text7_size);
	    protected final String first_text7 = "件";
	    protected final int first_text8_size = first_text6_size;
	    protected final int first_text8_x = first_text6_x;
	    protected final int first_text8_y = first_horizontal_line4_y1 + (int) (15 * MULTIPLE / 2);
	    protected final String first_text8 = "信";
	    protected final int first_text9_size = first_text6_size;
	    protected final int first_text9_x = first_text6_x;
	    protected final int first_text9_y = first_horizontal_line4_y1 + (int) (15 * MULTIPLE / 2 + first_text8_size);
	    protected final String first_text9 = "息";
	    protected final int first_text10_size = 24;
	    protected final int first_text10_x = first_vertical_line1_x1 + first_text10_size / 2;
	    protected final int first_text10_y = first_horizontal_line4_y1 + first_text10_size;
	    protected final int first_text11_size = 24;
	    protected final int first_text11_x = page_left + (int) (35 * MULTIPLE);
	    protected final int first_text11_y = first_text10_y;
	    protected final int first_text12_size = 24;
	    protected final int first_text12_x = first_text10_x;
	    protected final int first_text12_y = first_text10_y + first_text12_size;
	    protected final int first_text13_size = 24;
	    protected final int first_text13_x = page_left + (int) ((8 * MULTIPLE - first_text13_size) / 2);
	    protected final int first_text13_y = first_horizontal_line5_y1 + (int) (13 * MULTIPLE / 2 - 2 * first_text13_size);
	    protected final String first_text13 = "寄";
	    protected final int first_text14_size = first_text13_size;
	    protected final int first_text14_x = first_text13_x;
	    protected final int first_text14_y = first_horizontal_line5_y1 + (int) (13 * MULTIPLE / 2 - first_text7_size);
	    protected final String first_text14 = "件";
	    protected final int first_text15_size = first_text13_size;
	    protected final int first_text15_x = first_text13_x;
	    protected final int first_text15_y = first_horizontal_line5_y1 + (int) (13 * MULTIPLE / 2);
	    protected final String first_text15 = "信";
	    protected final int first_text16_size = first_text13_size;
	    protected final int first_text16_x = first_text13_x;
	    protected final int first_text16_y = first_horizontal_line5_y1 + (int) (13 * MULTIPLE / 2 + first_text8_size);
	    protected final String first_text16 = "息";
	    protected final int first_text17_size = 16;
	    protected final int first_text17_x = first_vertical_line1_x1 + first_text17_size / 2;
	    protected final int first_text17_y = first_horizontal_line5_y1 + first_text17_size;
	    protected final int first_text18_size = 16;
	    protected final int first_text18_x = page_left + (int) (35 * MULTIPLE);
	    protected final int first_text18_y = first_text17_y;
	    protected final int first_text19_size = 20;
	    protected final int first_text19_x = first_text17_x;
	    protected final int first_text19_y = first_text18_y + first_text19_size;
	    protected final int first_text20_size = 24;
	    protected final int first_text20_x = first_text13_x;
	    protected final int first_text20_y = first_horizontal_line6_y1 + (int) (22 * MULTIPLE / 2 - first_text7_size);
	    protected final String first_text20 = "服";
	    protected final int first_text21_size = first_text13_size;
	    protected final int first_text21_x = first_text13_x;
	    protected final int first_text21_y = first_horizontal_line6_y1 + (int) (22 * MULTIPLE / 2);
	    protected final String first_text21 = "务";
	    protected final int first_text22_size = 16;
	    protected final int first_text22_x = first_vertical_line1_x1 + first_text22_size;
	    protected final int first_text22_y = first_horizontal_line6_y1 + 6;
	    protected final int first_text23_size = first_text22_size;
	    protected final int first_text23_x = first_text22_x;
	    protected final int first_text23_y = first_text22_y + first_text23_size + 12;
	    protected final int first_text24_size = first_text22_size;
	    protected final int first_text24_x = first_text22_x;
	    protected final int first_text24_y = first_text23_y + first_text23_size + 12;
	    protected final int first_text25_size = first_text22_size;
	    protected final int first_text25_x = first_text22_x;
	    protected final int first_text25_y = first_text24_y + first_text23_size + 12;
	    protected final int first_text26_size = first_text22_size;
	    protected final int first_text26_x = first_text22_x;
	    protected final int first_text26_y = first_text25_y + first_text23_size + 12;
	    protected final int first_text27_size = 24;
	    protected final int first_text27_x = first_vertical_line3_x1 + first_text27_size;
	    protected final int first_text27_y = first_horizontal_line6_y1 + 12;
	    protected final String first_text27 = "签收人/签收时间";
	    protected final int first_text28_size = 24;
	    protected final int first_text28_x = first_vertical_line3_x1 + (int) (20 * MULTIPLE);
	    protected final int first_text28_y = first_pager_bottom - 2 * first_text27_size;
	    protected String first_text28 = "";
	    protected final int first_barcode1_x = page_left + (int) (10 * MULTIPLE);
	    protected final int first_barcode1_y = first_horizontal_line1_y1 + (int) (2 * MULTIPLE);
	    protected final int second_horizontal_line1_x1 = page_left;
	    //    private final int second_horizontal_line1_y1 = (int) (17* MULTIPLE);
	    protected final int second_horizontal_line1_y1 = first_pager_bottom + (int) (17 * MULTIPLE);
	    protected final int second_horizontal_line1_x2 = page_right;
	    protected final int second_horizontal_line1_y2 = second_horizontal_line1_y1;
	    protected final int second_horizontal_line2_x1 = page_left;
	    protected final int second_horizontal_line2_y1 = (int) (6 * MULTIPLE) + second_horizontal_line1_y1;
	    protected final int second_horizontal_line2_x2 = page_right;
	    protected final int second_horizontal_line2_y2 = second_horizontal_line2_y1;
	    protected final int second_horizontal_line3_x1 = page_left;
	    protected final int second_horizontal_line3_y1 = (int) (15 * MULTIPLE) + second_horizontal_line2_y1;
	    protected final int second_horizontal_line3_x2 = page_right;
	    protected final int second_horizontal_line3_y2 = second_horizontal_line3_y1;
	    protected final int second_horizontal_line4_x1 = page_left;
	    protected final int second_horizontal_line4_y1 = (int) (6 * MULTIPLE) + second_horizontal_line3_y1;
	    protected final int second_horizontal_line4_x2 = page_right;
	    protected final int second_horizontal_line4_y2 = second_horizontal_line4_y1;
	    protected final int second_vertical_line1_x1 = page_left + (int) (35 * MULTIPLE);
	    protected final int second_vertical_line1_y1 = second_horizontal_line1_y1;
	    protected final int second_vertical_line1_x2 = second_vertical_line1_x1;
	    protected final int second_vertical_line1_y2 = second_horizontal_line3_y1;
	    protected final int second_vertical_line2_x1 = page_left + (int) (14 * MULTIPLE);
	    protected final int second_vertical_line2_y1 = second_horizontal_line3_y1;
	    protected final int second_vertical_line2_x2 = second_vertical_line2_x1;
	    protected final int second_vertical_line2_y2 = second_pager_bottom;
	    protected final int second_vertical_line3_x1 = second_vertical_line2_x1 + (int) (14 * MULTIPLE);
	    protected final int second_vertical_line3_y1 = second_horizontal_line3_y1;
	    protected final int second_vertical_line3_x2 = second_vertical_line3_x1;
	    protected final int second_vertical_line3_y2 = second_pager_bottom;
	    protected final int second_vertical_line4_x1 = second_vertical_line3_x1 + (int) (14 * MULTIPLE);
	    protected final int second_vertical_line4_y1 = second_horizontal_line3_y1;
	    protected final int second_vertical_line4_x2 = second_vertical_line4_x1;
	    protected final int second_vertical_line4_y2 = second_pager_bottom;
	    protected final int second_vertical_line5_x1 = second_vertical_line4_x1 + (int) (14 * MULTIPLE);
	    protected final int second_vertical_line5_y1 = second_horizontal_line3_y1;
	    protected final int second_vertical_line5_x2 = second_vertical_line5_x1;
	    protected final int second_vertical_line5_y2 = second_pager_bottom;
	    protected final int second_text1_size = 16;
	    protected final int second_text1_x = page_left + 8;
	    protected final int second_text1_y = second_horizontal_line1_y1 + (int) ((6 * MULTIPLE - second_text1_size) / 2);
	    protected final int second_text2_size = 16;
	    protected final int second_text2_x = second_vertical_line1_x1 + 8;
	    protected final int second_text2_y = second_text1_y;
	    protected final int second_text3_size = 16;
	    protected final int second_text3_x = second_text1_x;
	    protected final int second_text3_y = second_horizontal_line2_y1;
	    protected final String second_text3 = "收件方信息";
	    protected final int second_text4_size = 16;
	    protected final int second_text4_x = second_text1_x;
	    protected final int second_text4_y = second_text3_y + second_text4_size+5;
	    protected final int second_text5_size = 16;
	    protected final int second_text5_x = second_text1_x;
	    protected final int second_text5_y = second_text4_y + second_text4_size+5;
	    protected final int second_text6_size = 16;
	    protected final int second_text6_x = second_text2_x;
	    protected final int second_text6_y = second_horizontal_line2_y1 ;
	    protected final String second_text6 = "寄件方信息";
	    protected final int second_text7_size = 16;
	    protected final int second_text7_x = second_text6_x;
	    protected final int second_text7_y = second_text6_y + second_text4_size +5;
	    protected final int second_text8_size = 16;
	    protected final int second_text8_x = second_text6_x;
	    protected final int second_text8_y = second_text7_y + second_text4_size +5;
	    protected final int second_text9_size = 16;
	    protected final int second_text9_x = page_left + 20;
	    protected final int second_text9_y = second_horizontal_line3_y1 + (int) ((6 * MULTIPLE - second_text9_size) / 2);
	    protected final String second_text9 = "内容品名";
	    protected final int second_text10_size = 16;
	    protected final int second_text10_x = second_vertical_line2_x1 + 10;
	    protected final int second_text10_y = second_text9_y;
	    protected final String second_text10 = "计费重量(kg)";
	    protected final int second_text11_size = 16;
	    protected final int second_text11_x = second_vertical_line3_x1 + 10;
	    protected final int second_text11_y = second_text9_y;
	    protected final String second_text11 = "声明价值(￥)";
	    protected final int second_text12_size = 16;
	    protected final int second_text12_x = second_vertical_line4_x1 + 10;
	    protected final int second_text12_y = second_text9_y;
	    protected final String second_text12 = "代收金额(￥)";
	    protected final int second_text13_size = 16;
	    protected final int second_text13_x = second_vertical_line5_x1 + 10;
	    protected final int second_text13_y = second_text9_y;
	    protected final String second_text13 = "到付金额(￥)";
	    protected final int second_text14_size = 16;
	    //    protected final int second_text14_x = page_left+ 8;
	    protected final int second_text14_y = second_horizontal_line4_y1 + (int) ((6 * MULTIPLE - second_text9_size) / 2);
	    protected final int second_text15_size = 16;
	    //    protected final int second_text15_x = second_vertical_line2_x1+ 22;
	    protected final int second_text15_y = second_text14_y;
	    protected final int second_text16_size = 16;
	    //    protected final int second_text16_x = second_vertical_line3_x1+ 22;
	    protected final int second_text16_y = second_text14_y;
	    protected final int second_text17_size = 16;
	    //    protected final int second_text17_x = second_vertical_line4_x1+ 22;
	    protected final int second_text17_y = second_text14_y;
	    protected final int second_text18_size = 16;
	    //    protected final int second_text18_x = second_vertical_line5_x1+ 22;
	    protected final int second_text18_y = second_text14_y;
	    protected final int third_horizontal_line1_x1 = page_left;
	    protected final int third_horizontal_line1_y1 = second_pager_bottom + (int) (13 * MULTIPLE);
	    protected final int third_horizontal_line1_x2 = page_right;
	    protected final int third_horizontal_line1_y2 = third_horizontal_line1_y1;
	    protected final int third_horizontal_line2_x1 = page_left;
	    protected final int third_horizontal_line2_y1 = (int) (15 * MULTIPLE) + third_horizontal_line1_y1;
	    protected final int third_horizontal_line2_x2 = page_right;
	    protected final int third_horizontal_line2_y2 = third_horizontal_line2_y1;
	    protected final int third_horizontal_line3_x1 = page_left;
	    protected final int third_horizontal_line3_y1 = (int) (6 * MULTIPLE) + third_horizontal_line2_y1;
	    protected final int third_horizontal_line3_x2 = page_right;
	    protected final int third_horizontal_line3_y2 = third_horizontal_line3_y1;
	    protected final int third_horizontal_line4_x1 = page_left;
	    protected final int third_horizontal_line4_y1 = (int) (6 * MULTIPLE) + third_horizontal_line3_y1;
	    protected final int third_horizontal_line4_x2 = page_right;
	    protected final int third_horizontal_line4_y2 = third_horizontal_line4_y1;
	    protected final int third_vertical_line1_x1 = page_left + (int) (35 * MULTIPLE);
	    protected final int third_vertical_line1_y1 = second_pager_bottom;
	    protected final int third_vertical_line1_x2 = third_vertical_line1_x1;
	    protected final int third_vertical_line1_y2 = third_horizontal_line2_y1;
	    protected final int third_vertical_line2_x1 = page_left + (int) (14 * MULTIPLE);
	    protected final int third_vertical_line2_y1 = third_horizontal_line2_y1;
	    protected final int third_vertical_line2_x2 = third_vertical_line2_x1;
	    protected final int third_vertical_line2_y2 = third_horizontal_line4_y1;
	    protected final int third_vertical_line3_x1 = third_vertical_line2_x1 + (int) (14 * MULTIPLE);
	    protected final int third_vertical_line3_y1 = third_horizontal_line2_y1;
	    protected final int third_vertical_line3_x2 = third_vertical_line3_x1;
	    protected final int third_vertical_line3_y2 = third_horizontal_line4_y1;
	    protected final int third_vertical_line4_x1 = third_vertical_line3_x1 + (int) (14 * MULTIPLE);
	    protected final int third_vertical_line4_y1 = third_horizontal_line2_y1;
	    protected final int third_vertical_line4_x2 = third_vertical_line4_x1;
	    protected final int third_vertical_line4_y2 = third_horizontal_line4_y1;
	    protected final int third_vertical_line5_x1 = third_vertical_line4_x1 + (int) (14 * MULTIPLE);
	    protected final int third_vertical_line5_y1 = third_horizontal_line2_y1;
	    protected final int third_vertical_line5_x2 = third_vertical_line5_x1;
	    protected final int third_vertical_line5_y2 = third_horizontal_line4_y1;
	    protected final int third_vertical_line6_x1 = page_left + (int) (35 * MULTIPLE);
	    protected final int third_vertical_line6_y1 = third_horizontal_line4_y1;
	    protected final int third_vertical_line6_x2 = third_vertical_line6_x1;
	    protected final int third_vertical_line6_y2 = (int) (200 * MULTIPLE);
	    protected final int third_text1_size = 24;
	    protected final int third_text1_x = page_left + 40;
	    protected final int third_text1_y = third_horizontal_line1_y1 - third_text1_size - 2;
	    protected final int third_text2_size = 16;
	    protected final int third_text2_x = page_left + 8;
	    protected final int third_text2_y = third_horizontal_line1_y1 + 8;
	    protected final String third_text2 = "收件方信息";
	    protected final int third_text3_size = 16;
	    protected final int third_text3_x = third_text2_x;
	    protected final int third_text3_y = third_text2_y + third_text3_size + 8;
	    protected final int third_text4_size = 16;
	    protected final int third_text4_x = third_text2_x;
	    protected final int third_text4_y = third_text3_y + third_text4_size + 8;
	    protected final int third_text5_size = 16;
	    protected final int third_text5_x = third_vertical_line1_x1 + 8;
	    protected final int third_text5_y = third_horizontal_line1_y1 + 8;
	    protected final String third_text5 = "寄件方信息";
	    protected final int third_text6_size = 16;
	    protected final int third_text6_x = third_text5_x;
	    protected final int third_text6_y = third_text5_y + third_text4_size + 8;
	    protected final int third_text7_size = 16;
	    protected final int third_text7_x = third_text5_x;
	    protected final int third_text7_y = third_text6_y + third_text4_size + 8;
	    protected final int third_text9_size = 16;
	    protected final int third_text9_x = page_left + 20;
	    protected final int third_text9_y = third_horizontal_line2_y1 + (int) ((6 * MULTIPLE - third_text9_size) / 2);
	    protected final String third_text9 = "内容品名";
	    protected final int third_text10_size = 16;
	    protected final int third_text10_x = third_vertical_line2_x1 + 10;
	    protected final int third_text10_y = third_text9_y;
	    protected final String third_text10 = "计费重量(kg)";
	    protected final int third_text11_size = 16;
	    protected final int third_text11_x = third_vertical_line3_x1 + 10;
	    protected final int third_text11_y = third_text9_y;
	    protected final String third_text11 = "声明价值(￥)";
	    protected final int third_text12_size = 16;
	    protected final int third_text12_x = third_vertical_line4_x1 + 10;
	    protected final int third_text12_y = third_text9_y;
	    protected final String third_text12 = "代收金额(￥)";
	    protected final int third_text13_size = 16;
	    protected final int third_text13_x = third_vertical_line5_x1 + 10;
	    protected final int third_text13_y = third_text9_y;
	    protected final int third_text14_size = 16;
	    //    protected final int third_text14_x = page_left+ 8;
	    protected final int third_text14_y = third_horizontal_line3_y1 + (int) ((6 * MULTIPLE - third_text9_size) / 2);
	    protected final int third_text15_size = 16;
	    //    protected final int third_text15_x = third_vertical_line2_x1+ 22;
	    protected final int third_text15_y = third_text14_y;
	    protected final int third_text16_size = 16;
	    //    protected final int third_text16_x = third_vertical_line3_x1+ 22;
	    protected final int third_text16_y = third_text14_y;
	    protected final int third_text17_size = 16;
	    //    protected final int third_text17_x = third_vertical_line4_x1+ 22;
	    protected final int third_text17_y = third_text14_y;
	    protected final int third_text18_size = 16;
	    //    protected final int third_text18_x = third_vertical_line5_x1+ 22;
	    protected final int third_text18_y = third_text14_y;
	    protected final int third_text19_size = 16;
	    protected final int third_text19_x = page_left + 8;
	    protected final int third_text19_y = third_horizontal_line4_y1 + 16;
	    protected final String third_text19 = "打印时间";
	    protected final int third_text20_size = 16;
	    protected final int third_text20_x = page_left + 8;
	    protected final int third_text20_y = third_text19_y + third_text19_size + 8;
	    protected final int third_text21_size = 16;
	    protected final int third_text21_x = third_vertical_line6_x1 + 8;
	    protected final int third_text21_y = third_horizontal_line4_y1 + 8;
	    protected final String third_text21 = "快递员签名/签名时间";
	    protected final int third_text22_size = 16;
	    protected final int third_text22_x = third_vertical_line6_x1 + 132;
	    protected final int third_text22_y = page_height - 2 * third_text22_size - 16;
	    protected final String third_text22 = "       月       日";
	    protected final int third_barcode1_x = page_left + (int) (4 * MULTIPLE);
	    protected final int third_barcode1_y = second_pager_bottom + (int) (2 * MULTIPLE);
	    protected String first_text1 = "";
	    protected String first_text2 = "360024139575";
	    protected String first_billcode = "Z0000000000000";
	    protected String first_text3 = "哈尔滨转齐齐哈尔";//居中打印
	    protected String first_text4 = "哈尔滨转齐齐哈尔";//居中打印
	    protected String first_text5 = "2015-12-24";//居中打印
	    protected String first_text10 = "收件人：李先";
	    protected String first_text11 = "手机/电话：15021498306";
	    protected String first_text12 = "地址：收件人地址收件人地址收件人地址收件人地址收件人地址收件人地址";//换行
	    protected String first_text17 = "收件人：李先";
	    protected String first_text18 = "手机/电话：15021498306";
	    protected String first_text19 = "地址：收件人地址收件人地址收件人地址收件人地址收件人地址收件人地址";//换行
	    protected String first_text22 = "内容品名：衣服";
	    protected String first_text23 = "计费重量：210（kg）";
	    protected String first_text24 = "声明价值：￥29995";
	    protected String first_text25 = "代收金额：￥29995";
	    protected String first_text26 = "到付金额：￥29995";
	    protected String second_text1 =  first_text2;
	    protected String second_text2 = "订单号：LP0000000000000";
	    protected String second_text4 = "小风采 15021498306";//姓名+电话
	    protected String second_text5 = "收件人地址收件人地址收件人地址收件人地址收件人地址";//需要换行打印
	    protected String second_text7 = "李晓晓 15021498306";//姓名+电话
	    protected String second_text8 = "寄件人地址寄件人地址寄件人地址寄件人地址寄件人地址";//需要换行打印
	    protected String second_text14 = "衣服";//居中
	    protected String second_text15 = "210";//居中
	    protected String second_text16 = "2999.95";//居中
	    protected String second_text17 = "2999.95";//居中
	    protected String second_text18 = "2999.95";//居中
	    protected String third_text1 = first_text2;
	    protected String third_text3 = "小风采 15021498306";//姓名+电话
	    protected String third_text4 = "收件人地址收件人地址收件人地址收件人地址收件人地址";//需要换行打印
	    protected String third_text6 = "李晓晓 15021498306";//姓名+电话
	    protected String third_text7 = "寄件人地址寄件人地址寄件人地址寄件人地址寄件人地址";//需要换行打印
	    protected String third_text13 = "运费(￥)";
	    protected String third_text14 = "衣服";//居中
	    protected String third_text15 = "210";//居中
	    protected String third_text16 = "2999.95";//居中
	    protected String third_text17 = "2999.95";//居中
	    protected String third_text18 = "2999.95";//居中
	    protected String third_text20 = "2015-12-28 16:52";
	    private String payMode = "";
    //申通边框
    private static final int TOP_BOX_START_X = 0;
    private static final int TOP1_BOX_START_Y = 0;
    private static final int TOP_BOX_END_X = 598;
    private static final int TOP1_BOX_END_Y = 664;
    private static final int TOP2_BOX_START_Y = 696;
    private static final int TOP2_BOX_END_Y = 968;
    private static final int TOP3_BOX_START_Y = 998;
    private static final int TOP3_BOX_END_Y = 1400;

    private static PrintPP_CPCL iPrinter;
    private BluetoothDevice printer;
    private boolean isConnected = false;
    private static KBPrinter qrPrinter;

//    private KBPrinter(PrintPP_CPCL iPrinter, BluetoothDevice printer){
//        this.iPrinter = iPrinter;
//        this.printer = printer;
//    }
    
    public KBPrinter(PrintPP_CPCL iPrinter ){
        this.iPrinter = iPrinter;
    }

//    public static KBPrinter getInstance(BluetoothDevice printer){
//        if(Utility.isEmpty(qrPrinter)){
//            iPrinter = new PrintPP_CPCL();
//            qrPrinter = new KBPrinter(iPrinter, printer);
//        }
//        return qrPrinter;
//    }
//
//    @Override
//    public void connect(BluetoothDevice device, final ConnectedCallBack callBack) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//
//                if(printer != null && !iPrinter.isConnected()){
//                    iPrinter.connect(printer.getName(), printer.getAddress());
//                }
//                return null;
//            }
//            protected void onPostExecute(Void result) {
//                callBack.connectedCallback();
//            }
//        }.execute();
//    }
//
//    @Override
//    public void connect(BluetoothDevice device) {
//        if(!Utility.isEmpty(device))
//            iPrinter.connect(device.getName(), device.getAddress());
//    }
//
//    @Override
//    public void disConnect() {
//        if(!Utility.isEmpty(iPrinter)){
//            iPrinter.disconnect();
//        }
//        isConnected = false;
//    }
//
//    @Override
//    public boolean isConnected() {
//        if(!Utility.isEmpty(iPrinter)){
//            isConnected = iPrinter.isConnected();
//        }
//        return isConnected;
//    }
//
//    @Override
//    public String getPrinterStatus() {
//        if(!Utility.isEmpty(iPrinter)){
//            return iPrinter.printerStatus();
//        }
//        return "";
//    }
//
//    @Override
//    public void destroyInstance() {
//        if (qrPrinter != null) qrPrinter = null;
//    }

    /**
     * 中通打印样板
     */

    public void printZTContnet( ) {
    	String num="1234567890";
    	String bigChar="南京";
        String charge = "12312313";//order.getCollection_amount();
        iPrinter.pageSetup(page_width, page_height);
        Order order=new Order();
        printFirstPager(num, bigChar, charge, "南京",order);
        printSecondPager(num, bigChar, charge,order);
        printThirdPager(num, bigChar, charge,order);
        iPrinter.print(0, 0);
    }

    /**
     * 申通打印样板
     */
    public void printStoContent() {
    	Order order=new Order();
    	String num="1234567890";
    	String bigChar="南京";
      //  int type = SkuaidiSpf.getPrintPaperType();
//        if("sto".equals(SkuaidiSpf.getLoginUser().getExpressNo()) && type == 1){
//            printStoLogo(num, bigChar, order);
//        }else{
            printSto(num, bigChar, order);
//        }
    }

//    @Override
//    public void printAneContent(String num, String bigChar, Order order) {
//
//    }

    private void printSto(String num, String bigChar, Order order){
        iPrinter.pageSetup(600, 1420);
        iPrinter.drawBox(2, TOP_BOX_START_X, TOP1_BOX_START_Y, TOP_BOX_END_X, TOP1_BOX_END_Y); //第一联边框
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+88, TOP_BOX_END_X, TOP1_BOX_START_Y+88,true);//第一联横线1
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+88+128, TOP_BOX_END_X, TOP1_BOX_START_Y+88+128,true);//第一联横线2
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+88+128+80, TOP_BOX_END_X, TOP1_BOX_START_Y+88+128+80,true);//第一联横线3
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+88+128+80+144, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+88+128+80+144,true);
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+88+128+80+144+128, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+88+128+80+144+128,true);
        iPrinter.drawLine(2, 52, TOP1_BOX_START_Y+88+128+80, 52, TOP1_BOX_START_Y+88+128+80+144+128,true);//第一联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+88+128+80, TOP_BOX_END_X-56-16, TOP1_BOX_END_Y, true);//第一联竖线2，从左到右
        Bitmap res;
        String expressNo = "1234567890";//SkuaidiSpf.getLoginUser().getExpressNo();
//        if("ht".equals(expressNo)){
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_ht_print1);
//        }else if("qf".equals(expressNo)){
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_qf_print1);
//        }else if("yd".equals(expressNo)){
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_yd_print1);
//        }else if("yt".equals(expressNo)){
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_yt_print1);
//        }else if("tt".equals(expressNo)){
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_tt_print1);
//        }else{
//            res = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_sto_print1);
//        }
      //  iPrinter.drawGraphic(20, TOP1_BOX_START_Y+12, res.getWidth(), res.getHeight(), res);
        iPrinter.drawBarCode(120, TOP1_BOX_START_Y+88+12, num, 1, 0, 2, 80);
        //条码数据
        iPrinter.drawText(120+12, TOP1_BOX_START_Y+88+20+76, "1234567890", 2,  0,0,false,false);
        int len = 299-24*bigChar.length();
        iPrinter.drawText(len > 0 ? len : 10 ,  TOP1_BOX_START_Y+88 + 128 +20 , bigChar, 4, 0, 0, false, false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+88 +128 + 80 +32,32,120,"收",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+88 +128 + 80 +96,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+88+128+80+144+32,32,120,"发",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+88+128+80+144+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+88+128+80+144+128+16,"签收人/签收时间",2,0,  0,false,false);
        String desc = "您的签字代表您已验收此包裹,并确认商品信息无误,包装完好,无划痕,破损等表面质量问题";
//        iPrinter.drawText(12,TOP1_BOX_START_Y+88+128+80+144+128+48,400,32,desc,1,0,  0,false,false);
        printChangeLineText(iPrinter, desc, 12, TOP1_BOX_START_Y+88+128+80+144+128+48, 16, 24, 1, 0);
        iPrinter.drawText(430,TOP1_BOX_START_Y+88+128+80+144+128+36, 20, 20, "月", 2, 0, 0, false, false);
        iPrinter.drawText(490,TOP1_BOX_START_Y+88+128+80+144+128+36, 20, 20, "日", 2, 0, 0, false, false);
        iPrinter.drawText(52+20,TOP1_BOX_START_Y+88 +128+80+24,448,40,order.getName()+"  "+order.getPhone(),3,0,  1,false,false);
//        iPrinter.drawText(52+20,TOP1_BOX_START_Y+88 +128+80+24+32,424,100,order.getAddress(),3,0,  1,false,false);
        printChangeLineText(iPrinter, order.getAddress(), 52+20, TOP1_BOX_START_Y+88 +128+80+24+32, 32, 14, 3, 1);
        iPrinter.drawText(52+20,TOP1_BOX_START_Y+88+128+80+144+24,448,40,order.getSenderName()+"  "+order.getSenderPhone(),2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP1_BOX_START_Y+88+128+80+144+24+32,424,100,order.getSenderAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, order.getSenderAddress(), 52+20, TOP1_BOX_START_Y+88+128+80+144+24+32, 24, 18, 2, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+88 +128+80+104,32,96,"派",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+88 +128+80+160,32,96,"件",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+88 +128+80+208,32,96,"联",2,0,0,false,false);

        //		//第二联
        iPrinter.drawBox(2, TOP_BOX_START_X,TOP2_BOX_START_Y, TOP_BOX_END_X, TOP2_BOX_END_Y);//第二联边框
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP2_BOX_START_Y+80, TOP_BOX_END_X, TOP2_BOX_START_Y+80,true);//第二联横线1，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP2_BOX_START_Y+80+136, TOP_BOX_END_X-56-16, TOP2_BOX_START_Y+80+136,true);//第二联横线2，从左到右
        iPrinter.drawLine(2, 52, TOP2_BOX_START_Y+80, 52, TOP2_BOX_START_Y+80+136,true);//第二联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP2_BOX_START_Y+80,TOP_BOX_END_X-56-16, TOP2_BOX_END_Y,true);//第二联竖线2，从左到右
        Bitmap bitmap;
//        if("ht".equals(expressNo)){
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_ht_print2);
//        }else if("qf".equals(expressNo)){
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_qf_print2);
//        }else if("yd".equals(expressNo)){
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_yd_print2);
//        }else if("yt".equals(expressNo)){
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_yt_print2);
//        }else if("tt".equals(expressNo)){
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_tt_print2);
//        }else{
//            bitmap = BitmapFactory.decodeResource(SKuaidiApplication.getContext().getResources(), R.drawable.logo_sto_print2);
//        }
       // iPrinter.drawGraphic(20, TOP2_BOX_START_Y+16, bitmap.getWidth(), bitmap.getHeight(), bitmap);
        iPrinter.drawBarCode(320, TOP2_BOX_START_Y+16, num, 1, 0, 2, 36);
        //条码数据
        iPrinter.drawText(320+8, TOP2_BOX_START_Y+54, "1234567890", 1,  0,0,false,false);
        iPrinter.drawText(12,TOP2_BOX_START_Y+80+35,32,120,"发",2,0,  0,false,false);
        iPrinter.drawText(12,TOP2_BOX_START_Y+80+84,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(52+20,TOP2_BOX_START_Y+80+28,448,40,order.getSenderName()+"  "+order.getSenderPhone(),2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP2_BOX_START_Y+80+28+32,424,100,order.getSenderAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, order.getSenderAddress(), 52+20, TOP2_BOX_START_Y+80+28+32, 24, 18, 2, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+50,32,96,"客",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+82,32,96,"户",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+106,32,96,"联",2,0,0,false,false);
        String weight = TextUtils.isEmpty(order.getCharging_weight()) ?"":order.getCharging_weight()+"kg";
        iPrinter.drawText(12+8,TOP2_BOX_START_Y+80+136+22-5,"物品："+order.getArticleInfo()+" "+weight,2,0,  0,false,false);
        iPrinter.drawBox(2, TOP_BOX_END_X-56-16-120, TOP2_BOX_START_Y+80+136+11, TOP_BOX_END_X-56-16-16, TOP2_BOX_END_Y-11);
        iPrinter.drawText(TOP_BOX_END_X-56-16-120+17, TOP2_BOX_START_Y+80+136+11+6, "已验视", 2, 0, 0, false, false);

        //第三联
        iPrinter.drawBox(2, TOP_BOX_START_X, TOP3_BOX_START_Y, TOP_BOX_END_X, TOP3_BOX_END_Y);//第三联边框
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP3_BOX_START_Y+80, TOP_BOX_END_X, TOP3_BOX_START_Y+80,true);//第三联横线1，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X,TOP3_BOX_START_Y+80+136, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80+136,true);//第三联横线2，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP3_BOX_START_Y+80+136+136, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80+136+136,true);//第三联横线3，从左到右
        iPrinter.drawLine(2, 52, TOP3_BOX_START_Y+80, 52, TOP3_BOX_START_Y+80+136+136, true);//第三联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80, TOP_BOX_END_X-56-16, TOP3_BOX_END_Y, true);//第三联竖线2，从左到右

    //    iPrinter.drawGraphic(20, TOP3_BOX_START_Y+16, bitmap.getWidth(), bitmap.getHeight(), bitmap);
        iPrinter.drawBarCode(320, TOP3_BOX_START_Y+16, num, 1, 0, 2, 36);
        //条码数据
        iPrinter.drawText(320+8, TOP3_BOX_START_Y+54, "1234567890", 1,  0,0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+32,32,120,"收",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+136+32,32,120,"发",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+136+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+17,448,40,order.getName()+"  "+order.getPhone(),2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+17+32,424,100,order.getAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, order.getAddress(), 52+20, TOP3_BOX_START_Y+80+17+32, 24, 18, 2, 0);
        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+136+17,448,40,order.getSenderName()+"  "+order.getSenderPhone(),2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+136+17+32,424,100,order.getSenderAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, order.getSenderAddress(), 52+20, TOP3_BOX_START_Y+80+136+17+32, 24, 18, 2, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+84,32,96,"寄",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+140,32,96,"件",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+188,32,96,"联",2,0,0,false,false);
        iPrinter.drawText(12+8,TOP3_BOX_START_Y+80+136+136+18-5,"物品："+order.getArticleInfo()+ " "+weight,2,0,  0,false,false);
        iPrinter.drawBox(2, TOP_BOX_END_X-56-16-120, TOP3_BOX_START_Y+80+136+136+8, TOP_BOX_END_X-56-16-16, TOP3_BOX_END_Y-8);
        iPrinter.drawText(TOP_BOX_END_X-56-16-120+17, TOP3_BOX_START_Y+80+136+136+8+5, "已验视", 2, 0, 0, false, false);
        iPrinter.print(0, 0);
    }

    private void printStoLogo(String num, String bigChar){
        iPrinter.pageSetup(600, 1420);
        iPrinter.drawBox(2, TOP_BOX_START_X, TOP1_BOX_START_Y, TOP_BOX_END_X, TOP1_BOX_END_Y); //第一联边框
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+92, TOP_BOX_END_X, TOP1_BOX_START_Y+92,true);//第一联横线1
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+92+86, TOP_BOX_END_X, TOP1_BOX_START_Y+92+86,true);//第一联横线2
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+92+86+80, TOP_BOX_END_X, TOP1_BOX_START_Y+92+86+80,true);//第一联横线3
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+92+86+80+172, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+92+86+80+172,true);
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP1_BOX_START_Y+92+86+80+172+140, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+92+86+80+172+140,true);
        iPrinter.drawLine(2, 52, TOP1_BOX_START_Y+92+86+80, 52, TOP1_BOX_START_Y+92+86+80+172+140,true);//第一联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP1_BOX_START_Y+92+86+80, TOP_BOX_END_X-56-16, TOP1_BOX_END_Y, true);//第一联竖线2，从左到右

        if(num != null && num.length() == 12){
            iPrinter.drawBarCode(224, TOP1_BOX_START_Y + 16, num, 1, 0, 2, 48);
            iPrinter.drawText(224 + 24, TOP1_BOX_START_Y + 16 + 52, "1234567890", 2, 0, 0, false, false);
        }else {
            iPrinter.drawBarCode(192, TOP1_BOX_START_Y + 16, num, 1, 0, 2, 48);
            iPrinter.drawText(200 + 24, TOP1_BOX_START_Y + 16 + 52, "1234567890", 2, 0, 0, false, false);
        }
        iPrinter.drawText(16,  TOP1_BOX_START_Y+ 92 + 20 , bigChar, 4, 0, 0, false, false);
        iPrinter.drawText(16,  TOP1_BOX_START_Y+ 92 + 86 +20 , "order.getConcentratePackage()", 4, 0, 0, false, false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+92+86 + 80 +32,32,120,"收",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+92+86 + 80 +96,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+92+86+80+172+32,32,120,"发",2,0,  0,false,false);
        iPrinter.drawText(12,TOP1_BOX_START_Y+92+86+80+172+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(52+20,TOP1_BOX_START_Y+92+86+80+24,448,40,"order.getName()"+"  "+"order.getPhone()",3,0,  1,false,false);
//        iPrinter.drawText(52+20,TOP1_BOX_START_Y+92+86+80+24+32,424,100,order.getAddress(),3,0,  1,false,false);
        printChangeLineText(iPrinter, "order.getAddress()", 52+20, TOP1_BOX_START_Y+92+86+80+24+32, 32, 14, 3, 1);
        iPrinter.drawText(52+20,TOP1_BOX_START_Y+92+86+80+172+24,448,40,"order.getSenderName()"+"  "+"order.getSenderPhone()",2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP1_BOX_START_Y+92+86+80+172+24+32,424,100,order.getSenderAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, "order.getSenderAddress()", 52+20, TOP1_BOX_START_Y+92+86+80+172+24+32, 24, 18, 2, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+92 +86+80+104,32,96,"派",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+92 +86+80+160,32,96,"件",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP1_BOX_START_Y+92 +86+80+208,32,96,"联",2,0,0,false,false);

        iPrinter.drawLine(2, 128, TOP1_BOX_START_Y+92+86+80+172+140, 128, TOP1_BOX_END_Y, true);
        iPrinter.drawText(16, TOP1_BOX_START_Y+92+86+80+172+140+12, "打印日期", 2, 0, 0, false, false);
        iPrinter.drawText(16, TOP1_BOX_START_Y+92+86+80+172+140+44, "Utility.getCurDate()", 1, 0, 0, false, false);
        String courNum = "21231231";//E3SysManager.getCourierNO();
        if(!TextUtils.isEmpty(courNum)){
            iPrinter.drawText(12, TOP1_BOX_START_Y+92+86+80+172+140+44+16, "工号"+courNum, 1, 0, 0, false, false);
        }
        iPrinter.drawText(128+12, TOP1_BOX_START_Y + 92 + 86 + 80 + 172 + 140 + 16, "签收人/签收时间", 2, 0, 0, false, false);
        String desc = "您的签字代表您已验收此包裹,并确认商品信息无误,包装完好,无划痕,破损等表面质量问题";
//        iPrinter.drawText(128+12, TOP1_BOX_START_Y + 92+86 + 80 + 172 + 140 + 48, 384, 32, desc, 1, 0, 0, false, false);
        printChangeLineText(iPrinter, desc, 128+12, TOP1_BOX_START_Y + 92+86 + 80 + 172 + 140 + 48, 16, 24, 1, 0);
        iPrinter.drawText(430, TOP1_BOX_START_Y + 92+86 + 80 + 172 + 140 + 36, 20, 20, "月", 2, 0, 0, false, false);
        iPrinter.drawText(490, TOP1_BOX_START_Y + 92+86 + 80 + 172 + 140 + 36, 20, 20, "日", 2, 0, 0, false, false);

        //第二联
        iPrinter.drawBox(2, TOP_BOX_START_X,TOP2_BOX_START_Y, TOP_BOX_END_X, TOP2_BOX_END_Y);//第二联边框
        iPrinter.drawLine(2, TOP_BOX_START_X, TOP2_BOX_START_Y+96, TOP_BOX_END_X, TOP2_BOX_START_Y+96,true);//第二联横线1，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP2_BOX_START_Y+96+128, TOP_BOX_END_X-56-16, TOP2_BOX_START_Y+96+128,true);//第二联横线2，从左到右
        iPrinter.drawLine(2, 264, TOP2_BOX_START_Y+96, 264, TOP2_BOX_START_Y+96+128,true);//第二联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP2_BOX_START_Y+96,TOP_BOX_END_X-56-16, TOP2_BOX_END_Y,true);//第二联竖线2，从左到右

        iPrinter.drawBarCode(312, TOP2_BOX_START_Y+16, num, 1, 0, 2, 36);
        //条码数据
        iPrinter.drawText(320+8, TOP2_BOX_START_Y+54, "1234567890", 1,  0,0,false,false);
        iPrinter.drawText(16,TOP2_BOX_START_Y+96+14,32,120,"收件",1,0,  0,false,false);
        iPrinter.drawText(16,TOP2_BOX_START_Y+96+14+26,230,40,"order.getName()"+"  "+"order.getPhone()",1,0,  0,false,false);
//        iPrinter.drawText(16,TOP2_BOX_START_Y+96+14+26+26,230,100,order.getAddress(),1,0,  0,false,false);
        printChangeLineText(iPrinter, "order.getAddress()", 16, TOP2_BOX_START_Y+96+14+26+26, 16, 14, 1, 0);
        iPrinter.drawText(16+264,TOP2_BOX_START_Y+96+14,32,120,"发件",1,0,  0,false,false);
        iPrinter.drawText(16+264,TOP2_BOX_START_Y+96+14+26,230,40,"order.getSenderName()"+"  "+"order.getSenderPhone()",1,0,  0,false,false);
//        iPrinter.drawText(16+264,TOP2_BOX_START_Y+96+14+26+26,230,100,order.getSenderAddress(),1,0,  0,false,false);
        printChangeLineText(iPrinter, "order.getSenderAddress()", 16+264, TOP2_BOX_START_Y+96+14+26+26, 16, 14, 1, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+32,32,96,"客",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+64,32,96,"户",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP2_BOX_START_Y+80+88,32,96,"联",2,0,0,false,false);
        String weight = "7kg";
        iPrinter.drawText(16,TOP2_BOX_START_Y+96+128+16,"物品："+"order.getArticleInfo()"+" "+weight,2,0,  0,false,false);
        iPrinter.drawBox(2, TOP_BOX_END_X-56-16-120, TOP2_BOX_START_Y+96+128+11, TOP_BOX_END_X-56-16-16, TOP2_BOX_END_Y-6);
        iPrinter.drawText(TOP_BOX_END_X-56-16-120+17, TOP2_BOX_START_Y+96+128+11+6, "已验视", 2, 0, 0, false, false);

        //第三联
        iPrinter.drawBox(2, TOP_BOX_START_X, TOP3_BOX_START_Y, TOP_BOX_END_X, TOP3_BOX_END_Y);//第三联边框
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP3_BOX_START_Y+80, TOP_BOX_END_X, TOP3_BOX_START_Y+80,true);//第三联横线1，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X,TOP3_BOX_START_Y+80+136, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80+136,true);//第三联横线2，从左到右
        iPrinter.drawLine(2,TOP_BOX_START_X, TOP3_BOX_START_Y+80+136+136, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80+136+136,true);//第三联横线3，从左到右
        iPrinter.drawLine(2, 52, TOP3_BOX_START_Y+80, 52, TOP3_BOX_START_Y+80+136+136, true);//第三联竖线1，从左到右
        iPrinter.drawLine(2, TOP_BOX_END_X-56-16, TOP3_BOX_START_Y+80, TOP_BOX_END_X-56-16, TOP3_BOX_END_Y, true);//第三联竖线2，从左到右

        iPrinter.drawBarCode(312, TOP3_BOX_START_Y+16, num, 1, 0, 2, 36);
        //条码数据
        iPrinter.drawText(320+8, TOP3_BOX_START_Y+54, "1234567890", 1,  0,0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+32,32,120,"收",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+136+32,32,120,"发",2,0,  0,false,false);
        iPrinter.drawText(12,TOP3_BOX_START_Y+80+136+80,32,120,"件",2,0,  0,false,false);
        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+17,448,40,"order.getName()"+"  "+"order.getPhone()",2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+17+32,424,100,order.getAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, "order.getAddress()", 52+20, TOP3_BOX_START_Y+80+17+32, 24, 18, 2, 0);
        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+136+17,448,40,"order.getSenderName()"+"  "+"order.getSenderPhone()",2,0,  0,false,false);
//        iPrinter.drawText(52+20,TOP3_BOX_START_Y+80+136+17+32,424,100,order.getSenderAddress(),2,0,  0,false,false);
        printChangeLineText(iPrinter, "order.getSenderAddress()", 52+20, TOP3_BOX_START_Y+80+136+17+32, 24, 18, 2, 0);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+84,32,96,"寄",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+140,32,96,"件",2,0,0,false,false);
        iPrinter.drawText(TOP_BOX_END_X-56-5,TOP3_BOX_START_Y+80+188,32,96,"联",2,0,0,false,false);
        iPrinter.drawText(12+8,TOP3_BOX_START_Y+80+136+136+18-5,"物品："+"order.getArticleInfo()"+ " "+weight,2,0,  0,false,false);
        iPrinter.drawBox(2, TOP_BOX_END_X-56-16-120, TOP3_BOX_START_Y+80+136+136+8, TOP_BOX_END_X-56-16-16, TOP3_BOX_END_Y-8);
        iPrinter.drawText(TOP_BOX_END_X-56-16-120+17, TOP3_BOX_START_Y+80+136+136+8+5, "已验视", 2, 0, 0, false, false);
        iPrinter.print(0, 0);
    }

    /**
     * 中通打印第一联
     */
    private void printFirstPager(String num, String bigChar, String charge, String ztShopName,Order order) {
        iPrinter.drawLine(1, first_horizontal_line1_x1, first_horizontal_line1_y1, first_horizontal_line1_x2, first_horizontal_line1_y2, true);
        iPrinter.drawLine(1, first_horizontal_line2_x1, first_horizontal_line2_y1, first_horizontal_line2_x2, first_horizontal_line2_y2, true);
        iPrinter.drawLine(1, first_horizontal_line3_x1, first_horizontal_line3_y1, first_horizontal_line3_x2, first_horizontal_line3_y2, true);
        iPrinter.drawLine(1, first_horizontal_line4_x1, first_horizontal_line4_y1, first_horizontal_line4_x2, first_horizontal_line4_y2, true);
        iPrinter.drawLine(1, first_horizontal_line5_x1, first_horizontal_line5_y1, first_horizontal_line5_x2, first_horizontal_line5_y2, true);
        iPrinter.drawLine(1, first_horizontal_line6_x1, first_horizontal_line6_y1, first_horizontal_line6_x2, first_horizontal_line6_y2, true);
        iPrinter.drawLine(1, first_vertical_line1_x1, first_vertical_line1_y1, first_vertical_line1_x2, first_vertical_line1_y2, true);
        iPrinter.drawLine(1, first_vertical_line2_x1, first_vertical_line2_y1, first_vertical_line2_x2, first_vertical_line2_y2, true);
        iPrinter.drawLine(1, first_vertical_line3_x1, first_vertical_line3_y1, first_vertical_line3_x2, first_vertical_line3_y2, true);

//        if(!Utility.isEmpty(charge) && Double.parseDouble(charge) != 0) {
//            iPrinter.drawText(first_text1_x, first_text1_y/2, first_text1 + charge, 4, 0, 0, true, false);
//        }
        iPrinter.drawText(first_text1_x, first_text1_y / 2 + 60, "订单：" + order.getId(), 2, 0, 0, false, false);
        String barcode = addSpace2Barcode2(num);
        iPrinter.drawText(first_barcode1_x, first_text2_y, barcode, 9, 0, 0, false, false);

        int x = calculateX(page_left, page_right, bigChar, first_text3_size);
        iPrinter.drawText(x, first_text3_y, bigChar, 9, 0, 1, false, false);

        x = calculateX(page_left, first_vertical_line2_x1, ztShopName, first_text4_size);
        iPrinter.drawText(x, first_text4_y, ztShopName, 3, 0, 0, false, false);

        String time = order.getTime();
        x = calculateX(first_vertical_line2_x1, page_right, time.substring(0,time.indexOf(" ")), first_text5_size / 2);
        iPrinter.drawText(x, first_text5_y, time.substring(0,time.indexOf(" ")), 3, 0, 0, false, false);

        iPrinter.drawText(first_text6_x, first_text7_y, first_text6, 2, 0, 0, false, false);
        iPrinter.drawText(first_text7_x, first_text8_y, first_text7, 2, 0, 0, false, false);
        iPrinter.drawText(first_text10_x, first_text10_y, order.getName() + "  "+order.getPhone(), 2, 0, 1, false, false);

        printChangeLineText(iPrinter, order.getAddress(), first_text12_x, first_text12_y, first_text12_size, 19, 1, 1);
        iPrinter.drawText(first_text13_x, first_text14_y, first_text13, 2, 0, 0, false, false);
        iPrinter.drawText(first_text14_x, first_text15_y, first_text14, 2, 0, 0, false, false);
        iPrinter.drawText(first_text17_x, first_text17_y, order.getSenderName() + "  "+ order.getSenderPhone(), 8, 0, 0, false, false);

        printChangeLineText(iPrinter, order.getSenderAddress(), first_text19_x, first_text19_y, first_text19_size, 23, 8, 0);
        iPrinter.drawText(first_text20_x, first_text20_y, first_text20, 2, 0, 0, false, false);
        iPrinter.drawText(first_text21_x, first_text21_y, first_text21, 2, 0, 0, false, false);
        iPrinter.drawText(first_text22_x, first_text22_y, first_text22 + order.getArticleInfo(), 1, 0, 0, false, false);
        String weight = TextUtils.isEmpty(order.getCharging_weight()) ?"":order.getCharging_weight()+"kg";
        iPrinter.drawText(first_text23_x, first_text23_y, first_text23 + weight, 1, 0, 0, false, false);
        iPrinter.drawText(first_text24_x, first_text24_y, first_text24, 1, 0, 0, false, false);
        iPrinter.drawText(first_text25_x, first_text25_y, first_text25+charge, 1, 0, 0, false, false);
        iPrinter.drawText(first_text27_x, first_text27_y, first_text27, 2, 0, 0, false, false);
        iPrinter.drawText(first_text28_x+16, first_text28_y+12, first_text28, 3, 0, 1, false, false);

        iPrinter.drawBarCode(first_barcode1_x, first_barcode1_y, num, 1, 0, 3, 80);
    }
    
    //12345  1234  123
    protected String addSpace2Barcode2(String barcode) {
        StringBuilder sb = new StringBuilder(barcode);
        sb.insert(9, " ");
        sb.insert(6, " ");
        sb.insert(3, " ");
        return sb.toString();
    }

    //横向居中
    protected int calculateX(int startX, int endX, String text, int height) {
        int re = 0;
        if (text != null && text.length() > 0) {
            int x = text.length() * height;
            re = (endX - startX - x) / 2 + startX;
        }
        if (re <= 0) {
            re = 10;
        }
        return re;
    }

    /**
     * 中通打印第二联
     */
    private void printSecondPager(String num, String bigChar, String charge, Order order) {
        iPrinter.drawLine(1, second_horizontal_line1_x1, second_horizontal_line1_y1, second_horizontal_line1_x2, second_horizontal_line1_y2, true);
        iPrinter.drawLine(1, second_horizontal_line2_x1, second_horizontal_line2_y1, second_horizontal_line2_x2, second_horizontal_line2_y2, true);
        iPrinter.drawLine(1, second_horizontal_line3_x1, second_horizontal_line3_y1, second_horizontal_line3_x2, second_horizontal_line3_y2, true);
        iPrinter.drawLine(1, second_horizontal_line4_x1, second_horizontal_line4_y1, second_horizontal_line4_x2, second_horizontal_line4_y2, true);
        iPrinter.drawLine(1, second_vertical_line1_x1, second_vertical_line1_y1, second_vertical_line1_x2, second_vertical_line1_y2, true);
        iPrinter.drawLine(1, second_vertical_line2_x1, second_vertical_line2_y1, second_vertical_line2_x2, second_vertical_line2_y2, true);
        iPrinter.drawLine(1, second_vertical_line3_x1-8, second_vertical_line3_y1, second_vertical_line3_x2-8, second_vertical_line3_y2, true);
        iPrinter.drawLine(1, second_vertical_line4_x1, second_vertical_line4_y1, second_vertical_line4_x2, second_vertical_line4_y2, true);

        iPrinter.drawBarCode(third_barcode1_x, second_horizontal_line1_y1+6, num, 1, 0, 2, 30); //快宝原来类型是8，线宽是1
        String barcode = addSpace2Barcode2(num);
        iPrinter.drawText(third_barcode1_x+8, second_horizontal_line1_y1+40, barcode, 2, 0, 0, false, false);
        iPrinter.drawText(second_text2_x, second_horizontal_line1_y1+6, second_text2+order.getId(), 2, 0, 0, false, false);
        iPrinter.drawText(second_text3_x, second_text3_y, second_text3, 1, 0, 0, false, false);
        iPrinter.drawText(second_text4_x, second_text4_y, order.getName() + "  "+order.getPhone(), 1, 0, 0, false, false);
        printChangeLineText(iPrinter, order.getAddress(), second_text5_x, second_text5_y, second_text5_size, 14, 1, 0);
        iPrinter.drawText(second_text6_x, second_text6_y, second_text6, 1, 0, 0, false, false);
        iPrinter.drawText(second_text7_x, second_text7_y, order.getSenderName() + "  "+order.getSenderPhone(), 1, 0, 0, false, false);
        printChangeLineText(iPrinter, order.getSenderAddress(), second_text8_x, second_text8_y, second_text8_size, 14, 1, 0);
        iPrinter.drawText(second_text9_x, second_text9_y, second_text9, 1, 0, 0, false, false);
        iPrinter.drawText(second_text10_x, second_text10_y, second_text10, 1, 0, 0, false, false);
        iPrinter.drawText(second_text11_x, second_text11_y, second_text11, 1, 0, 0, false, false);
        iPrinter.drawText(second_text12_x, second_text12_y, second_text12, 1, 0, 0, false, false);
        int x = 10;
      //  if (!Utility.isEmpty(order.getArticleInfo())) {//内容品名，暂时没有
            x = calculateX(page_left, second_vertical_line2_x1, order.getArticleInfo(), second_text14_size);
            iPrinter.drawText(x, second_text14_y, order.getArticleInfo(), 1, 0, 0, false, false);
     //   }

        x = calculateX(second_vertical_line2_x1, second_vertical_line3_x1, order.getCharging_weight(), second_text15_size / 2);
        iPrinter.drawText(x, second_text15_y, order.getCharging_weight(), 1, 0, 0, false, false);

        if (order.getPrice() != null) {
            x = calculateX(second_vertical_line3_x1, second_vertical_line4_x1, "", second_text16_size / 2);
            iPrinter.drawText(x, second_text16_y, "", 1, 0, 0, false, false);
        }


        if (charge != null && charge.length() > 0) {//代收货款有可能没有
            x = calculateX(second_vertical_line4_x1, second_vertical_line5_x1, charge, second_text17_size / 2);
            iPrinter.drawText(x, second_text17_y, charge, 1, 0, 0, false, false);
        }
        if (order.getPrice() != null) {
            x = calculateX(second_vertical_line5_x1, page_right, "", second_text18_size / 2);
            iPrinter.drawText(x, second_text18_y, "", 1, 0, 0, false, false);
        }

    }

    /**
     * 中通打印第三联
     */
    protected void printThirdPager(String num, String bigChar, String charge, Order order) {
        iPrinter.drawLine(1, third_horizontal_line1_x1, third_horizontal_line1_y1, third_horizontal_line1_x2, third_horizontal_line1_y2, true);
        iPrinter.drawLine(1, third_horizontal_line2_x1, third_horizontal_line2_y1, third_horizontal_line2_x2, third_horizontal_line2_y2, true);
        iPrinter.drawLine(1, third_horizontal_line3_x1, third_horizontal_line3_y1, third_horizontal_line3_x2, third_horizontal_line3_y2, true);
        iPrinter.drawLine(1, third_horizontal_line4_x1, third_horizontal_line4_y1, third_horizontal_line4_x2, third_horizontal_line4_y2, true);
        iPrinter.drawLine(1, third_vertical_line1_x1, third_vertical_line1_y1, third_vertical_line1_x2, third_vertical_line1_y2, true);
        iPrinter.drawLine(1, third_vertical_line2_x1, third_vertical_line2_y1, third_vertical_line2_x2, third_vertical_line2_y2, true);
        iPrinter.drawLine(1, third_vertical_line3_x1, third_vertical_line3_y1, third_vertical_line3_x2, third_vertical_line3_y2, true);
        iPrinter.drawLine(1, third_vertical_line4_x1, third_vertical_line4_y1, third_vertical_line4_x2, third_vertical_line4_y2, true);
        iPrinter.drawLine(1, third_vertical_line5_x1, third_vertical_line5_y1, third_vertical_line5_x2, third_vertical_line5_y2, true);
        iPrinter.drawLine(1, third_vertical_line6_x1, third_vertical_line6_y1, third_vertical_line6_x2, third_vertical_line6_y2, true);

        String barcode = addSpace2Barcode2(num);
        iPrinter.drawText(third_text1_x, third_text1_y, barcode, 2, 0, 0, false, false);
        iPrinter.drawText(third_text2_x, third_text2_y, third_text2, 1, 0, 0, false, false);
        iPrinter.drawText(third_text3_x, third_text3_y, order.getName()+"  "+order.getPhone(), 1, 0, 0, false, false);
        printChangeLineText(iPrinter, order.getAddress(), third_text4_x, third_text4_y, third_text4_size, 14, 1, 0);
        iPrinter.drawText(third_text5_x, third_text5_y, third_text5, 1, 0, 0, false, false);
        iPrinter.drawText(third_text6_x, third_text6_y, order.getSenderName()+"  "+order.getSenderPhone(), 1, 0, 0, false, false);
        printChangeLineText(iPrinter, order.getSenderAddress(), third_text7_x, third_text7_y, third_text7_size, 14, 1, 0);
        iPrinter.drawText(third_text9_x, third_text9_y, third_text9, 1, 0, 0, false, false);
        iPrinter.drawText(third_text10_x, third_text10_y, third_text10, 1, 0, 0, false, false);
        iPrinter.drawText(third_text11_x, third_text11_y, third_text11, 1, 0, 0, false, false);
        iPrinter.drawText(third_text12_x, third_text12_y, third_text12, 1, 0, 0, false, false);
      //  if(order.getIsMonthly() == 1) {
            iPrinter.drawText(third_text13_x, third_text13_y, "月结运费(￥)", 1, 0, 0, false, false);
      //  }else{
        //    iPrinter.drawText(third_text13_x, third_text13_y, "现付运费(￥)", 1, 0, 0, false, false);
      //  }
        int x = 10;
//        if (!Utility.isEmpty(order.getArticleInfo())) {
//            x = calculateX(page_left, third_vertical_line2_x1, order.getArticleInfo(), third_text14_size);
//            iPrinter.drawText(x, third_text14_y, order.getArticleInfo(), 1, 0, 0, false, false);
//        }
        x = calculateX(third_vertical_line2_x1, third_vertical_line3_x1, order.getCharging_weight(), third_text15_size / 2);
        iPrinter.drawText(x, third_text15_y, order.getCharging_weight(), 1, 0, 0, false, false);

        if (order.getPrice() != null) {
            x = calculateX(third_vertical_line3_x1, third_vertical_line4_x1, "", third_text16_size / 2);
            iPrinter.drawText(x, third_text16_y, "", 1, 0, 0, false, false);
        }

        if (charge != null) {
            x = calculateX(third_vertical_line4_x1, third_vertical_line5_x1, charge, third_text17_size / 2);
            iPrinter.drawText(x, third_text17_y, charge, 1, 0, 0, false, false);
        }

        if (!TextUtils.isEmpty(order.getFreight())) {
            x = calculateX(third_vertical_line5_x1, page_right, order.getFreight(), third_text18_size / 2);
            iPrinter.drawText(x, third_text18_y, order.getFreight(), 1, 0, 0, false, false);
        }

        iPrinter.drawText(third_text19_x, third_text19_y, third_text19, 1, 0, 0, false, false);
        iPrinter.drawText(third_text20_x, third_text20_y, third_text20, 1, 0, 0, false, false);
        iPrinter.drawText(third_text21_x, third_text21_y, third_text21, 1, 0, 0, false, false);
        iPrinter.drawText(third_text22_x, third_text22_y, third_text22, 1, 0, 0, false, false);

        iPrinter.drawBarCode(third_barcode1_x, third_barcode1_y, num, 1, 0, 2, 60); //原来是8，线宽是1
    }

    /**
     * 方法用于打印文字太多换行打印
     *
     * @param printPP_cpcl
     * @param text
     * @param x
     * @param y
     * @param height       字体实际高度
     * @param num          换行字数
     * @param size         对应打印机打印文字高度参数
     */
    protected void printChangeLineText(PrintPP_CPCL printPP_cpcl, String text, int x, int y, int height, int num, int size, int bold) {
        if (height == 16) {
            size = 1;
        } else if (height == 24) {
            size = 2;
        }
        if (text != null && num > 0 && text.length() > num) {
            StringBuilder sb = new StringBuilder(text);
            String subs = sb.substring(0, num);
            //打印
            printPP_cpcl.drawText(x, y, subs, size, 0, bold, false, false);
            sb.delete(0, num);
            String remainming = sb.toString();
            if (remainming.length() > num) {
                //x,y设置
                y += height;
                printChangeLineText(printPP_cpcl, remainming, x, y, height, num, size, bold);
            } else {
                //打印
                y += height;
                printPP_cpcl.drawText(x, y, remainming, size, 0, bold, false, false);
            }
        } else {
            printPP_cpcl.drawText(x, y, text, size, 0, bold, false, false);
        }
    }

}
