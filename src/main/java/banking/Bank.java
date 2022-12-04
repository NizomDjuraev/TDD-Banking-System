package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<Integer, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public void createCheckingAccount(int id, double apr) {
        accounts.put(id, new CheckingAccount("Checking", id, apr));
    }

    public void createSavingsAccount(int id, double apr) {
        accounts.put(id, new SavingsAccount("Savings", id, apr));
    }

    public void createCdAccount(int id, double apr, double amount) {
        accounts.put(id, new CdAccount("Cd", id, apr, amount));
    }

    public void depositIntoAccount(int id, double amount) {
        accounts.get(id).deposit(amount);
    }

    public void withdrawFromAccount(int id, double amount) {
        accounts.get(id).withdraw(amount);
    }

    public String getType(int id) {
        return accounts.get(id).getType();
    }

    public boolean doesIdExist(int id) {
        return (accounts.containsKey(id));
    }

    public void passMonths(int addMonths) {
        List<Integer> zeroBalanceAccounts = new ArrayList<>();
        for (Integer id : accounts.keySet()) {
            Account account = accounts.get(id);
            if (account.balance == 0) {
                zeroBalanceAccounts.add(id);
            }
            account.passMonthsIntoAccount(addMonths);
        }
        accounts.keySet().removeAll(zeroBalanceAccounts);
    }


}
