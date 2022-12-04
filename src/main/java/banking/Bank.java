package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void createCheckingAccount(String id, double apr) {
        accounts.put(id, new CheckingAccount("Checking", id, apr));
    }

    public void createSavingsAccount(String id, double apr) {
        accounts.put(id, new SavingsAccount("Savings", id, apr));
    }

    public void createCdAccount(String id, double apr, double amount) {
        accounts.put(id, new CdAccount("Cd", id, apr, amount));
    }

    public void depositIntoAccount(String id, double amount) {
        accounts.get(id).deposit(amount);
    }

    public void withdrawFromAccount(String id, double amount) {
        accounts.get(id).withdraw(amount);
    }

    public String getType(String id) {
        return accounts.get(id).getType();
    }

    public boolean doesIdExist(String id) {
        return (accounts.containsKey(id));
    }

    public void passMonths(int addMonths) {
        ArrayList<String> zeroBalanceAccounts = new ArrayList<>();
        for (String id : accounts.keySet()) {
            Account account = accounts.get(id);
            if (account.balance == 0) {
                zeroBalanceAccounts.add(id);
            }
            account.passMonthsIntoAccount(addMonths);
        }
        accounts.keySet().removeAll(zeroBalanceAccounts);
    }

    public void addToInputs(String id, String command) {
        accounts.get(id).allInputsEntered.add(command);
    }
}
