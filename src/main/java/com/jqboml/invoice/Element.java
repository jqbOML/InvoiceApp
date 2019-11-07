package com.jqboml.invoice;

public class Element {
    String name = "New Element";
    float net_price = 0.0f;
    float gross_price = 0.0f;
    int quantity = 1;
    int tax_value = 0;

    Element(String name, float net_price, int quantity, int tax_value){
        this.name = name;
        this.net_price = net_price;
        gross_price = net_price*(float)(100-tax_value)/100;
        this.quantity = quantity;
        this.tax_value = tax_value;
    }
}
