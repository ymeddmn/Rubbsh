package com.qr.demo.vo;

/**
 * 运单
 * Created by zhangshaofang on 2017/10/31.
 */

public class Waybill {
     //运单号
    private String number;
    //收货人
    private String receiver;
    //收货人电话
    private String receiverPhone;
    //收货地址
    private String receiverAddress;
    //类型
    private String receiverType;
    //发货人
    private String sender;
    //发货人电话
    private String senderPhone;
    //发货地址
    private String senderAdress;
    //日期
    private String date;

    //货物品名
    private String goodsName;
    //数量
    private String quantity;
    //运费
    private String freight;
    //声明保价
    private String declareValueInsured;
    //代收货款
    private String collectionPayment;
    //送货费
    private String deliveryFee;
    //保价费
    private String insuredValue;
    //付款方式
    private String paymentMethod;
    //费用合计
    private String totalCost;

    //二维码
    private String qrCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderAdress() {
        return senderAdress;
    }

    public void setSenderAdress(String senderAdress) {
        this.senderAdress = senderAdress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getDeclareValueInsured() {
        return declareValueInsured;
    }

    public void setDeclareValueInsured(String declareValueInsured) {
        this.declareValueInsured = declareValueInsured;
    }

    public String getCollectionPayment() {
        return collectionPayment;
    }

    public void setCollectionPayment(String collectionPayment) {
        this.collectionPayment = collectionPayment;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getInsuredValue() {
        return insuredValue;
    }

    public void setInsuredValue(String insuredValue) {
        this.insuredValue = insuredValue;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
