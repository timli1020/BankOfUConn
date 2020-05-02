import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferSearchAccount extends JFrame {
    private JPanel rootPanel;
    private JLabel transferLabel;
    private JTextField sourceAccountNumberTextField;
    private JLabel sourceAccountNumberLabel;
    private JTextField targetAccountNumberTextField;
    private JButton selectAccountsButton;
    private JButton backButton;

    public TransferSearchAccount() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //action listener for select accounts button
        selectAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EnterTransferWindow();
            }
        });

        //action listener for back button
        //add action listener if user clicks back to main menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });
    }

    public void EnterTransferWindow() {
        int sourceAccountNumber = Integer.parseInt(sourceAccountNumberTextField.getText());
        int targetAccountNumber = Integer.parseInt(targetAccountNumberTextField.getText());
        MySQLCon mySQLCon = new MySQLCon();
        Account sourceAccount = mySQLCon.checkBalance(sourceAccountNumber);
        Account targetAccount = mySQLCon.checkBalance(targetAccountNumber);
        mySQLCon.CloseConnection();


        if (sourceAccount == null){
            JOptionPane.showMessageDialog(null,"Source Account Does Not Exist");
        } else if (targetAccount == null) {
            JOptionPane.showMessageDialog(null,"Target Account Does Not Exist");
        } else {
            new Transfer(sourceAccount, targetAccount);
            dispose();
        }
    }

    //function to go back to the main menu
    public void goBackToMainMenu() {
        new MainMenu();
        dispose();
    }
}
