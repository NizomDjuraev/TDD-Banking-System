package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    public static final String checkingTYPE = "checking";
    public static final String savingsTYPE = "savings";
    public static final String cdTYPE = "cd";
    public static final String ID = "12345678";
    public static final double APR = 0.1;
    public static final double BALANCE = 0.0;

    Account checking;
    Account savings;
    Account cd;

    @BeforeEach
    void setup() {
        checking = new Account(checkingTYPE, ID, APR);
        savings = new Account(savingsTYPE, ID, APR);
        cd = new Account(cdTYPE, ID, APR);
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


}
