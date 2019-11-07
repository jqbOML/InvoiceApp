package com.jqboml.invoice;

import org.junit.Assert;
import org.junit.Test;

public class SumTest {
    @Test
    public void canCalculateNetSum(){
        Invoice invoice = new Invoice(0);
        invoice.create_element("test_element1", 0.40f, 20,23);
        invoice.create_element("test_element2", 12.49f, 2, 8);
        Assert.assertEquals("Should calculate net sum equals 32.98", 32.98, Math.round(invoice.net_sum * 100)/100.0, 0.0f);
    }

    @Test
    public void canCalculateGrossSum(){
        Invoice invoice = new Invoice(0);
        invoice.create_element("test_element1", 0.40f, 20,23);
        invoice.create_element("test_element2", 12.49f, 2, 8);
        Assert.assertEquals("Should calculate gross sum equals 29.14", 29.14, Math.round(invoice.gross_sum * 100)/100.0, 0.0f);
    }
}
