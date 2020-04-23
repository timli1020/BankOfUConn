public class Account {
    private int accountNumber;
    private String name;
    private int balance;
    private String accountOpenDate;

    public Account(int _accountNumber, String _name, int _balance, String _accountOpenDate) {
        this.accountNumber = _accountNumber;
        this.name = _name;
        this.balance = _balance;
        this.accountOpenDate = _accountOpenDate;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getAccountOpenDate() {
        return this.accountOpenDate;
    }
}
