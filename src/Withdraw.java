import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw extends JFrame{
    private JPanel rootPanel;
    private JLabel accountNumberLabel;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JLabel dateAccountOpenedLabel;
    private JTextField withdrawAmountTextField;
    private JButton withdrawButton;
    private JButton backButton;

    private Account account;

    public Withdraw(Account _account){
        this.account = _account;

        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameLabel.setText(account.getName());
        balanceLabel.setText("$" + account.getBalance());
        accountNumberLabel.setText(Integer.toString(account.getAccountNumber()));
        dateAccountOpenedLabel.setText(account.getAccountOpenDate());

        //add action listener if user clicks deposit
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WithdrawFromAccount();
            }
        });

        //add action listener if user clicks back to main menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });
    }

    public void WithdrawFromAccount() {
        MySQLCon mySQLCon = new MySQLCon();
        int withdrawAmount = Integer.parseInt(withdrawAmountTextField.getText());

        if (this.account.getBalance() - withdrawAmount < 0) {
            JOptionPane.showMessageDialog(null,"Insufficient Funds");
        } else {
            Account account = mySQLCon.Withdraw(this.account.getAccountNumber(), withdrawAmount, this.account.getBalance());

            if (account == null){
                JOptionPane.showMessageDialog(null,"An Error Occured");
            } else {
                JOptionPane.showMessageDialog(null,"Successfully Withdrew");
                new Withdraw(account);
                dispose();
            }
        }
    }

    //function to go back to the main menu
    public void goBackToMainMenu() {
        new MainMenu();
        dispose();
    }
}
