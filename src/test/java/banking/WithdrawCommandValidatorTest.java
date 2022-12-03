package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    public void valid_checking_account_withdraw_command() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    public void valid_savings_account_withdraw_command() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    public void valid_cd_account_withdraw_command() {
        bank.createCdAccount(12345678, 0.1, 300);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertTrue(actual);
    }


    @Test
    public void valid_withdraw_checking_command() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    public void valid_withdraw_savings_command() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    public void withdraw_command_valid_case_insensitivity() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("wITHDRaw 12345678 300");
        assertTrue(actual);
    }

    @Test
    public void valid_maximum_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 400");
        assertTrue(actual);
    }

    @Test
    public void valid_minimum_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    public void valid_maximum_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 1000");
        assertTrue(actual);
    }

    @Test
    public void valid_minimum_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    public void valid_upper_boundary_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 399");
        assertTrue(actual);
    }

    @Test
    public void valid_lower_boundary_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 1");
        assertTrue(actual);
    }

    @Test
    public void valid_upper_boundary_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 999");
        assertTrue(actual);
    }

    @Test
    public void valid_lower_boundary_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 1");
        assertTrue(actual);
    }

    @Test
    public void invalid_upper_boundary_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 401");
        assertFalse(actual);
    }

    @Test
    public void invalid_lower_boundary_withdraw_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 -1");
        assertFalse(actual);
    }

    @Test
    public void invalid_upper_boundary_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 1001");
        assertFalse(actual);
    }

    @Test
    public void invalid_lower_boundary_withdraw_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 -1");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_has_no_inputs() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    public void invalid_checking_account_withdraw_account_does_not_exist() {
        actual = commandValidator.validate("withdraw 12345678 300");
        assertFalse(actual);
    }

    @Test
    public void invalid_command_no_inputs_given() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    public void invalid_command_too_many_inputs_given() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 12345678 12345678 checking");
        assertFalse(actual);
    }

    @Test
    public void withdraw_input_missing_from_command() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("12345678 500");
        assertFalse(actual);
    }

    @Test
    public void withdraw_command_missing_id() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 500");
        assertFalse(actual);
    }

    @Test
    public void cannot_withdraw_bank_account_does_not_exist() {
        actual = commandValidator.validate("withdraw 12345678 300");
        assertFalse(actual);
    }

    @Test
    public void cannot_withdraw_from_cd_account_with_amount_less_than_account_balance() {
        bank.createCdAccount(12345678, 0.1, 1500);
        actual = commandValidator.validate("withdraw 12345678 300");
        assertFalse(actual);
    }

    @Test
    public void cannot_withdraw_negative_amount_into_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 -300");
        assertFalse(actual);
    }

    @Test
    public void cannot_withdraw_negative_amount_into_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 -300");
        assertFalse(actual);
    }

    @Test
    public void inputs_are_in_wrong_order() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 300 12345678");
        assertFalse(actual);
    }

    @Test
    public void withdraw_command_has_negative_id() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw -12345678 300");
        assertFalse(actual);
    }

    @Test
    public void withdraw_command_has_non_integer_id() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12abc678 300");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_account_id_too_short() {
        actual = commandValidator.validate("withdraw 1234 300");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_account_id_too_long() {
        actual = commandValidator.validate("withdraw 1234567890 500");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_misspelled() {
        actual = commandValidator.validate("withdrew 12345678 500");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_has_non_integer_amount() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 5.a0");
        assertFalse(actual);
    }

    @Test
    public void invalid_withdraw_command_amount_is_noninteger_and_negative() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("withdraw 12345678 -50@.");
        assertFalse(actual);
    }
}
