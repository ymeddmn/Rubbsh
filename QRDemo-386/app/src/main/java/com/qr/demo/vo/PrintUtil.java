package com.qr.demo.vo;

/**
 * 蓝牙打印工具
 * Created by zhangshaofang on 2017/10/31.
 */

public class PrintUtil {
    //格式化手机号
    public static String formatMobile(String phone) {
        if (phone.length() == 11) {
            StringBuffer sb = new StringBuffer(phone);
            sb.insert(3, "-").insert(8, "-");
            phone = sb.toString();
        }
        return phone;
    }

    public static Ticket getTicketSimple() {
        Ticket ticket = new Ticket();
        ticket.setSpecialLine1("北京→广州、深圳、佛山");
        ticket.setSpecialLine2("北京→沈阳、哈尔滨");
        ticket.setSpecialLine3("北京→郑州、西安、兰州");
        ticket.setSpecialLine4("北京→成都、重庆");
        ticket.setSpecialLinePhone("北京(010-25258585)→广州(010-25258585)");
        ticket.setBarCode("123456789012");
        ticket.setNumber("1001200501");
        ticket.setDate("2017-09-09 15:30");
        ticket.setSender("马勇");
        ticket.setSenderPhone("13688889999");
        ticket.setSenderAdress("北京市海淀区东北旺路23号");
        ticket.setReceiver("刘益岑");
        ticket.setReceiverPhone("13566669999");
        ticket.setReceiverAddress("广州天河区长兴路5号产业园");
        ticket.setReceiverType("送货");
        ticket.setTransportMethod("汽运");
        ticket.setGoodsName("饮料");
        ticket.setQuantity("0.60吨\\1.00方");
        ticket.setCount("20件");
        ticket.setPack("纸箱");
        ticket.setReceipt("不需要");
        ticket.setFreight("￥55");
        ticket.setDeliveryFee("￥115");
        ticket.setOtherFee("￥0");
        ticket.setCollectionPayment("￥3000");
        ticket.setDeclareValueInsured("￥2000");
        ticket.setInsuredValue("￥15");
        ticket.setTotalCost("现付：￥10 到付：￥3170");
        ticket.setQrCode("123456789");
        return ticket;
    }

    public static Waybill getWaybillSimple() {
        Waybill waybill = new Waybill();
        waybill.setNumber("1001200501");
        waybill.setSender("马勇");
        waybill.setSenderPhone("13688889999");
        waybill.setSenderAdress("北京市海淀区东北旺路23号");
        waybill.setReceiver("刘益岑");
        waybill.setReceiverPhone("13566669999");
        waybill.setReceiverAddress("广州天河区长兴路5号产业园");
        waybill.setReceiverType("送货");
        waybill.setDate("2017-09-09 15:30");
        waybill.setGoodsName("饮料");
        waybill.setQuantity("55箱\\200kg\\1.5方");
        waybill.setFreight("￥55");
        waybill.setDeclareValueInsured("￥2000");
        waybill.setCollectionPayment("￥3000");
        waybill.setDeliveryFee("￥115");
        waybill.setInsuredValue("￥15");
        waybill.setPaymentMethod("到付");
        waybill.setTotalCost("￥3170");
        return waybill;
    }
}
