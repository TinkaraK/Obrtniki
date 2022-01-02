package com.example.obrtniki;

public class Obrtnik {
    int id;
    String company_name;
    int post_number;
    String city;
    String phone_number;
    String tax_number;
    String service_description;
    String company_description;
    int user_id;
    int region_id;
    int trade_type_id;
    int price_range_id;
    String created_at;
    String updated_at;
    float avg_rating;

    public int getId() {
        return id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public int getPost_number() {
        return post_number;
    }

    public String getCity() {
        return city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getTax_number() {
        return tax_number;
    }

    public String getService_description() {
        return service_description;
    }

    public String getCompany_description() {
        return company_description;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public int getTrade_type_id() {
        return trade_type_id;
    }

    public int getPrice_range_id() {
        return price_range_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public float getAvg_rating() {
        return avg_rating;
    }
}
