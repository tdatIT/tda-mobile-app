package com.tda.app.data.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ProductResponse {
    public long productId;
    public String productCode;
    public String productName;
    public String description;
    public int quantity;
    public int status;
    public double price;
    public double promotionPrice;
    public Timestamp createDate;
    public Timestamp updateDate;
    public boolean isDeleted;
    public long categoryId;
    public List<ProductType> productType;
    public List<String> images_file;


}
