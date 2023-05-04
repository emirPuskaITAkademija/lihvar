package ba.lihvarenje.gui;

import ba.lihvarenje.bank.BankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BankAccountPanel extends JPanel {
    private final JLabel fromAccountLabel = new JLabel("From");
    private final JComboBox<BankAccount> fromAccountComboBox = new JComboBox<>();
    private final JLabel toAccountLabel = new JLabel("To");
    private final JComboBox<BankAccount> toAccountComboBox = new JComboBox<>();
    private final JLabel amountLabel = new JLabel("Amount");
    private final JTextField amountTextField = new JTextField(10);
    private final JButton transferButton = new JButton("Transfer Money");

    public BankAccountPanel(){
        setLayout(new GridLayout(4, 1));
        JPanel fromAccountPanel = new JPanel();
        JPanel toAccountPanel = new JPanel();
        JPanel amountPanel = new JPanel();
        JPanel transferPanel = new JPanel();
        add(fromAccountPanel);
        add(toAccountPanel);
        add(amountPanel);
        add(transferPanel);

        fromAccountPanel.add(fromAccountLabel);
        fromAccountPanel.add(fromAccountComboBox);
        toAccountPanel.add(toAccountLabel);
        toAccountPanel.add(toAccountComboBox);
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        transferButton.addActionListener(this::transferMoney);
        transferPanel.add(transferButton);

        List<BankAccount> bankAccounts = BankAccount.findAll();
        for (BankAccount bankAccount : bankAccounts) {
            fromAccountComboBox.addItem(bankAccount);
            toAccountComboBox.addItem(bankAccount);
        }
    }


    private void transferMoney(ActionEvent actionEvent){
        BankAccount fromBankAccount = (BankAccount) fromAccountComboBox.getSelectedItem();
        BankAccount toBankAccount = (BankAccount) toAccountComboBox.getSelectedItem();
        Double transferAmount = Double.parseDouble(amountTextField.getText());
        BankAccount.transferMoney(fromBankAccount, toBankAccount, transferAmount);
        refresh();
        JOptionPane.showMessageDialog(null, "Uspješan transfer novca");
    }

    private void refresh(){
        List<BankAccount> bankAccounts = BankAccount.findAll();
        for (BankAccount bankAccount : bankAccounts) {
            fromAccountComboBox.addItem(bankAccount);
            toAccountComboBox.addItem(bankAccount);
        }
        amountTextField.setText("");
    }

}
