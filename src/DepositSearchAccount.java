import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositSearchAccount extends JFrame{
    private JPanel rootPanel;
    private JLabel depositLabel;
    private JLabel accountNumberLabel;
    private JTextField accountNumberTextField;
    private JButton enterButton;
    private JButton backButton;

    public DepositSearchAccount() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //action listener is user clicks enter button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchDepositWindow();
            }
        });

        //action listener if user clicks back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });
    }

    public void launchDepositWindow() {
        int accountNumber = Integer.parseInt(accountNumberTextField.getText());
        MySQLCon mySQLCon = new MySQLCon();
        Account account = mySQLCon.checkBalance(accountNumber);
        mySQLCon.CloseConnection();

        if (account == null) {
            JOptionPane.showMessageDialog(null,"Account Not Found");
        } else {
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
