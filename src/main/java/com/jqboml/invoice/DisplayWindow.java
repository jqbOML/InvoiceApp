package com.jqboml.invoice;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel headerLabel;
    private JLabel displayLabel;
    private JPanel displayPanel;
    private JTextPane invoicesTextPane;
    private JLabel typenumberLabel;
    private JTextField invoicenumberTextField;
    private JButton displayButton;
    private JButton generateButton;
    private JButton deleteButton;
    private JButton closeButton;

    DisplayWindow(final InvoiceDatabase database) {
        add(mainPanel);
        setVisible(true);
        closeButton.setVisible(false);
        setLocation(450, 150);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printAvailableInvoices(database);

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int invoice_id = Integer.parseInt(invoicenumberTextField.getText()) - 1;
                if (invoice_id >= 0 && invoice_id <= database.invoices.size() - 1) {
                    typenumberLabel.setText("Type number of invoice to display: ");
                    typenumberLabel.setForeground(Color.black);
                    printInvoiceTextPane(database, invoice_id);
                } else {
                    typenumberLabel.setText("Type a number between 0 - " + database.invoices.size() + "! ");
                    typenumberLabel.setForeground(Color.red);
                }
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayWindow.super.dispose();
                database.create_invoice();
                GenerateInvoiceWindow generatewindow = new GenerateInvoiceWindow(database);
            }
        });


    }

    private void printAvailableInvoices(final InvoiceDatabase database) {
        displayButton.setVisible(true);
        generateButton.setVisible(true);
        closeButton.setVisible(false);
        invoicesTextPane.setText("");
        StyledDocument elementsdoc = invoicesTextPane.getStyledDocument();
        if (database.invoices.size() > 0) {
            try {
                elementsdoc.insertString(elementsdoc.getLength(), database.printInvoicesList(), null);
            } catch (BadLocationException exc) {
                exc.printStackTrace();
            }


        } else {
            try {
                elementsdoc.insertString(elementsdoc.getLength(), "No available invoices!", null);
            } catch (BadLocationException exc) {
                exc.printStackTrace();
            }

            generateButton.setText("Generate Invoice");
            displayButton.setVisible(false);
            typenumberLabel.setVisible(false);
            invoicenumberTextField.setVisible(false);

        }
    }

    private void printInvoiceTextPane(final InvoiceDatabase database, int invoice_id) {
        displayButton.setVisible(false);
        generateButton.setVisible(false);
        closeButton.setVisible(true);

        invoicesTextPane.setText("------------------------------------------------ Invoice #" + (invoice_id + 1) + " for: " + database.invoices.get(invoice_id).client.name + " (NIP: " + database.invoices.get(invoice_id).client.nip + ")" + "\n\n");
        StyledDocument elementsdoc = invoicesTextPane.getStyledDocument();

        try {
            elementsdoc.insertString(elementsdoc.getLength(), "Products: \n", null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }


        try {
            elementsdoc.insertString(elementsdoc.getLength(), database.invoices.get(invoice_id).printInvoice(), null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }

        try {
            elementsdoc.insertString(elementsdoc.getLength(), "-------------------------\n" + "NET SUM: " + Math.round(database.invoices.get(invoice_id).net_sum * 100) / 100.0 + "\n", null);
            elementsdoc.insertString(elementsdoc.getLength(), "GROSS SUM: " + Math.round(database.invoices.get(invoice_id).gross_sum * 100) / 100.0 + "\n\n\n", null);
            elementsdoc.insertString(elementsdoc.getLength(), "------------------------------------------------ Generated by " + database.user.name + " (NIP: " + database.user.nip + ")\n", null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printAvailableInvoices(database);
            }
        });
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 4, new Insets(10, 15, 5, 15), -1, -1));
        headerLabel = new JLabel();
        Font headerLabelFont = this.$$$getFont$$$("Britannic Bold", -1, 24, headerLabel.getFont());
        if (headerLabelFont != null) headerLabel.setFont(headerLabelFont);
        headerLabel.setText("Invoice Generator");
        mainPanel.add(headerLabel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(displayPanel, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        invoicesTextPane = new JTextPane();
        invoicesTextPane.setEditable(false);
        displayPanel.add(invoicesTextPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        displayLabel = new JLabel();
        Font displayLabelFont = this.$$$getFont$$$("Cambria", -1, -1, displayLabel.getFont());
        if (displayLabelFont != null) displayLabel.setFont(displayLabelFont);
        displayLabel.setText("Display Invoices");
        mainPanel.add(displayLabel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typenumberLabel = new JLabel();
        typenumberLabel.setText("Type number of invoice to display:");
        mainPanel.add(typenumberLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        invoicenumberTextField = new JTextField();
        mainPanel.add(invoicenumberTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        displayButton = new JButton();
        displayButton.setText("Display");
        mainPanel.add(displayButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateButton = new JButton();
        generateButton.setText("Generate");
        mainPanel.add(generateButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Close");
        mainPanel.add(closeButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
