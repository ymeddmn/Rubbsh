package com.qr.demo;

import android.graphics.Bitmap;
import android.util.Log;

import com.qr.demo.vo.PrintUtil;
import com.qr.demo.vo.Ticket;
import com.qr.demo.vo.Waybill;
import com.qr.print.PrintPP_CPCL;

/**
 * Created by zhangshaofang on 2017/10/30.
 */

public class BeescmPrint {
    protected static final int page_width = 560;//模板宽度
    protected static final int PAGE_END = 30;//尾部预留
    protected static final int PENDING_LEFT_RIGHT = 10;//左右边距
    protected static final int DIVIDER = 5;//间距

    /**
     * 蜂网快运托运凭证
     *
     * @param iPrinter
     * @param bmp      logo
     * @param location 地址图标
     */
    public static void transportTicket(PrintPP_CPCL iPrinter, Ticket t, Bitmap bmp, Bitmap location) {
        int logo_wh = bmp.getWidth();
        int page_height = 1080 + DIVIDER * 13 + PAGE_END;
        int left = 30;

        iPrinter.pageSetup(page_width, page_height);
        PrintProxy p = new PrintProxy(iPrinter);
        int h1 = p.drawGraphic2(left, 10, logo_wh, logo_wh, bmp);
        p.drawText(left + logo_wh + 10, 30, "蜂网快运托运凭证", 4, 0, 0, false, false);

        int h2 = p.drawText(PENDING_LEFT_RIGHT, h1 + 10, t.getSpecialLine1(), 2, 0, 0, false, false);
        iPrinter.drawText(page_width / 2 + PENDING_LEFT_RIGHT, h1 + 10, t.getSpecialLine2(), 2, 0, 0, false, false);
        int h3 = p.drawText(PENDING_LEFT_RIGHT, h2, t.getSpecialLine3(), 2, 0, 0, false, false);
        iPrinter.drawText(page_width / 2 + PENDING_LEFT_RIGHT, h2, t.getSpecialLine4(), 2, 0, 0, false, false);

        int h4 = p.drawText((page_width - getLength(t.getSpecialLinePhone(),24)) / 2, h3, t.getSpecialLinePhone(), 2, 0, 0, false, false);

        int h5 = p.drawLine(2, 0, h4, page_width, h4, false);//第一联横线1

        int h6 = p.drawBarCode((page_width - t.getBarCode().length() * 24) / 2, h5 + DIVIDER*2, t.getBarCode(), 1, 0, 3, 80);

        int h7 = p.drawText(PENDING_LEFT_RIGHT, h6 + DIVIDER*2, "运单号："+t.getNumber(), 2, 0, 0, false, false);

        p.drawText(page_width - (8 * 24 + PENDING_LEFT_RIGHT), h6+ DIVIDER*2, t.getDate(), 2, 0, 0, false, false);

        int h8 = p.drawLine(2, 0, h7, page_width, h7, false);//第二联横线1

        int h9 = p.drawText(PENDING_LEFT_RIGHT, h8 + DIVIDER, "发货人："+t.getSender(), 2, 0, 0, false, false);
        p.drawText(page_width - (7 * 24 + PENDING_LEFT_RIGHT), h8 + DIVIDER, PrintUtil.formatMobile(t.getSenderPhone()), 2, 0, 0, false, false);
        int lw = location.getWidth();
        int lh = location.getHeight();
        int h10 = p.drawGraphic(PENDING_LEFT_RIGHT, h9, lw, lh, location);
        p.drawText(PENDING_LEFT_RIGHT + 45 + 10, h9 + (lh - 24) / 2, t.getSenderAdress(), 2, 0, 0, false, false);
        int h11 = p.drawLine(2, 0, h10 + DIVIDER, page_width, h10 + DIVIDER, false);//第三联定位下横线1

        int h12 = p.drawText(PENDING_LEFT_RIGHT, h11 + DIVIDER, "收货人："+t.getReceiver(), 2, 0, 0, false, false);
        p.drawText(page_width - (7 * 24 + PENDING_LEFT_RIGHT), h11 + DIVIDER, PrintUtil.formatMobile(t.getReceiverPhone()), 2, 0, 0, false, false);
        int h13 = p.drawGraphic(PENDING_LEFT_RIGHT, h12, lw, lh, location);
        p.drawText(PENDING_LEFT_RIGHT + 45 + 10, h12 + (lh - 24) / 2, t.getReceiverAddress(), 2, 0, 0, false, false);
        int h14 = p.drawLine(2, 0, h13 + DIVIDER, page_width, h13 + DIVIDER, false);//第四联定位下横线

        p.drawText(0, h14 + DIVIDER, "货物详情", 2, 0, 1, false, false);
        int h15 = p.drawBox(2, page_width - (2 * 24 + PENDING_LEFT_RIGHT + 10), h14 + DIVIDER, page_width - PENDING_LEFT_RIGHT, h14 + 10 + 24 + DIVIDER);
        p.drawText(page_width - (2 * 24 + PENDING_LEFT_RIGHT + 5), h14 + 5 + DIVIDER, t.getReceiverType(), 2, 0, 0, false, false);
        int h16 = drawText(p, h15 + DIVIDER, "运输方式", t.getTransportMethod());
        int h17 = drawText(p, h16, "货物名称", t.getGoodsName());
        int h18 = drawText(p, h17, "重量\\体积", t.getQuantity());
        int h19 = drawText(p, h18, "件数", t.getCount());
        int h20 = drawText(p, h19, "包装", t.getPack());
        int h21 = drawText(p, h20, "回单", t.getReceipt());
        int h22 = p.drawLine(2, 0, h21, page_width, h21, false);//第四联横线1

        int h23 = p.drawText(0, h22 + DIVIDER, "费用详情", 2, 0, 1, false, false);
        int h24 = drawText(p, h23 + DIVIDER * 2, "运费", t.getFreight());
        int h25 = drawText(p, h24, "送货费", t.getDeliveryFee());
        int h26 = drawText(p, h25, "其他费用", t.getOtherFee());
        int h27 = drawText(p, h26, "代收货款", t.getCollectionPayment());
        int h28 = drawText(p, h27, "声明保价", t.getDeclareValueInsured());
        int h29 = drawText(p, h28, "保价费", t.getInsuredValue());
        int h30 = drawText3Font(p, h29, "合计", t.getTotalCost());
        int h31 = p.drawLine(2, 0, h30 + DIVIDER, page_width, h30 + DIVIDER, false);//横线

        int h32 = p.drawText(0, h31 + DIVIDER, "须知：", 2, 0, 0, false, false);
        int h33 = p.drawText(PENDING_LEFT_RIGHT, h32, "1、感谢您选择蜂网快运，请核对好发货信息", 2, 0, 0, false, false);
        int h34 = p.drawText(PENDING_LEFT_RIGHT, h33, "2、关注右边二维码可以及时跟踪货物", 2, 0, 0, false, false);
        int h35 = p.drawText(PENDING_LEFT_RIGHT, h34, "3、物流运送过程中只保件数和是否完整", 2, 0, 0, false, false);

        p.drawQrCode(page_width - 85, h32, t.getQrCode(), 0, 4, 20);
        iPrinter.print(0, 0);
    }

