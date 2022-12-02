public class CdAccount extends Account {

    CdAccount(String type, int id, double apr, double amount) {
        super(type, id, apr);
        this.balance = amount;
    }
}
