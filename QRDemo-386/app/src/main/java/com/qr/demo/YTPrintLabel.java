package com.qr.demo;

import com.qr.print.*;

import android.graphics.Bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory; 

//import printpp.printpp_yt.PrintPP_CPCL;

public class YTPrintLabel {
	public static void Lable(PrintPP_CPCL iPrinter){

	   String s = 	iPrinter.printerStatus();

		iPrinter.pageSetup(586, 1436-8);
		 //第一联
//	 	iPrinter.drawBox(2, 0, 1, 568+16, 256+128+168+128); //第一联边框
	 	
	 	iPrinter.drawLine(2, 0, 240, 568+16, 240,false);//第一联横线1
		
		iPrinter.drawLine(2, 0, 384, 568+16, 384,false);//第一联横线2
		
		iPrinter.drawLine(2, 0, 552, 568-32+8, 552,false);//第一联横线3
		
		iPrinter.drawLine(2, 40, 384, 40, 680,false);//第一联竖线1，从左到右
		
		iPrinter.drawLine(2, 408, 552, 408, 680,false);//第一联竖线2，从左到右
		
		iPrinter.drawLine(2, 568-32+8, 384, 568-32+8, 680,false);//第一联竖线3，从左到右

		//二维码信息
//		iPrinter.drawQrCode(2+160, 16, "www.yto.net.cn", 0, 4, 5);
	
		//代收货款
//		iPrinter.drawText(2+320+16, 16+8, "代收货款", 3,0, 1,false,false);
//				//金额
//		iPrinter.drawText(2+320+16, 48+8+8, "金额：", 3,0,  0, false, false);
//				//具体金额
//		iPrinter.drawText(2+8+400+16, 48+8+8, "0.0元", 3,0,  1, false, false);
		
	
		//目的地条码
//		iPrinter.drawBarCode(2+320+16, 20,"210045", 1,  0, 3, 60);
//		iPrinter.drawQrCode(2+320+16, 20,"210045", 0, 0, 0);
		iPrinter.drawQrCode(2+320+16, 20,"4060002550288", 0, 4, 20);
		//目的地
//		iPrinter.drawText(2+320+16+32+16, 48+8+4+16+8, "210045", 3,0, 1,false,false);
		//三段码
		iPrinter.drawText(2+20, 128+16+8, "210-123-000", 6,0, 0, false, false);
		
		
		if("123456789012".length()==12||"1234567891012".length()==10){
		//条码
			iPrinter.drawBarCode(32+100, 240+16,"123456789012", 1,  0, 3, 80);
		//条码字符
			iPrinter.drawText(2+96+76+32-32, 340, "123456789012", 3,0,  0, false, false);
		}else{
			//条码
			iPrinter.drawBarCode(32, 240+16,"123456789012123456", 1,  0, 3, 80);
			//条码字符
			iPrinter.drawText(2+96+76+32-32-64, 340, "123456789012123456", 3,0,  0, false, false);
		}
		//收件人
		iPrinter.drawText(2+4,384+28,32,120,"收件人",3,0,  1,false,false);
		//收件人姓名＋电话，最终实施时请用变量替换
		iPrinter.drawText(2+4+32+8,264+128,480,32,"张三"+" "+"18212345678",3,0, 1,false,false);
		//收件地址 ，最终实施时请用变量替换
		iPrinter.drawText(2+4+32+8,372+40+22,448,120,"湖南省"+"湘潭市"+"雨湖区"+" "+"雨湖区政府1234号",3,0,  1,false,false);
		//寄件人
		iPrinter.drawText(2+8,552+22,32,96,"寄件人",2,0,  0,false,false);
		//寄件人姓名＋电话，
		iPrinter.drawText(2+4+32+8,552+8,480,24,"李四"+" "+"15188889999",2,0, 0,false,false);
		//寄件人地址
		iPrinter.drawText(2+4+32+8,552+40,344,112,"上海"+"上海市"+"青浦区"+" "+"华徐公路3029弄28号",2,0,  0,false,false);
//		//签收人
		iPrinter.drawText(2+424-8,552+8,"签收人：",2,0,  0,false,false);
//		//日期
		iPrinter.drawText(2+424,680-26-8,"日期：",2,0,  0,false,false);
//		//派件联
		iPrinter.drawText(568-32+3+8,384+128,32,96,"派件联",2,0,0,false,false);
//		//虚线
//		iPrinter.drawLine(2, 2, 680+8, 568, 680+8,false);
//		
//		//第二联
//		iPrinter.drawBox(2, 0,680+16, 568+16, 680+16+288);//第二联边框 
		
		iPrinter.drawLine(2,0, 696+32, 568+16, 696+32,false);//第二联横线1，从左到右
		
		iPrinter.drawLine(2,0, 696+160, 568-32+16, 696+160,false);//第二联横线2，从左到右
		
		iPrinter.drawLine(2,40, 696+160+96, 568-32+8, 696+160+96,false);//第二联横线3，从左到右
		
		iPrinter.drawLine(2, 40, 696+32, 40, 696+288,false);//第二联竖线1，从左到右
		
		iPrinter.drawLine(2, 248+42, 696+160+96,248+42, 680+16+288,false);//第二联竖线2，从左到右	
		
		iPrinter.drawLine(2, 568-32+8-96, 696+160,568-32+8-96,696+160+96,false);//第二联竖线3，从左到右	
		
		iPrinter.drawLine(2, 568-32+8, 696+32,568-32+8, 680+16+288,false);//第二联竖线4，从左到右	
		
		//运单号+运单号
		iPrinter.drawText(8,696+3,"运单号："+"1234567890"+" □"+"订单号："+"LP123456789",2,0,  0,false,false);
		//收件人
		iPrinter.drawText(2+4,696+32+16,32,96,"收件人",2,0,  0,false,false);
		//收件人姓名＋电话，最终实施时请用变量替换
		iPrinter.drawText(2+8+32+8,608+128,480,24,"张三"+" "+"18212345678",2,0, 0,false,false);
		//收件地址 ，最终实施时请用变量替换
		iPrinter.drawText(2+8+32+8,696+32+40+2,424,80,"湖南省"+"湘潭市"+"雨湖区"+" "+"雨湖区政府1234号",2,0,  0,false,false);
//		//内容品名
		iPrinter.drawText(2+4,696+160+3,32,120,"内容品名",2,0, 0,false,false);
		//内容品名具体
		iPrinter.drawText(2+4+32+8+4+4,696+160+8,432-100,136,"鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、",2,0, 0,false,false);
		//面单模式 A ：A网
		iPrinter.drawText(568-32+8-96+24,696+160+2,"A",7,0, 0,false,false);
		//数量
		iPrinter.drawText(2+4+32+8,696+160+96+4,"数量："+"1",2,0, 0,false,false);
		//重量
		iPrinter.drawText(248+42+4,696+160+96+4,"重量："+"1"+"kg",2,0, 0,false,false);			
//		//收件联
		iPrinter.drawText(568-32+3+8,696+32+80,32,96,"收件联",2,0,0,false,false);
		//虚线
//		iPrinter.drawLine(2, 2, 992, 568, 992,false);//
		
		//第三联
//		iPrinter.drawBox(2, 0,1000, 568+16, 1000+432-4-16+4);//第三联边框 		
		
		iPrinter.drawLine(2,0, 1096, 568+16, 1096,false);//第三联横线1，从左到右
		
		iPrinter.drawLine(2,0, 1096+104-8+4, 568-32+8, 1096+104-8+4,false);//第三联横线2，从左到右
		
		iPrinter.drawLine(2,0, 1096+104+104-8, 568-32+8, 1096+104+104-8,false);//第三联横线3，从左到右
		
		iPrinter.drawLine(2,40, 1096+104+104+96+4-4-2-8-4,568-32+8, 1096+104+104+96+4-4-2-8-4,false);//第三联横线4，从左到右
		
		
		iPrinter.drawLine(2, 40, 1096, 40, 1432-4-16+4,false);//第三联竖线1，从左到右
		
		iPrinter.drawLine(2, 248+42, 1096+104+104+96-8+4,248+42, 1432-4-16+4,false);//第三联竖线2，从左到右	
		
		iPrinter.drawLine(2, 568-32+8, 1096,568-32+8, 1432-4-16+4,false);//第三联竖线3，从左到右
		
//		iPrinter.drawBarCode(2+250+4-16, 1000+8,mOrderVO.getWaybillNo(), 1,  0, 3, 56);
		//条码数据
//		iPrinter.drawText(2+312, 1008+56+4, mOrderVO.getWaybillNo(), 2,  0,0,false,false);
		
		if("123456789012".length()==12||"1234567891012".length()==10){
			//条码
			iPrinter.drawBarCode(100+140-60, 1000+8,"1234567891012", 1,  0, 3, 56);
			iPrinter.drawText(2+312-60, 1008+56+4, "1234567891012", 2,  0,0,false,false);
		}else{
			iPrinter.drawBarCode(105, 1000+8,"123456789012123456", 1,  0, 3, 56);
			iPrinter.drawText(2+312-64, 1008+56+4, "123456789012123456", 2,  0,0,false,false);
		}
		//收件人
		iPrinter.drawText(2+4,1096+5,32,96,"收件人",2,0,  0,false,false);
		//收件人姓名＋电话，最终实施时请用变量替换
		iPrinter.drawText(2+8+32+8+4+4,1096+8,480,24,"张三"+" "+"18212345678",2,0, 0,false,false);
		//收件地址 ，最终实施时请用变量替换
		iPrinter.drawText(2+8+32+8+4+4,1096+8+24+8,456,64,"湖南省"+"湘潭市"+"雨湖区"+" "+"雨湖区政府1234号",2,0,  0,false,false);
		//寄件人
		iPrinter.drawText(2+4,1096+104+5,32,96,"寄件人",2,0,  0,false,false);
		//寄件人姓名＋电话，
		iPrinter.drawText(2+4+32+8,1096+104+8,480,24,"李四"+" "+"15188889999",2,0, 0,false,false);
		//寄件人地址
		iPrinter.drawText(2+4+32+8,1096+104+8+24+8,456,72,"上海"+"上海市"+"青浦区"+" "+"华徐公路3029弄28号",2,0,  0,false,false);
		//内容品名
		iPrinter.drawText(2+4,1096+104+104+1,32,120,"内容品名",2,0, 0,false,false);
		//订单号
	//	iPrinter.drawText(2+4+32+8,1348,"订单号："+mOrderVO.getOrderNo(),2,0, 0,false,false);
		//内容品名具体
		iPrinter.drawText(2+4+32+8+4+4,1096+104+104+8,432,156,"衣服",2,0, 0,false,false);
		//数量
		iPrinter.drawText(2+4+32+8,1432-32+4-4-8-4,"数量："+"1",2,0, 0,false,false);
		//重量
		iPrinter.drawText(248+42+4,1432-32+4-4-8-4,"重量："+"1"+"kg",2,0, 0,false,false);
		//寄件联
		iPrinter.drawText(568-32+3+8,1096+104+16,32,96,"寄件联",2,0,0,false,false);
      
		iPrinter.print(0,1);
        
	}
	
	public static void label2(PrintPP_CPCL iPrinter,Bitmap rawBitmap){
		iPrinter.pageSetup(608, 600); 
		iPrinter.drawGraphic(0, 0, rawBitmap.getWidth(), rawBitmap.getHeight(), rawBitmap);
//		iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
//		iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
//		iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
//		iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
//		iPrinter.drawText(20, 40, "210-123-456", 7,0,  1, false, false);
//		iPrinter.drawText(190, 130, "1234567890", 3,0,  1, false, false);
		iPrinter.print(0,0);
	}
	  public static void label3(PrintPP_CPCL iPrinter){
		   	iPrinter.pageSetup(608, 200);
			iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
			iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
			iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
			iPrinter.drawText(0, 0, "", 7,0,  0, false, false);
			iPrinter.drawText(70, 40, "B-123-456-789", 5,0,  1, false, false);
			if("123456789012".length()==18){
				iPrinter.drawText(140, 130,"123456789012345678", 3,0,  1, false, false);
			}else{
				iPrinter.drawText(190, 130,"123456789012", 3,0,  1, false, false);
			}
			
			iPrinter.print(0,1);
	  }
}
