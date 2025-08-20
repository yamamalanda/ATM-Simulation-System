import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATMGUI extends JFrame implements ActionListener {
    private ATMOperations atmOps = new ATMOperations();
    private User currentUser;

    private JTextField tfAccount;
    private JPasswordField pfPin;
    private JLabel lblMessage;
    private JPanel panelMain, panelMenu;

    public ATMGUI() {
        setTitle("ATM Simulation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelMain = new JPanel(new GridLayout(4, 2));
        tfAccount = new JTextField();
        pfPin = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        lblMessage = new JLabel("");

        panelMain.add(new JLabel("Account No:"));
        panelMain.add(tfAccount);
        panelMain.add(new JLabel("PIN:"));
        panelMain.add(pfPin);
        panelMain.add(btnLogin);
        panelMain.add(lblMessage);

        btnLogin.addActionListener(this);
        add(panelMain);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String acc = tfAccount.getText();
        String pin = new String(pfPin.getPassword());
        currentUser = atmOps.login(acc, pin);

        if (currentUser != null) {
            showMenu();
        } else {
            lblMessage.setText("Invalid login!");
        }
    }

    private void showMenu() {
        panelMenu = new JPanel(new GridLayout(4, 1));
        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnBalance = new JButton("Check Balance");
        JButton btnExit = new JButton("Exit");

        btnDeposit.addActionListener(e -> deposit());
        btnWithdraw.addActionListener(e -> withdraw());
        btnBalance.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Balance: " + currentUser.getBalance()));
        btnExit.addActionListener(e -> System.exit(0));

        panelMenu.add(btnDeposit);
        panelMenu.add(btnWithdraw);
        panelMenu.add(btnBalance);
        panelMenu.add(btnExit);

        setContentPane(panelMenu);
        validate();
    }

    private void deposit() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (amtStr != null) {
            double amt = Double.parseDouble(amtStr);
            currentUser.deposit(amt);
            JOptionPane.showMessageDialog(this, "Deposited: " + amt);
        }
    }

    private void withdraw() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (amtStr != null) {
            double amt = Double.parseDouble(amtStr);
            if (currentUser.withdraw(amt)) {
                JOptionPane.showMessageDialog(this, "Withdrawn: " + amt);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient Balance!");
            }
        }
    }
}
