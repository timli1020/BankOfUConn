import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateAccount extends JFrame {
    private JPanel rootPanel;
    private JLabel createNewAccountLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel openBalanceLabel;
    private JTextField openBalanceTextField;
    private JButton createAccountButton;
    private JButton backButton;

    public CreateAccount() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //action listener for create account button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                create();
            }
        });


        //action listener for going back to main menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });
    }

    //function to create the account and insert it into the account table
    public void create() {
        String name = nameTextField.getText();
        int openingBalance = Integer.parseInt(openBalanceTextField.getText());
        Account account = new Account(0, name, openingBalance, null);
        MySQLCon mySQLCon = new MySQLCon();
        SQLException error = mySQLCon.createAccount(account);

        if (error != null) {
            JOptionPane.showMessageDialog(null,
                    error.getMessage());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Account Successfully Created");
            new MainMenu();
            dispose();
        }
    }

    //function to go back to the main menu
    public void goBackToMainMenu(){
        new MainMenu();
        dispose();
    }
}
