import java.util.HashMap;
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
        accounts.put(id, new CheckingAccount("checking", id, apr));
    }

    public void createSavingsAccount(int id, double apr) {
        accounts.put(id, new SavingsAccount("savings", id, apr));
    }

    public void createCdAccount(int id, double apr, double amount) {
        accounts.put(id, new CdAccount("cd", id, apr, amount));
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
}
