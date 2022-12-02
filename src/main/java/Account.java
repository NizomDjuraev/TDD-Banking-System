public class Account {

    private int id;
    private double apr;
    double balance;
    String type;

    public Account(String type, int id, double apr) {
        this.type = type;
        this.id = id;
        this.apr = apr;
        this.balance = 0;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public double getApr() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            balance = 0;
        } else {
            this.balance -= amount;
        }
    }
}
