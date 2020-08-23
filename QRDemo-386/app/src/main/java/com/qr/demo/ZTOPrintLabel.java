package com.qr.demo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.List;

import com.qr.print.*;

//import printpp.printpp_yt.PrintPP_CPCL;

/**
 * 万琛  QR
 * 3联电子面单.
 */
public class ZTOPrintLabel  {
	
	
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
	    protected String first_text1 = "代收金额反显";
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
	    private PrintPP_CPCL printPP_cpcl;



    String mAddress;

    public ZTOPrintLabel(){}
    
    public ZTOPrintLabel(PrintPP_CPCL printPP_cpcl ){
    	
    	this.printPP_cpcl=printPP_cpcl;
    }

    protected void printPager() {
        printFirstPager();
        printSecondPager();
        printThirdPager();
    	//反显测试
//        printPP_cpcl.drawText(250, 500, "我是小程序", 4, 0, 0,true, false);
//        printPP_cpcl.drawText(250, 500, "我是小程序", 4, 1, 0,true, false);
//        printPP_cpcl.drawText(250, 500, "我是小程序", 4, 2, 0,true, false);
//        printPP_cpcl.drawText(250, 500, "我是小程序", 4, 3, 0,true, false);
    }

    protected void printFirstPager() {
        printFirstPagerLine();
        printFirstPagerText();
        printFirstPagerBarcode();
    }

    protected void printSecondPager() {
        printSecondPagerLine();
        printSecondPagerText();
        printSecondPagerBarcode();
    }

    protected void printThirdPager() {
        printThirdPagerLine();
        printThirdPagerText(payMode);
        printthirdPagerBarcode();
    }
   
    public void doPrint() {
     
    			printPP_cpcl.pageSetup(page_width, page_height);
                printPager();
                printPP_cpcl.print(0, 0);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                
        

    }

//    @Override public boolean connect(String macString) {
//        this.mAddress = macString;
//
//        printPP_cpcl.connect(macString);
//        return isConnected();
//    }
//
//    @Override public boolean disconnect() {
//        printPP_cpcl.disconnect();
//        return !isConnected();
//    }
//
//    @Override public boolean isConnected() {
//        return printPP_cpcl.isConnected();
//    }

    //    @Override
//    public boolean doPrint(PrintBean billRecord, ResultCallBack callBack) {
//        super.doPrint(billRecord, callBack);
//        if (!openPrinter(callBack)) {
//            return false;
//        }
//        printPP_cpcl = new PrintPP_CPCL();
//        printPP_cpcl.connect(mAddress);
//        if (!printPP_cpcl.isConnected()) {
//            callBack.onResult(false,"读取蓝牙设备错误");
//            return false;
//        }
//        printPP_cpcl.pageSetup(page_width, page_height);
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//        }
//        printPager(callBack);
//        printPP_cpcl.print(0, 1);
//        printPP_cpcl.disconnect();
//        return true;
//    }



//    public boolean openPrinter( ListResultCallBack callBack) {
//        String BDAddress = mAddress;
//        if (BDAddress == "" || BDAddress == null) {
//            callBack.onResult(false,"没有选择打印机",-1);
//            return false;
//        }
//        BluetoothDevice myDevice;
//        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (myBluetoothAdapter == null) {
//            callBack.onResult(false,"读取蓝牙设备错误",-1);
//            return false;
//        }
//
//        if (myBluetoothAdapter.isDiscovering()) {
//            myBluetoothAdapter.cancelDiscovery();
//        }
//
//
//        myDevice = myBluetoothAdapter.getRemoteDevice(BDAddress);
//        if (myDevice == null) {
//            callBack.onResult(false,"读取蓝牙设备错误",-1);
//            return false;
//        }
//        return true;
//    }


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
    protected void printFirstPagerLine() {
        printPP_cpcl.drawLine(1, first_horizontal_line1_x1, first_horizontal_line1_y1, first_horizontal_line1_x2, first_horizontal_line1_y2, true);
        printPP_cpcl.drawLine(1, first_horizontal_line2_x1, first_horizontal_line2_y1, first_horizontal_line2_x2, first_horizontal_line2_y2, true);
        printPP_cpcl.drawLine(1, first_horizontal_line3_x1, first_horizontal_line3_y1, first_horizontal_line3_x2, first_horizontal_line3_y2, true);
        printPP_cpcl.drawLine(1, first_horizontal_line4_x1, first_horizontal_line4_y1, first_horizontal_line4_x2, first_horizontal_line4_y2, true);
        printPP_cpcl.drawLine(1, first_horizontal_line5_x1, first_horizontal_line5_y1, first_horizontal_line5_x2, first_horizontal_line5_y2, true);
        printPP_cpcl.drawLine(1, first_horizontal_line6_x1, first_horizontal_line6_y1, first_horizontal_line6_x2, first_horizontal_line6_y2, true);
        printPP_cpcl.drawLine(1, first_vertical_line1_x1, first_vertical_line1_y1, first_vertical_line1_x2, first_vertical_line1_y2, true);
        printPP_cpcl.drawLine(1, first_vertical_line2_x1, first_vertical_line2_y1, first_vertical_line2_x2, first_vertical_line2_y2, true);
        printPP_cpcl.drawLine(1, first_vertical_line3_x1, first_vertical_line3_y1, first_vertical_line3_x2, first_vertical_line3_y2, true);
    }


