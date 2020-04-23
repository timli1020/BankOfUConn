import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JPanel rootPanel;
    private JLabel MainMenuLabel;
    private JRadioButton createAccountRadioButton;
    private JRadioButton checkBalanceRadioButton;
    private JButton selectButton;

    public MainMenu() {
        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(createAccountRadioButton);
        buttonGroup.add(checkBalanceRadioButton);

        //the user's choice
        final String[] choice = {null};

        //action listener if user picks create account
        createAccountRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "createAccount";
            }
        });

        //action listener is user picks check balance
        checkBalanceRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "checkBalance";
            }
        });

        //action listener when user clicks select button
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //pull up the appropriate frame based on user's choice
                switch (choice[0]) {
                    case "createAccount":
                        new CreateAccount();
                        dispose();
                        break;
                    case "checkBalance":
                        new AccountSearch();
                        dispose();
                        break;
                    default:
                        break;

                }
            }
        });
    }
}
