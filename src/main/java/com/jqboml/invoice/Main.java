package com.jqboml.invoice;

public class Main {
    public static void main(String[] args){
        final InvoiceDatabase database = new InvoiceDatabase();

        /**
         * SZTUCZNE TEST DANE
         **/
        database.create_invoice();
        database.invoices.get(0).client.name = "Jan Kowalski";
        database.invoices.get(0).client.nip="1234567890";
        database.invoices.get(0).create_element("Bułki", 0.40f, 20, 23);
        database.invoices.get(0).create_element("Chusteczki paczka", 12.49f, 2, 8);

        database.create_invoice();
        database.invoices.get(1).client.name = "Adam Kowal";
        database.invoices.get(1).client.nip="2134262892";
        database.invoices.get(1).create_element("TV", 1199.99f, 1, 23);

        database.create_invoice();
        database.invoices.get(2).client.name = "Adam Kowal";
        database.invoices.get(2).client.nip="2134262892";
        database.invoices.get(2).create_element("Głośniki", 599.99f, 1, 23);
        database.invoices.get(2).create_element("Książka 'Java Podstawy'", 24.49f, 1, 5);

        database.create_invoice();
        database.invoices.get(3).client.name = "Janina Kowalska";
        database.invoices.get(3).client.nip="4144242894";
        database.invoices.get(3).create_element("Słuchawki", 149.99f, 1, 23);
        database.invoices.get(3).create_element("Książka 'PHP Zaawansowany'", 34.39f, 1, 5);
        /**
         * KONIEC
         */

        MainWindow window = new MainWindow(database);
    }
}
