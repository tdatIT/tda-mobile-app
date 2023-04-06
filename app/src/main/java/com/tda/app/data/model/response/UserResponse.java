package com.tda.app.data.model.response;


import java.sql.Timestamp;
public class UserResponse {
    public long userId;
    public String firstname;
    public String lastname;
    public String email;
    public String phone;
    public String avatar;
    public boolean status;
    public Timestamp createDate;
    public Timestamp updateDate;
}
