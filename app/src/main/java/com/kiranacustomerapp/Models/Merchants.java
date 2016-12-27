package com.kiranacustomerapp.Models;

import java.io.Serializable;

/**
 * Created by Siddhi on 11/26/2016.
 */
public class Merchants implements Serializable{

    private String merchant_name,kirana_name,merchant_address,mobile_no,phone,email_id;
    int fav;
    private Long merchant_id;
    private double longitude,latitude;

    public Merchants(){}

    public Merchants(Long merchant_id,String kirana_name,String merchant_address,int fav)
    {
        this.merchant_id = merchant_id;
        this.kirana_name = kirana_name;
        this.merchant_address = merchant_address;
        this.fav = fav;

    }

    public Merchants(Long merchant_id,String kirana_name,String merchant_name,String phone,String email_id,String merchant_address,int fav)
    {
        this.merchant_id = merchant_id;
        this.kirana_name = kirana_name;
        this.merchant_address = merchant_address;
        this.fav = fav;
        this.merchant_name = merchant_name;
        this.email_id = email_id;
        this.phone = phone;

    }
    public Merchants(Long merchant_id,String kirana_name,String merchant_name,String phone,String email_id,String merchant_address,double latitude,double longitude)
    {
        this.merchant_id = merchant_id;
        this.kirana_name = kirana_name;
        this.merchant_address = merchant_address;
        this.fav = fav;
        this.merchant_name = merchant_name;
        this.email_id = email_id;
        this.phone = phone;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFav() {
        return fav;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getKirana_name() {
        return kirana_name;
    }

    public void setMerchant_id(Long merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_address() {
        return merchant_address;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public Long getMerchant_id() {
        return merchant_id;
    }

    public void setKirana_name(String kirana_name) {
        this.kirana_name = kirana_name;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_address(String merchant_address) {
        this.merchant_address = merchant_address;
    }
}