    protected void printFirstPagerText() {
       printPP_cpcl.drawText(first_text1_x - 50, first_text1_y - 10, first_text1, 4, 0, 0,true, false);
//        printPP_cpcl.drawText(first_text1_x - 50, first_text1_y - 10, first_text1, 4, 1, 0,true, false);
//        printPP_cpcl.drawText(first_text1_x - 50, first_text1_y - 10, first_text1, 4, 2, 0,true, false);
//        printPP_cpcl.drawText(first_text1_x - 50, first_text1_y - 10, first_text1, 4, 3, 0,true, false);
 
    	
    	
       // printPP_cpcl.drawText(first_text1_x - 50, first_text1_y + 65, first_billcode, 2, 0, 0, false, false);
        String barcode = addSpace2Barcode2(first_text2);
        printPP_cpcl.drawText(first_text2_x - 58, first_text2_y, barcode, 9, 0, 0, false, false);

        int x = calculateX(page_left, page_right, first_text3, first_text3_size);
        printPP_cpcl.drawText(x, first_text3_y, first_text3, 9, 0, 1, false, false);

        x = calculateX(page_left, first_vertical_line2_x1, first_text4, first_text4_size);
        printPP_cpcl.drawText(x, first_text4_y, first_text4, 3, 0, 1, false, false);

        x = calculateX(first_vertical_line2_x1, page_right, first_text5, first_text5_size / 2);
        printPP_cpcl.drawText(x, first_text5_y, first_text5, 3, 0, 0, false, false);

        printPP_cpcl.drawText(first_text6_x, first_text6_y, first_text6, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text7_x, first_text7_y, first_text7, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text8_x, first_text8_y, first_text8, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text9_x, first_text9_y, first_text9, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text10_x, first_text10_y, first_text10, 2, 0, 1, false, false);//zhougf
        printPP_cpcl.drawText(first_text11_x, first_text11_y, first_text11, 2, 0, 1, false, false);
        printChangeLineTextBold(printPP_cpcl, first_text12, first_text12_x, first_text12_y + 5, first_text12_size, 20, 1);
        printPP_cpcl.drawText(first_text13_x, first_text13_y, first_text13, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text14_x, first_text14_y, first_text14, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text15_x, first_text15_y, first_text15, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text16_x, first_text16_y, first_text16, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text17_x, first_text17_y, first_text17, 8, 0, 0, false, false);
        printPP_cpcl.drawText(first_text18_x, first_text18_y, first_text18, 8, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, first_text19, first_text19_x, first_text19_y + 5, first_text19_size, 24, 8);
        printPP_cpcl.drawText(first_text20_x, first_text20_y, first_text20, 2, 0, 0, false, false);
        printPP_cpcl.drawText(first_text21_x, first_text21_y, first_text21, 2, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, first_text22, first_text22_x, first_text22_y, 18, 12, 13);
//        printPP_cpcl.drawText(first_text22_x, first_text22_y, first_text22, 1, 0, 0, false, false);
        printPP_cpcl.drawText(first_text23_x, first_text24_y, first_text23, 1, 0, 0, false, false);
        printPP_cpcl.drawText(first_text24_x, first_text25_y, first_text24, 1, 0, 0, false, false);
        printPP_cpcl.drawText(first_text25_x, first_text26_y, first_text25, 1, 0, 0, false, false);
//        printPP_cpcl.drawText(first_text26_x, first_text26_y, first_text26, 1, 0, 0, false, false);
        printPP_cpcl.drawText(first_text27_x, first_text27_y, first_text27, first_text27_size, 0, 0, false, false);
        printPP_cpcl.drawText(first_text28_x, first_text28_y + 20, first_text28, 3, 0, 1, false, false);
    }

  
    protected void printFirstPagerBarcode() {
        printPP_cpcl.drawBarCode(first_barcode1_x, first_barcode1_y, first_text2, 1, 0, 4, 90);
    }

  
    protected void printSecondPagerLine() {
        printPP_cpcl.drawLine(1, second_horizontal_line1_x1, second_horizontal_line1_y1, second_horizontal_line1_x2, second_horizontal_line1_y2, true);
        printPP_cpcl.drawLine(1, second_horizontal_line2_x1, second_horizontal_line2_y1 + 20, second_horizontal_line2_x2, second_horizontal_line2_y2 + 20, true);
        printPP_cpcl.drawLine(1, second_horizontal_line3_x1, second_horizontal_line3_y1, second_horizontal_line3_x2, second_horizontal_line3_y2, true);
        printPP_cpcl.drawLine(1, second_horizontal_line4_x1, second_horizontal_line4_y1, second_horizontal_line4_x2, second_horizontal_line4_y2, true);
        printPP_cpcl.drawLine(1, second_vertical_line1_x1, second_vertical_line1_y1, second_vertical_line1_x2, second_vertical_line1_y2, true);
//        printPP_cpcl.drawLine(1, second_vertical_line2_x1, second_vertical_line2_y1, second_vertical_line2_x2, second_vertical_line2_y2, true);
        printPP_cpcl.drawLine(1, second_vertical_line3_x1, second_vertical_line3_y1, second_vertical_line3_x2, second_vertical_line3_y2, true);
        printPP_cpcl.drawLine(1, second_vertical_line4_x1, second_vertical_line4_y1, second_vertical_line4_x2, second_vertical_line4_y2, true);
        printPP_cpcl.drawLine(1, second_vertical_line5_x1, second_vertical_line5_y1, second_vertical_line5_x2, second_vertical_line5_y2, true);
    }


 
    protected void printSecondPagerText() {
        String barcode = addSpace2Barcode2(second_text1);
        printPP_cpcl.drawText(second_text1_x + 50, second_text1_y + 35, barcode, 2, 0, 0, false, false);
        printPP_cpcl.drawText(second_text2_x, second_text2_y + 8 , second_text2, 2, 0, 0, false, false);
        printPP_cpcl.drawText(second_text3_x, second_text3_y + 30, second_text3, 1, 0, 0, false, false);
        printPP_cpcl.drawText(second_text4_x, second_text4_y + 30, second_text4, 1, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, second_text5, second_text5_x, second_text5_y + 30, second_text5_size, 17, 1);
        printPP_cpcl.drawText(second_text6_x, second_text6_y + 30, second_text6, 1, 0, 0, false, false);
        printPP_cpcl.drawText(second_text7_x, second_text7_y + 30, second_text7, 1, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, second_text8, second_text8_x, second_text8_y + 30, second_text8_size, 17, 1);
        printPP_cpcl.drawText(second_text9_x + 60, second_text9_y, second_text9, 1, 0, 0, false, false);
        printPP_cpcl.drawText(second_text11_x, second_text10_y, second_text10, 1, 0, 0, false, false);
        printPP_cpcl.drawText(second_text12_x, second_text11_y, second_text11, 1, 0, 0, false, false);
        printPP_cpcl.drawText(second_text13_x, second_text12_y, second_text12, 1, 0, 0, false, false);
//        printPP_cpcl.drawText(second_text13_x, second_text13_y, second_text13, 1, 0, 0, false, false);        //到付不显示
        int x = 10;
        if (second_text14 != null) {//内容品名，暂时没有
            x = calculateX(page_left, second_vertical_line3_x1, second_text14, second_text14_size);
            if(second_text14.length() < 14){
                printPP_cpcl.drawText(x, second_text14_y, second_text14, 1, 0, 0, false, false);
            }else {
                printChangeLineText(printPP_cpcl, second_text14, x, second_text14_y, 18, 13, 13);
            }
        }

        x = calculateX(second_vertical_line3_x1, second_vertical_line4_x1, second_text15, second_text15_size / 2);
        printPP_cpcl.drawText(x, second_text15_y, second_text15, 1, 0, 0, false, false);

        if (second_text16 != null) {
            x = calculateX(second_vertical_line4_x1, second_vertical_line5_x1, second_text16, second_text16_size / 2);
            printPP_cpcl.drawText(x, second_text16_y, second_text16, 1, 0, 0, false, false);
        }


        if (second_text17 != null && second_text17.length() > 0) {//代收货款有可能没有
            x = calculateX(second_vertical_line5_x1, page_right, second_text17, second_text17_size / 2);
            printPP_cpcl.drawText(x, second_text17_y, second_text17, 1, 0, 0, false, false);
        }
//        if (second_text18 != null) {
//            x = calculateX(second_vertical_line5_x1, page_right, second_text18, second_text18_size / 2);
//            printPP_cpcl.drawText(x, second_text18_y, second_text18, 1, 0, 0, false, false);
//        }

    }


