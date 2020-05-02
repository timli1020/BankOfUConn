
import java.sql.*;

public class MySQLCon {
    private String username;
    private String password;
    private String mySQLConnectorPath;
    private Connection con;
    private PreparedStatement preparedStatement;

    //constructor for the MySQLCon object
    public MySQLCon() {
        this.username = "root";
        this.password = "eix3OhNg";
        this.mySQLConnectorPath = "jdbc:mysql://localhost:3306/cse4701s20_project2";
        this.preparedStatement = null;
        this.con = null;

        //get the mysql connection
        try {
            //get the sql connector class
            this.con = DriverManager.getConnection(this.mySQLConnectorPath, this.username, this.password);
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            System.out.println("SQLState: " + error.getSQLState());
            System.out.println("VendorError: " + error.getErrorCode());
        }

        System.out.println("Successfully connected to mysql");
    }

    //function to insert the account into the account table
    public SQLException createAccount(Account account) {
        try {

            assert this.con != null;
            //query to insert the account/row into the accountt able
            String query = "insert into account(name_on_account, balance) " +
                    "values(?, ?)";
            this.preparedStatement = this.con.prepareStatement(query);
            this.preparedStatement.setString(1, account.getName());
            this.preparedStatement.setInt(2, account.getBalance());

            this.preparedStatement.execute();

        } catch (SQLException error) {
            return error;
        }
        return null;
    }

    //function to select the account from the account table
    public Account checkBalance(int accountNumber) {
        Statement stmt;
        ResultSet rs = null;
        Account account;
        try {
            assert this.con != null;
            //query to select the account from the table where the account_no = user input
            String query = "select * from account where account_no = ? for share";
            this.preparedStatement = this.con.prepareStatement(query);
            this.preparedStatement.setInt(1, accountNumber);
            rs = this.preparedStatement.executeQuery();

            String name = null;
            int balance = 0;
            String dateOpened = null;

            if (rs.next()) {
                name = rs.getString("name_on_account");
                balance = rs.getInt("balance");
                dateOpened = rs.getString("account_open_date");
                account = new Account(accountNumber, name, balance, dateOpened);
            } else {
                account = null;
            }

        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            System.out.println("SQLState: " + error.getSQLState());
            System.out.println("VendorError: " + error.getErrorCode());
            return null;
        }
        return account;
    }

    public Account Deposit(int accountNumber, int depositAmount, int currentBalance) {
        Statement stmt;
        ResultSet rs = null;
        Account account;
        int newBalance = depositAmount + currentBalance;

        try {
            assert this.con != null;
            //query to select balance from the account from the table where the account_no = user input
            String query = "update account set balance = ? where account_no = ?";
            this.preparedStatement = this.con.prepareStatement(query);
            this.preparedStatement.setInt(1, newBalance);
            this.preparedStatement.setInt(2, accountNumber);
            this.preparedStatement.executeUpdate();


            account = checkBalance(accountNumber);

        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            System.out.println("SQLState: " + error.getSQLState());
            System.out.println("VendorError: " + error.getErrorCode());
            return null;
        }
        return account;
    }

    public Account Withdraw(int accountNumber, int withdrawAmmount, int currentBalance) {
        Statement stmt;
        ResultSet rs = null;
        Account account;
        int newBalance = currentBalance - withdrawAmmount;
        try {
            assert this.con != null;
            //query to select balance from the account from the table where the account_no = user input
            String query = "update account set balance = ? where account_no = ?";
            this.preparedStatement = this.con.prepareStatement(query);
            this.preparedStatement.setInt(1, newBalance);
            this.preparedStatement.setInt(2, accountNumber);
            this.preparedStatement.executeUpdate();


            account = checkBalance(accountNumber);

        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            System.out.println("SQLState: " + error.getSQLState());
            System.out.println("VendorError: " + error.getErrorCode());
            return null;
        }
        return account;
    }

    public void CloseConnection(){
        if (this.preparedStatement != null) {
            try {
                this.preparedStatement.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (this.con != null) {
            try {
                this.con.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
}

