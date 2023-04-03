package com.driver;

public class Order {

    public String id;
    public int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int hh = Integer.parseInt(deliveryTime.substring(0,2));
        int mm = Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime=hh*60+mm;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
