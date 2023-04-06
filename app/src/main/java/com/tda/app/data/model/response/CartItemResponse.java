package com.tda.app.data.model.response;

import java.util.List;


public class CartItemResponse {
    public long itemId;
    public int quantity;
    public double amount;
    //product
    public String productCode;
    public String productName;
    public int status;
    public double price;
    public double promotionPrice;
    public long categoryId;
    public List<String> images_file;




}
