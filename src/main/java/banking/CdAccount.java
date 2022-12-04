package banking;

public class CdAccount extends Account {

    CdAccount(String type, String id, double apr, double amount) {
        super(type, id, apr);
        this.balance = amount;
    }
}
