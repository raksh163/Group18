import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> currencyFrom;
    private JComboBox<String> currencyTo;
    private JButton convertButton;
    private JLabel resultLabel;

    private static final double USD_TO_EUR = 0.89;
    private static final double EUR_TO_USD = 1.12;
    private static final double USD_TO_INR = 83.63;
    private static final double INR_TO_USD = 1 / USD_TO_INR;
    private static final double AED_TO_USD = 0.27; 
    private static final double USD_TO_AED = 1 / AED_TO_USD;
    private static final double EUR_TO_INR = 93.49;
    private static final double INR_TO_EUR = 1 / EUR_TO_INR;
    private static final double AED_TO_EUR = 0.24;
    private static final double EUR_TO_AED = 1 / AED_TO_EUR;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());


        amountField = new JTextField(10);
        add(new JLabel("Amount:"));
        add(amountField);


        String[] currencies = {"USD", "EUR", "INR", "AED"};
        currencyFrom = new JComboBox<>(currencies);
        currencyTo = new JComboBox<>(currencies);
        add(new JLabel("From:"));
        add(currencyFrom);
        add(new JLabel("To:"));
        add(currencyTo);


        convertButton = new JButton("Convert");
        add(convertButton);


        resultLabel = new JLabel("Result:");
        add(resultLabel);


        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) currencyFrom.getSelectedItem();
            String toCurrency = (String) currencyTo.getSelectedItem();
            double result = 0;

            if (fromCurrency.equals("USD")) {
                if (toCurrency.equals("EUR")) {
                    result = amount * USD_TO_EUR;
                } else if (toCurrency.equals("INR")) {
                    result = amount * USD_TO_INR;
                } else if (toCurrency.equals("AED")) {
                    result = amount * USD_TO_AED;
                }
            } else if (fromCurrency.equals("EUR")) {
                if (toCurrency.equals("USD")) {
                    result = amount * EUR_TO_USD;
                } else if (toCurrency.equals("INR")) {
                    result = amount * EUR_TO_INR;
                } else if (toCurrency.equals("AED")) {
                    result = amount * EUR_TO_AED;
                }
            } else if (fromCurrency.equals("INR")) {
                if (toCurrency.equals("USD")) {
                    result = amount * INR_TO_USD;
                } else if (toCurrency.equals("EUR")) {
                    result = amount * INR_TO_EUR;
                } else if (toCurrency.equals("AED")) {
                    result = amount * INR_TO_USD * USD_TO_AED;
                }
            } else if (fromCurrency.equals("AED")) {
                if (toCurrency.equals("USD")) {
                    result = amount * AED_TO_USD;
                } else if (toCurrency.equals("EUR")) {
                    result = amount * AED_TO_USD * USD_TO_EUR;
                } else if (toCurrency.equals("INR")) {
                    result = amount * AED_TO_USD * USD_TO_INR;
                }
            } else {
                result = amount;
            }

            resultLabel.setText("Result: " + String.format("%.2f", result) + " " + toCurrency);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