    protected void printSecondPagerBarcode() {
        printPP_cpcl.drawBarCode(third_barcode1_x, second_text1_y - 10, first_text2, 1, 0, 2, 40);
    }


    protected void printThirdPagerLine() {
        printPP_cpcl.drawLine(1, third_horizontal_line1_x1, third_horizontal_line1_y1, third_horizontal_line1_x2, third_horizontal_line1_y2, true);
        printPP_cpcl.drawLine(1, third_horizontal_line2_x1, third_horizontal_line2_y1, third_horizontal_line2_x2, third_horizontal_line2_y2, true);
        printPP_cpcl.drawLine(1, third_horizontal_line3_x1, third_horizontal_line3_y1, third_horizontal_line3_x2, third_horizontal_line3_y2, true);
        printPP_cpcl.drawLine(1, third_horizontal_line4_x1, third_horizontal_line4_y1 + 20, third_horizontal_line4_x2, third_horizontal_line4_y2 + 20, true);
        printPP_cpcl.drawLine(1, third_vertical_line1_x1, third_vertical_line1_y1, third_vertical_line1_x2, third_vertical_line1_y2, true);
        printPP_cpcl.drawLine(1, third_vertical_line2_x1, third_vertical_line2_y1, third_vertical_line2_x2, third_vertical_line2_y2 + 20, true);
        printPP_cpcl.drawLine(1, third_vertical_line3_x1, third_vertical_line3_y1, third_vertical_line3_x2, third_vertical_line3_y2 + 20, true);
        printPP_cpcl.drawLine(1, third_vertical_line4_x1, third_vertical_line4_y1, third_vertical_line4_x2, third_vertical_line4_y2 + 20, true);
        printPP_cpcl.drawLine(1, third_vertical_line5_x1, third_vertical_line5_y1, third_vertical_line5_x2, third_vertical_line5_y2 + 20, true);
        printPP_cpcl.drawLine(1, third_vertical_line6_x1, third_vertical_line6_y1 + 20, third_vertical_line6_x2, third_vertical_line6_y2, true);
    }


  
    protected void printThirdPagerText(String payType) {
        String barcode = addSpace2Barcode2(third_text1);
        printPP_cpcl.drawText(third_text1_x, third_text1_y, barcode, 2, 0, 0, false, false);
        printPP_cpcl.drawText(third_text2_x, third_text2_y, third_text2, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text3_x, third_text3_y, third_text3, 1, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, third_text4, third_text4_x, third_text4_y, third_text4_size, 17, 1);
        printPP_cpcl.drawText(third_text5_x, third_text5_y, third_text5, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text6_x, third_text6_y, third_text6, 1, 0, 0, false, false);
        printChangeLineText(printPP_cpcl, third_text7, third_text7_x, third_text7_y, third_text7_size, 17, 1);
        printPP_cpcl.drawText(third_text9_x, third_text9_y, third_text9, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text10_x, third_text10_y, third_text10, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text11_x, third_text11_y, third_text11, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text12_x, third_text12_y, third_text12, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text13_x, third_text13_y, payType + third_text13, 1, 0, 0, false, false);
        int x = 10;
        if (third_text14 != null) {
            x = calculateX(page_left, third_vertical_line2_x1, third_text14, third_text14_size);
            printChangeLineText(printPP_cpcl, third_text14, x, third_text14_y, third_text7_size, 6, 1);
//            printPP_cpcl.drawText(x, third_text14_y, third_text14, 1, 0, 0, false, false);
        }
        x = calculateX(third_vertical_line2_x1, third_vertical_line3_x1, third_text15, third_text15_size / 2);
        printPP_cpcl.drawText(x, third_text15_y, third_text15, 1, 0, 0, false, false);

        if (third_text16 != null) {
            x = calculateX(third_vertical_line3_x1, third_vertical_line4_x1, third_text16, third_text16_size / 2);
            printPP_cpcl.drawText(x, third_text16_y, third_text16, 1, 0, 0, false, false);
        }

        if (third_text17 != null) {
            x = calculateX(third_vertical_line4_x1, third_vertical_line5_x1, third_text17, third_text17_size / 2);
            printPP_cpcl.drawText(x, third_text17_y, third_text17, 1, 0, 0, false, false);
        }

        if (third_text18 != null) {
            x = calculateX(third_vertical_line5_x1, page_right, third_text18, third_text18_size / 2);
            printPP_cpcl.drawText(x, third_text18_y, third_text18, 1, 0, 0, false, false);
        }

        printPP_cpcl.drawText(third_text19_x, third_text19_y + 20, third_text19, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text20_x, third_text20_y + 20, third_text20, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text21_x, third_text21_y + 20, third_text21, 1, 0, 0, false, false);
        printPP_cpcl.drawText(third_text22_x, third_text22_y, third_text22, 1, 0, 0, false, false);

    }

   
    protected void printthirdPagerBarcode() {
        printPP_cpcl.drawBarCode(third_barcode1_x, third_barcode1_y, first_text2, 1, 0, 2, 60);
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
    protected void printChangeLineText(PrintPP_CPCL printPP_cpcl, String text, int x, int y, int height, int num, int size) {
        if (height == 16) {
            size = 1;
        } else if (height == 24) {
            size = 2;
        }
        if (text != null && num > 0 && text.length() > num) {
            StringBuilder sb = new StringBuilder(text);
            String subs = sb.substring(0, num);
            //打印
            printPP_cpcl.drawText(x, y, subs, size, 0, 0, false, false);
            sb.delete(0, num);
            String remainming = sb.toString();
            if (remainming.length() > num) {
                //x,y设置
                y += height;
                printChangeLineText(printPP_cpcl, remainming, x, y, height, num, size);
            } else {
                //打印
                y += height;
                printPP_cpcl.drawText(x, y, remainming, size, 0, 0, false, false);
            }
        } else {
            printPP_cpcl.drawText(x, y, text, size, 0, 0, false, false);
        }
    }
    protected void printChangeLineTextBold(PrintPP_CPCL printPP_cpcl, String text, int x, int y, int height, int num, int size) {
        if (height == 16) {
            size = 1;
        } else if (height == 24) {
            size = 2;
        }
        if (text != null && num > 0 && text.length() > num) {
            StringBuilder sb = new StringBuilder(text);
            String subs = sb.substring(0, num);
            //打印
            printPP_cpcl.drawText(x, y, subs, size, 0, 1, false, false);
            sb.delete(0, num);
            String remainming = sb.toString();
            if (remainming.length() > num) {
                //x,y设置
                y += height;
                printChangeLineTextBold(printPP_cpcl, remainming, x, y, height, num, size);
            } else {
                //打印
                y += height;
                printPP_cpcl.drawText(x, y, remainming, size, 0, 1, false, false);
            }
        } else {
            printPP_cpcl.drawText(x, y, text, size, 0, 1, false, false);
        }
    }


}
