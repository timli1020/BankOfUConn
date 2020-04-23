
import java.sql.*;

public class MySQLCon {
    private String username;
    private String password;
    private String mySQLConnectorPath;
    private Connection con;

    //constructor for the MySQLCon object
    public MySQLCon() {
        this.username = "root";
        this.password = "eix3OhNg";
        this.mySQLConnectorPath = "jdbc:mysql://localhost:3306/cse4701s20_project2";
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
        PreparedStatement preparedStatement = null;
        try {

            assert this.con != null;
            //query to insert the account/row into the accountt able
            String query = "insert into account(name_on_account, balance) " +
                    "values(?, ?)";
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setInt(2, account.getBalance());

            preparedStatement.execute();

        } catch (SQLException error) {
            return error;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (this.con != null) {
                try {
                    this.con.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return null;
    }

    //function to select the account from the account table
    public Account checkBalance(int accountNumber) {
        Statement stmt;
        ResultSet rs = null;
        Account account;
        PreparedStatement preparedStatement = null;
        try {
            assert this.con != null;
            //query to select the account from the table where the account_no = user input
            String query = "select * from account where account_no = ?";
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, accountNumber);
            rs = preparedStatement.executeQuery();

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
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (this.con != null) {
                try {
                    this.con.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return account;
    }
}

