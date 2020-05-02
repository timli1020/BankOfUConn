import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JPanel rootPanel;
    private JLabel MainMenuLabel;
    private JRadioButton createAccountRadioButton;
    private JRadioButton checkBalanceRadioButton;
    private JButton selectButton;
    private JRadioButton depositRadioButton;
    private JRadioButton withdrawRadioButton;
    private JRadioButton transferRadioButton;
    private JButton quitButton;

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
        buttonGroup.add(depositRadioButton);
        buttonGroup.add(withdrawRadioButton);
        buttonGroup.add(transferRadioButton);

        //the user's choice
        final String[] choice = {null};

        //action listener if user picks create account
        createAccountRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "createAccount";
            }
        });

        //action listener if user picks check balance
        checkBalanceRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "checkBalance";
            }
        });

        //action listener if user picks deposit
        depositRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "deposit";
            }
        });

        //action listener if user picks withdraw
        withdrawRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "withdraw";
            }
        });

        //action listener if user picks transfer
        transferRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                choice[0] = "transfer";
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
                    case "deposit":
                        dispose();
                        new DepositSearchAccount();
                        break;
                    case "withdraw":
                        new WithdrawSearchAccount();
                        dispose();
                        break;
                    case "transfer":
                        dispose();
                        break;
                    default:
                        break;

                }
            }
        });

        //action listener when user clicks quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quit();
            }
        });
    }

    public void quit() {
        System.exit(0);
    }

}