    /**
     * 打印运单
     *
     * @param iPrinter
     * @param bmp      logo
     * @param b        运单
     */
    public static void printWaybill(PrintPP_CPCL iPrinter, Waybill b, Bitmap bmp) {
        int flontSize = 8;
        int lw = bmp.getWidth();
        int lh = bmp.getHeight();
        int page_height = 328 + PAGE_END;
        iPrinter.pageSetup(page_width, page_height);
        PrintProxy p = new PrintProxy(iPrinter);
        int h1 = p.drawGraphic(PENDING_LEFT_RIGHT, 0, lw, lh, bmp);
        String title = "运单号 " + b.getNumber();
        p.drawText((page_width - getLength(title, 24)) / 2, (lh - 24) / 2, title, 2, 0, 1, false, false);
        int h2 = p.drawLine(2, 0, h1, page_width, h1 + DIVIDER, false);//横线
        String receiver = "收货人：";
        int h3 = p.drawText(PENDING_LEFT_RIGHT, h2, receiver, flontSize, 0, 1, false, false);
        p.drawText(getLength(receiver, 20), h2, b.getReceiver(), flontSize, 0, 0, false, false);
        String receiverPhone = PrintUtil.formatMobile(b.getReceiverPhone());
        p.drawText((page_width - getLength(receiverPhone, 20)) / 2, h2, receiverPhone, flontSize, 0, 0, false, false);

        int h4 = p.drawText(PENDING_LEFT_RIGHT, h3, b.getReceiverAddress() + "  " + b.getReceiverType(), flontSize, 0, 0, false, false);

        int h5 = p.drawLine(2, 0, h4, page_width, h4 + DIVIDER, false);//横线
        String sender = "发货人：";
        int h6 = p.drawText(PENDING_LEFT_RIGHT, h5, sender, flontSize, 0, 1, false, false);
        String senderPhone = PrintUtil.formatMobile(b.getSenderPhone());
        p.drawText(getLength(sender, 20), h5, b.getSender(), flontSize, 0, 0, false, false);
        p.drawText((page_width - getLength(senderPhone, 20)) / 2, h5, senderPhone, flontSize, 0, 0, false, false);
        int hd1 = p.drawText(PENDING_LEFT_RIGHT, h6, b.getSenderAdress(), flontSize, 0, 0, false, false);
        String date = b.getDate();
        p.drawText(page_width - getLength(date, 20) - PENDING_LEFT_RIGHT, h6, date, flontSize, 0, 0, false, false);

        int h7 = p.drawLine(2, 0, hd1, page_width, hd1, false);//横线

        int h8 = drawTextFont8(p, h7 + DIVIDER, "货物品名：" + b.getGoodsName(), b.getQuantity());
        int h9 = drawTextFont8(p, h8, "运费：" + b.getFreight(), "代收货款："+b.getCollectionPayment());
        String s1 = "声明保价：" + b.getDeclareValueInsured();
        p.drawText((page_width - getLength(s1, 20)) / 2, h8, s1, flontSize, 0, 0, false, false);
        int h10 = drawTextLeftCenter(p, h9 + DIVIDER, "送货费："+b.getDeliveryFee(), "保价费："+b.getInsuredValue());
        drawTextLeftCenter(p, h10 + DIVIDER, "付款方式："+b.getPaymentMethod(), "费用合计："+b.getTotalCost());

        p.drawQrCode(page_width - 85 - 25, h9 - 8, b.getQrCode(), 0, 4, 20);

        int hend = p.drawText(page_width - 20, h9 - 8, 20, 80, "扫我查货", 1, 0, 0, false, false);
        Log.d("end", "" + hend);
        iPrinter.print(0, 0);
    }

