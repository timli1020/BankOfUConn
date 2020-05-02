import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSearch extends JFrame {
    private JPanel rootPanel;
    private JLabel accountSearchLabel;
    private JTextField accountNumberTextField;
    private JLabel accountNumberLabel;
    private JButton searchButton;
    private JButton backButton;

    public AccountSearch() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //action listener for going back to main menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });

        //action listener for searching
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchAccount();
            }
        });
    }

    public void searchAccount() {
        int accountNumber = Integer.parseInt(accountNumberTextField.getText());
        MySQLCon mySQLCon = new MySQLCon();
        Account account = mySQLCon.checkBalance(accountNumber);
        mySQLCon.CloseConnection();

        if (account == null) {
            JOptionPane.showMessageDialog(null,"Account Not Found");
        } else {
            DisplayAccount.main(account);
        }
    }

    //function to go back to the main menu
    public void goBackToMainMenu(){
        new MainMenu();
        dispose();
    }
}
