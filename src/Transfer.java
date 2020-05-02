import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Transfer extends JFrame{

    private JPanel rootPanel;
    private JLabel transferLabel;
    private JLabel targetAccountLabel;
    private JLabel sourceAccountLabel;
    private JLabel sourceAccountNumberLabel;
    private JLabel targetAccountNumberLabel;
    private JLabel sourceAccountNameLabel;
    private JLabel targetAccountNameLabel;
    private JLabel sourceAccountBalanceLabel;
    private JLabel targetAccountBalanceLabel;
    private JLabel sourceAccountDateLabel;
    private JLabel targetAccountDateLabel;
    private JLabel transferAmountLabel;
    private JTextField transferAmountTextField;
    private JButton transferButton;
    private JButton backAccountSelectButton;
    private JButton goBackToMainButton;

    private Account sourceAccount;
    private Account targetAccount;

    public Transfer(Account _sourceAccount, Account _targetAccount) {
        this.sourceAccount = _sourceAccount;
        this.targetAccount = _targetAccount;

        setVisible(true);
        add(rootPanel);
        setTitle("Bank of UConn");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set source Account labels
        sourceAccountNumberLabel.setText(Integer.toString(this.sourceAccount.getAccountNumber()));
        sourceAccountNameLabel.setText(this.sourceAccount.getName());
        sourceAccountBalanceLabel.setText("$" + this.sourceAccount.getBalance());
        sourceAccountDateLabel.setText(this.sourceAccount.getAccountOpenDate());

        //set target Account labels
        targetAccountNumberLabel.setText(Integer.toString(this.targetAccount.getAccountNumber()));
        targetAccountNameLabel.setText(this.targetAccount.getName());
        targetAccountBalanceLabel.setText("$" + this.targetAccount.getBalance());
        targetAccountDateLabel.setText(this.targetAccount.getAccountOpenDate());

        //action listener for transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    PerformTransfer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //action listener for back to main menu button
        goBackToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goBackToMainMenu();
            }
        });

        //action listener for going back to account selection
        backAccountSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TransferSearchAccount();
                dispose();
            }
        });
    }

    public void PerformTransfer() throws InterruptedException {
        int transferAmount = Integer.parseInt(transferAmountTextField.getText());

        //check if there is enough money to transfer
        if (transferAmount > sourceAccount.getBalance()) {
            JOptionPane.showMessageDialog(null,"Insufficient Funds");
        } else {
            MySQLCon mySQLCon = new MySQLCon();
            Account newSourceAccount = mySQLCon.Withdraw(this.sourceAccount.getAccountNumber(), transferAmount, this.sourceAccount.getBalance());
            Account newTargetAccount = mySQLCon.Deposit(this.targetAccount.getAccountNumber(), transferAmount, this.targetAccount.getBalance());
            mySQLCon.CloseConnection();

            JOptionPane.showMessageDialog(null,"Transferring Money...");

            TimeUnit.SECONDS.sleep(10);


            new Transfer(newSourceAccount, newTargetAccount);
            dispose();
        }

    }

    //function to go back to the main menu
    public void goBackToMainMenu() {
        new MainMenu();
        dispose();
    }
}
