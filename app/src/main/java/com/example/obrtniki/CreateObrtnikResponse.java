package com.example.obrtniki;

import java.io.Serializable;

public class CreateObrtnikResponse implements Serializable {

    private String company_name;
    private String address;
    private int post_number;
    private String city;
    private String tax_number;
    private int trade_type_id;
    private int region_id;
    private int price_range_id;
    private String service_description;
    private int user_id;
    private String company_description;
    private String phone_number;
    private int id;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPost_number() {
        return post_number;
    }

    public void setPost_number(int post_number) {
        this.post_number = post_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public int getTrade_type_id() {
        return trade_type_id;
    }

    public void setTrade_type_id(int trade_type_id) {
        this.trade_type_id = trade_type_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getPrice_range_id() {
        return price_range_id;
    }

    public void setPrice_range_id(int price_range_id) {
        this.price_range_id = price_range_id;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