    private static int drawTextFont8(PrintProxy p, int y, String left, String right) {
        p.drawText(PENDING_LEFT_RIGHT, y, left, 8, 0, 0, false, false);
        return p.drawText(page_width - (getLength(right, 20) + PENDING_LEFT_RIGHT), y, right, 8, 0, 0, false, false);
    }

    private static int drawText(PrintProxy p, int y, String left, String right) {
        p.drawText(PENDING_LEFT_RIGHT, y, left, 2, 0, 0, false, false);
        return p.drawText(page_width - (getLength(right, 24) + PENDING_LEFT_RIGHT), y, right, 2, 0, 0, false, false);
    }

    private static int drawTextLeftCenter(PrintProxy p, int y, String left, String center) {
        p.drawText(PENDING_LEFT_RIGHT, y, left, 8, 0, 0, false, false);
        return p.drawText((page_width - getLength(center, 20)) / 2, y, center, 8, 0, 0, false, false);
    }

    private static int drawText3Font(PrintProxy p, int y, String left, String right) {
        p.drawText(PENDING_LEFT_RIGHT, y, left, 3, 0, 1, false, false);
        return p.drawText(page_width - (getLength(right, 32) + PENDING_LEFT_RIGHT), y, right, 3, 0, 0, false, false);

    }

    /**
     * 汉字宽度 等于ascII 长度乘 2
     *
     * @param str
     * @param size
     * @return
     */
    private static int getLength(String str, int size) {
        int length = 0;
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr >= 0 && chr <= 126) {
                length += size / 2;
            } else {
                length += size;
            }
        }
        return length;
    }

}
