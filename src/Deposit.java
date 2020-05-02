import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Deposit extends JFrame{
    private JPanel rootPanel;
    private JLabel depositlabel;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JLabel dateAccountOpenedLabel;
    private JLabel accountNumberLabel;
    private JLabel depositAmountLabel;
    private JTextField depositAmountTextField;
    private JButton depositButton;
    private JButton goBackToMainButton;

    private Account account;

    public Deposit(Account _account) {
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
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DepositIntoAccount();
            }
        });

        //add action listener if user clicks back to main menu
        goBackToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });
    }

    public void DepositIntoAccount() {
        MySQLCon mySQLCon = new MySQLCon();
        int depositAmount = Integer.parseInt(depositAmountTextField.getText());
        Account account = mySQLCon.Deposit(this.account.getAccountNumber(), depositAmount, this.account.getBalance());
        mySQLCon.CloseConnection();
        if (account == null){
            JOptionPane.showMessageDialog(null,"An Error Occured");
        } else {
            JOptionPane.showMessageDialog(null,"Successfully Deposited");
            new Deposit(account);
            dispose();
        }
    }

    //function to go back to the main menu
    public void goBackToMainMenu(){
        new MainMenu();
        dispose();
    }

}
