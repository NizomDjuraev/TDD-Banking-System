package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    public static final String ID = "12345678";
    public static final double APR = 0.1;
    public static final double AMOUNT = 1500;

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void create_checking_account() {
        bank.createCheckingAccount(ID, APR);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_savings_account() {
        bank.createSavingsAccount(ID, APR);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_cd_account() {
        bank.createCdAccount(ID, APR, AMOUNT);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void create_several_checking_accounts() {
        bank.createCheckingAccount(ID, APR);
        bank.createCheckingAccount(ID + 1, APR);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

    @Test
    void create_several_savings_accounts() {
        bank.createSavingsAccount(ID, APR);
        bank.createSavingsAccount(ID + 1, APR);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

    @Test
    void create_several_cd_accounts() {
        bank.createCdAccount(ID, APR, AMOUNT);
        bank.createCdAccount(ID + 1, APR, AMOUNT);
        assertEquals(ID + 1, bank.getAccounts().get(ID + 1).getId());
    }

    @Test
    void deposit_into_checking_account() {
        bank.createCheckingAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        assertEquals(500, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_from_checking_account() {
        bank.createCheckingAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 250);
        assertEquals(250, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void several_deposits_into_checking_account() {
        bank.createCheckingAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.depositIntoAccount(ID, 500);
        assertEquals(1000, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void several_withdrawals_from_checking_account() {
        bank.createCheckingAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 250);
        bank.withdrawFromAccount(ID, 250);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }


    @Test
    void deposit_into_savings_account() {
        bank.createSavingsAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        assertEquals(500, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_from_savings_account() {
        bank.createSavingsAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 250);
        assertEquals(250, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void several_deposits_into_savings_account() {
        bank.createSavingsAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.depositIntoAccount(ID, 500);
        assertEquals(1000, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void several_withdrawals_from_savings_account() {
        bank.createSavingsAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 250);
        bank.withdrawFromAccount(ID, 250);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_from_checking_balance_cant_go__below_zero() {
        bank.createCheckingAccount(ID, APR);
        bank.withdrawFromAccount(ID, 500);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_zero_amount_from_checking_account_with_zero_balance() {
        bank.createCheckingAccount(ID, APR);
        bank.withdrawFromAccount(ID, 0);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_zero_amount_from_checking_account_with_nonzero_balance() {
        bank.createCheckingAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 0);
        assertEquals(500, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_zero_amount_from_savings_account_with_zero_balance() {
        bank.createCheckingAccount(ID, APR);
        bank.withdrawFromAccount(ID, 0);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_zero_amount_from_savings_account_with_nonzero_balance() {
        bank.createSavingsAccount(ID, APR);
        bank.depositIntoAccount(ID, 500);
        bank.withdrawFromAccount(ID, 0);
        assertEquals(500, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void valid_three_months_passed_into_checking_account_with_upper_boundary_of_minimum_balance() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 100);
        bank.passMonths(3);
        assertEquals(100.2502083912037, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_three_months_passed_into_savings_account_with_upper_boundary_of_minimum_balance() {
        bank.createSavingsAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 100);
        bank.passMonths(3);
        assertEquals(100.2502083912037, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_three_months_passed_into_cd_account_with_upper_boundary_of_minimum_balance() {
        bank.createCdAccount("12345678", 1.0, 1000);
        bank.passMonths(3);
        assertEquals(1010.045960887182, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_checking_account() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 1000);
        bank.passMonths(1);
        assertEquals(1000.8333333333334, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_savings_account() {
        bank.createSavingsAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 1000);
        bank.passMonths(1);
        assertEquals(1000.8333333333334, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_cd_account() {
        bank.createCdAccount("12345678", 1.0, 1000);
        bank.passMonths(1);
        assertEquals(1003.3375023152971, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_checking_account_with_below_minimum_balance() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 50);
        bank.passMonths(1);
        assertEquals(25.020833333333332, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_savings_account_with_below_minimum_balance() {
        bank.createSavingsAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 50);
        bank.passMonths(1);
        assertEquals(25.020833333333332, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_one_month_passed_into_cd_account_with_below_minimum_balance() {
        bank.createCdAccount("12345678", 1.0, 1000);
        bank.withdrawFromAccount("12345678", 950);
        bank.passMonths(1);
        assertEquals(0.0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void account_gone_valid_one_month_passed_into_cd_account_with_below_minimum_balance() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 0);
        bank.passMonths(3);
        assertEquals(0, bank.getAccounts().size());
    }


}
