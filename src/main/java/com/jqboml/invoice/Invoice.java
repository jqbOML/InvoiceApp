package com.jqboml.invoice;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    int id;
    List<Element> elements = new ArrayList<>();
    Client client = new Client();
    float net_sum = 0.0f;
    float gross_sum = 0.0f;

    public Invoice(int id){
        this.id = id;
    }

    void create_element(String name, float net_price, int quantity, int tax_value){
        Element new_element = new Element(name, net_price, quantity, tax_value);
        elements.add(new_element);
        upgrade_netsum();
        upgrade_grosssum();
    }

    private void upgrade_netsum() {
        net_sum=0.0f;
        for (Element cur_elem : elements) {
            net_sum+=cur_elem.net_price*cur_elem.quantity;
        }
    }

    private void upgrade_grosssum(){
        gross_sum=0.0f;
        for (Element cur_elem : elements) {
            gross_sum+=cur_elem.gross_price*cur_elem.quantity;
        }
    }

    String printInvoice(){
        int el_quantity = 1;
        StringBuilder invoice = new StringBuilder();
        for (Element cur_elem : elements) {
            invoice.append("#").append(el_quantity).append(" ").append(cur_elem.name).append(" x").append(cur_elem.quantity).append("   |||  Net Price: ").append(cur_elem.net_price).append("   |||  Net Sum: ").append(Math.round((cur_elem.net_price) * (cur_elem.quantity) * 100) / 100.0).append("  |||  VAT: ").append(cur_elem.tax_value).append("%   |||  Gross Sum: ").append(Math.round((cur_elem.gross_price) * (cur_elem.quantity) * 100) / 100.0).append("\n");
            el_quantity++;
        }

        return invoice.toString();
    }
}
