import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawSearchAccount extends JFrame{
    private JPanel rootPanel;
    private JLabel withdrawLabel;
    private JLabel accountNumberLabel;
    private JTextField accountNumberTextField;
    private JButton enterButton;
    private JButton backButton;

    public WithdrawSearchAccount() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //add action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });

        //add action listener for enter button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchWithdrawWindow();
            }
        });
    }
    public void launchWithdrawWindow(){
        int accountNumber = Integer.parseInt(accountNumberTextField.getText());
        MySQLCon mySQLCon = new MySQLCon();
        Account account = mySQLCon.checkBalance(accountNumber);
        mySQLCon.CloseConnection();

        if (account == null) {
            JOptionPane.showMessageDialog(null,"Account Not Found");
        } else {
            new Withdraw(account);
            dispose();
        }

    }

    //function to go back to the main menu
    public void goBackToMainMenu(){
        new MainMenu();
        dispose();
    }
}
