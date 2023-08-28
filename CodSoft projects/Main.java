import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount{
    private double balance;
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            return true;
        }
        else{
            return false;
        }
    }
}

class ATM{
    private BankAccount bankAccount;
    public ATM(BankAccount account){
        bankAccount = account;
    }
    public boolean deposit(double amount){
        if(amount>0){
            bankAccount.deposit(amount);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean withdraw(double amount){
        if(amount>0 && bankAccount.withdraw(amount)){
            return true;
        }
        else{
            return false;
        }
    }

    public double checkBalance(){
        return bankAccount.getBalance();
    }
}

class ATMGUI extends JFrame{
    private ATM atm;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMGUI(ATM atm){
        this.atm = atm;

        setTitle("ATM");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountField = new JTextField(10);
        outputArea = new JTextArea(10,30);
        outputArea.setEditable(false);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double amount = Double.parseDouble(amountField.getText());
                boolean success = atm.withdraw(amount);
                if(success){
                    updateOutput("Successful\n");
                }
                else{
                    updateOutput("Insufficient balance or invalid amount\n");
                }
            }
        });

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double amount = Double.parseDouble(amountField.getText());
                boolean success = atm.deposit(amount);
                if(success){
                    updateOutput("Successful\n");
                }
                else{
                    updateOutput("Deposit Failed\n");
                }
            }
        });

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double balance = atm.checkBalance();
                updateOutput("Current Balance is: "+ balance + "ruppes\n");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
}

private void updateOutput(String message){
    outputArea.append(message);
    amountField.setText("");
}
}
public class Main{
    public static void main(String[] args){
        BankAccount bankAccount = new BankAccount(1500.0);
        ATM atm = new ATM(bankAccount);

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                ATMGUI gui = new ATMGUI(atm);
                gui.setVisible(true);
            }
        });
    }
}