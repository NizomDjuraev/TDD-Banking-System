import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    public static final Integer ID = 12345678;
    public static final double APR = 0.2;
    public static final double BALANCE = 0.0;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void create_checking_account() {
        bank.createCheckingAccount(ID, APR, BALANCE);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_savings_account() {
        bank.createSavingsAccount(ID, APR, BALANCE);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_cd_account() {
        bank.createCdAccount(ID, APR, BALANCE);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_several_checking_accounts() {
        bank.createCheckingAccount(ID, APR, BALANCE);
        bank.createCheckingAccount(ID + 1, APR, BALANCE);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

    @Test
    void create_several_savings_accounts() {
        bank.createSavingsAccount(ID, APR, BALANCE);
        bank.createSavingsAccount(ID + 1, APR, BALANCE);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

    @Test
    void create_several_cd_accounts() {
        bank.createCdAccount(ID, APR, BALANCE);
        bank.createCdAccount(ID + 1, APR, BALANCE);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

}
