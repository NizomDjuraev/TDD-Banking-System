package banking;

public class Account {
    Bank bank;
    private int id;
    private double apr;
    double balance;
    String type;
    int monthsPassed = 0;

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

    public int getMonthsPassed() {
        return monthsPassed;
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

    private double accountBalanceUpdateAfterOneMonth(double runningBalance) {
        return this.balance += (((this.apr / 100) / 12) * runningBalance);
    }

    public double accountBalanceAfterPenaltyFee() {
        if (25 > this.balance) {
            return this.balance = 0;
        } else {
            return this.balance -= 25;
        }
    }

    public void passMonthsIntoAccount(int monthsPassedIntoAccount) {
        monthsPassed += monthsPassedIntoAccount;
        if (this.balance == 0) {
        }
        if (this.balance < 100) {
            updatedAccountBelowMinimumBalanceCalculation();
        }
        if (this.balance >= 100) {
            updatedAccountAboveMinimumBalanceCalculation();
        }
    }


    public void updatedAccountBelowMinimumBalanceCalculation() {
        if (this.type.equalsIgnoreCase("cd")) {
            int cdMonthCalculation;
            for (cdMonthCalculation = 4 * monthsPassed; cdMonthCalculation > 0; cdMonthCalculation--) {
                accountBalanceUpdateAfterOneMonth(accountBalanceAfterPenaltyFee());
            }
        }
        if (this.type.equalsIgnoreCase("checking") || this.type.equalsIgnoreCase("savings")) {
            int checkingAndSavingsCalculation;
            for (checkingAndSavingsCalculation = monthsPassed; checkingAndSavingsCalculation > 0; checkingAndSavingsCalculation--) {
                accountBalanceUpdateAfterOneMonth(accountBalanceAfterPenaltyFee());
            }
        }
    }

    public void updatedAccountAboveMinimumBalanceCalculation() {
        if (this.type.equalsIgnoreCase("cd")) {
            int cdMonthCalculation;
            for (cdMonthCalculation = 4 * monthsPassed; cdMonthCalculation > 0; cdMonthCalculation--) {
                accountBalanceUpdateAfterOneMonth(this.balance);
            }
        } else {
            int checkingAndSavingsCalculation;
            for (checkingAndSavingsCalculation = monthsPassed; checkingAndSavingsCalculation > 0; checkingAndSavingsCalculation--) {
                accountBalanceUpdateAfterOneMonth(this.balance);
            }
        }
    }


}
