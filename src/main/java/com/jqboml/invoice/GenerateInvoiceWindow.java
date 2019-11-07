package com.jqboml.invoice;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateInvoiceWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel headerLabel;
    private JLabel generateIDLabel;
    private JPanel generatePanel;
    private JLabel clientnameLabel;
    private JTextField clientnameTextField;
    private JLabel clientnipLabel;
    private JTextField clientnipTextField;
    private JLabel elementsLabel;
    private JTextPane elementsTextPane;
    private JButton addElementButton;
    private JButton generateInvoiceButton;
    private JButton confirmButton;

    GenerateInvoiceWindow(final InvoiceDatabase database) {
        add(mainPanel);
        setVisible(true);
        setClientData(database);
        setLocation(450, 150);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateIDLabel.setText("Generate Invoice, ID: " + database.invoices.get(database.invoices.size() - 1).id);
        printInvoiceElements(database);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.invoices.get(database.invoices.size() - 1).client.name = clientnameTextField.getText();
                database.invoices.get(database.invoices.size() - 1).client.nip = clientnipTextField.getText();

                if (clientnipTextField.getText().length() == 10) {
                    clientnipLabel.setText("Client NIP: ");
                    clientnipLabel.setForeground(Color.BLACK);
                    setClientData(database);
                } else {
                    clientnipLabel.setText("Invalid NIP (10 digits required): ");
                    clientnipLabel.setForeground(Color.RED);
                }

            }
        });

        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateInvoiceWindow.super.dispose();
                AddElementWindow addwindow = new AddElementWindow(database);
            }
        });

        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (database.invoices.get(database.invoices.size() - 1).elements.size() > 0) {
                    elementsTextPane.setForeground(Color.BLACK);
                    GenerateInvoiceWindow.super.dispose();
                    MainWindow window = new MainWindow(database);
                } else {
                    elementsTextPane.setText("The invoice does not contain any elements!");
                    elementsTextPane.setForeground(Color.RED);
                }

            }
        });


    }

    private void printInvoiceElements(InvoiceDatabase database) {
        elementsTextPane.setText("");
        elementsTextPane.setForeground(Color.BLACK);
        StyledDocument elementsdoc = elementsTextPane.getStyledDocument();
        if (database.invoices.get(database.invoices.size() - 1).elements.size() > 0) {
            try {
                elementsdoc.insertString(elementsdoc.getLength(), database.invoices.get(database.invoices.size() - 1).printInvoice(), null);
            } catch (BadLocationException exc) {
                exc.printStackTrace();

            }
            try {
                elementsdoc.insertString(elementsdoc.getLength(), "-------------------------\n" + "NET SUM: " + Math.round(database.invoices.get(database.invoices.size() - 1).net_sum * 100) / 100.0 + "\n", null);
                elementsdoc.insertString(elementsdoc.getLength(), "GROSS SUM: " + Math.round(database.invoices.get(database.invoices.size() - 1).gross_sum * 100) / 100.0 + "\n", null);
            } catch (BadLocationException exc) {
                exc.printStackTrace();
            }
        }
    }

    private void setClientData(InvoiceDatabase database) {
        if ((database.invoices.get(database.invoices.size() - 1).client.name.equals("Client Name") && database.invoices.get(database.invoices.size() - 1).client.nip.equals("0000000000"))) {
            elementsLabel.setVisible(false);
            elementsTextPane.setVisible(false);
            addElementButton.setVisible(false);
            generateInvoiceButton.setVisible(false);
            confirmButton.setVisible(true);
        } else {
            clientnameTextField.setEditable(false);
            clientnameTextField.setText(database.invoices.get(database.invoices.size() - 1).client.name);
            clientnipTextField.setEditable(false);
            clientnipTextField.setText(database.invoices.get(database.invoices.size() - 1).client.nip);
            confirmButton.setVisible(false);
            elementsLabel.setVisible(true);
            elementsTextPane.setVisible(true);
            addElementButton.setVisible(true);
            generateInvoiceButton.setVisible(true);
        }
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
        mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(10, 15, 5, 15), -1, -1));
        headerLabel = new JLabel();
        Font headerLabelFont = this.$$$getFont$$$("Britannic Bold", -1, 24, headerLabel.getFont());
        if (headerLabelFont != null) headerLabel.setFont(headerLabelFont);
        headerLabel.setText("Invoice Generator");
        mainPanel.add(headerLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generatePanel = new JPanel();
        generatePanel.setLayout(new GridLayoutManager(5, 1, new Insets(5, 30, 5, 30), -1, -1));
        mainPanel.add(generatePanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(5, 0, 5, 0), -1, -1));
        generatePanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        clientnameTextField = new JTextField();
        panel1.add(clientnameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clientnipTextField = new JTextField();
        panel1.add(clientnipTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clientnipLabel = new JLabel();
        clientnipLabel.setText("Client NIP:");
        panel1.add(clientnipLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clientnameLabel = new JLabel();
        clientnameLabel.setText("Client name:");
        panel1.add(clientnameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 10, 0), -1, -1));
        generatePanel.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        elementsLabel = new JLabel();
        elementsLabel.setText("Elements");
        panel2.add(elementsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        elementsTextPane = new JTextPane();
        elementsTextPane.setEditable(false);
        panel2.add(elementsTextPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        addElementButton = new JButton();
        addElementButton.setText("Add Element");
        generatePanel.add(addElementButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateInvoiceButton = new JButton();
        generateInvoiceButton.setText("Generate Invoice");
        generatePanel.add(generateInvoiceButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        generatePanel.add(confirmButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateIDLabel = new JLabel();
        Font generateIDLabelFont = this.$$$getFont$$$("Cambria", -1, -1, generateIDLabel.getFont());
        if (generateIDLabelFont != null) generateIDLabel.setFont(generateIDLabelFont);
        generateIDLabel.setText("Generate Invoice, ID: 1");
        mainPanel.add(generateIDLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
