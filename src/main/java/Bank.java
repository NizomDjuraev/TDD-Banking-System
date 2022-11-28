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

    public void createCheckingAccount(Integer id, double apr, double balance) {
        accounts.put(id, new Account(id, apr, balance));
    }

    public void createSavingsAccount(Integer id, double apr, double balance) {
        accounts.put(id, new Account(id, apr, balance));
    }

    public void createCdAccount(Integer id, double apr, double balance) {
        accounts.put(id, new Account(id, apr, balance));
    }
}
