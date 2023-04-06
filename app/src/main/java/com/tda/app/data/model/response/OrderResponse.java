package com.tda.app.data.model.response;

import com.tda.app.data.model.Voucher;

import java.sql.Timestamp;
import java.util.List;


public class OrderResponse {
    public long orderId;
    public long userId;
    public double shippingCosts;
    public double discount;
    public double total;
    public int orderStatus;
    public int paymentMethod;
    public Timestamp createDate;
    public String address_detail;
    public long address_id;
    public Voucher voucher;
    public List<OrderItemResp> order_items;

}
