package com.jqboml.invoice;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

class InvoiceDatabase {
    List<Invoice> invoices = new ArrayList<>();
    User user = new User();

    void create_invoice(){
        Invoice new_invoice = new Invoice(this.invoices.size() + 1);
        invoices.add(new_invoice);
    }

    String printInvoicesList(){
        int quantity = 1;
        StringBuilder invoicesList = new StringBuilder();
        for (Invoice cur_invoice : invoices) {
            invoicesList.append("Invoice #").append(quantity).append(" for: ").append(cur_invoice.client.name).append(" (NIP: ").append(cur_invoice.client.nip).append(")   |||  Net Sum: ").append(Math.round(cur_invoice.net_sum * 100) / 100.0).append("   |||  Gross Sum: ").append(Math.round(cur_invoice.gross_sum * 100) / 100.0).append("\n");
            quantity++;
        }

        return invoicesList.toString();
    }
}


