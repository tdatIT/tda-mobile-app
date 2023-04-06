package com.tda.app.data.model;

import java.sql.Date;



public class Voucher {
    public String voucherCode;
    public int type;
    public double discount;
    public Date startDate;
    public Date endDate;
    public int quantityAvailable;
    public boolean isDeleted;
}

