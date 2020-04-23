import javax.swing.*;
import java.awt.event.*;

public class DisplayAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JLabel accountNumberLabel;
    private JLabel dateOpenedLabel;

    public DisplayAccount(Account account) {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);
        setTitle("Bank Of UConn");

        //set the dialog to display the traveler account information
        accountNumberLabel.setText(Integer.toString(account.getAccountNumber()));
        nameLabel.setText(account.getName());
        balanceLabel.setText("$" + account.getBalance());
        dateOpenedLabel.setText(account.getAccountOpenDate());


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }


    public static void main(Account account) {
        DisplayAccount dialog = new DisplayAccount(account);
        dialog.pack();
        dialog.setVisible(true);
    }
}
