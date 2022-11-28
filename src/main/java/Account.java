public class Account {

    private Integer id;
    private double apr;
    private double balance;

    public Account(Integer id, double apr, double balance) {
        this.id = id;
        this.apr = apr;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public double getApr() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public void depositIntoAccount(double amount) {
        this.balance = this.balance + amount;
    }

    public void withdrawFromAccount(double amount) {
        if (amount > this.balance) {
            balance = 0;
        } else {
            this.balance = this.balance - amount;
        }
    }
}
