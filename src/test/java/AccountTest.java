import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    public static final Integer ID = 12345678;
    public static final double APR = 0.2;
    public static final double BALANCE = 0.0;
    Account checking;
    Account savings;
    Account cd;

    @BeforeEach
    void setup() {
        checking = new Account(ID, APR, BALANCE);
        savings = new Account(ID, APR, BALANCE);
        cd = new Account(ID, APR, BALANCE);
    }

    @Test
    void does_the_checking_account_have_an_id() {
        assertEquals(ID, checking.getId());
    }

    @Test
    void does_the_checking_account_have_an_apr() {
        assertEquals(APR, checking.getApr());
    }

    @Test
    void does_the_checking_account_have_a_starting_balance() {
        assertEquals(BALANCE, checking.getBalance());
    }


    @Test
    void does_the_savings_account_have_an_id() {
        assertEquals(ID, savings.getId());
    }

    @Test
    void does_the_savings_account_have_an_apr() {
        assertEquals(APR, savings.getApr());
    }

    @Test
    void does_the_savings_account_have_a_starting_balance() {
        assertEquals(BALANCE, savings.getBalance());
    }


    @Test
    void does_the_cd_account_have_an_id() {
        assertEquals(ID, cd.getId());
    }

    @Test
    void does_the_cd_account_have_an_apr() {
        assertEquals(APR, cd.getApr());
    }

    @Test
    void does_the_cd_account_have_a_starting_balance() {
        assertEquals(BALANCE, cd.getBalance());
    }


    @Test
    void deposit_into_checking_account() {
        checking.depositIntoAccount(500);
        assertEquals(500, checking.getBalance());
    }

    @Test
    void withdraw_from_checking_account() {
        checking.depositIntoAccount(500);
        checking.withdrawFromAccount(250);
        assertEquals(250, checking.getBalance());
    }

    @Test
    void several_deposits_into_checking_account() {
        checking.depositIntoAccount(500);
        checking.depositIntoAccount(500);
        assertEquals(1000, checking.getBalance());
    }

    @Test
    void several_withdrawals_from_checking_account() {
        checking.depositIntoAccount(500);
        checking.withdrawFromAccount(250);
        checking.withdrawFromAccount(250);
        assertEquals(0, checking.getBalance());
    }


    @Test
    void deposit_into_savings_account() {
        savings.depositIntoAccount(500);
        assertEquals(500, savings.getBalance());
    }

    @Test
    void withdraw_from_savings_account() {
        savings.depositIntoAccount(500);
        savings.withdrawFromAccount(250);
        assertEquals(250, savings.getBalance());
    }

    @Test
    void several_deposits_into_savings_account() {
        savings.depositIntoAccount(500);
        savings.depositIntoAccount(500);
        assertEquals(1000, savings.getBalance());
    }

    @Test
    void several_withdrawals_from_savings_account() {
        savings.depositIntoAccount(500);
        savings.withdrawFromAccount(250);
        savings.withdrawFromAccount(250);
        assertEquals(0, savings.getBalance());
    }

    @Test
    void withdraw_from_checking_balance_cant_go__below_zero() {
        checking.withdrawFromAccount(500);
        assertEquals(0, checking.getBalance());
    }
}
