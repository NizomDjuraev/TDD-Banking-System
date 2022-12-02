package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandValidatorTest {

    CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }


    @Test
    public void invalid_command_no_inputs_given() {
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    public void invalid_command_too_many_inputs_given() {
        actual = commandValidator.validate("deposit 12345678 12345678 12345678 checking");
        assertFalse(actual);
    }

    @Test
    public void deposit_input_missing_from_command() {
        actual = commandValidator.validate("12345678 500");
        assertFalse(actual);
    }

    @Test
    public void deposit_command_missing_id() {
        actual = commandValidator.validate("deposit 500");
        assertFalse(actual);
    }

    @Test
    public void bank_account_does_not_exist() {
        actual = commandValidator.validate("deposit 12345678 500");
        assertFalse(actual);
    }

    @Test
    public void cannot_deposit_into_cd_account() {
        bank.createCdAccount(12345678, 0.1, 1500);
        actual = commandValidator.validate("deposit 12345678 500");
        assertFalse(actual);
    }

    @Test
    public void cannot_deposit_negative_amount_into_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 -500");
        assertFalse(actual);
    }

    @Test
    public void inputs_are_in_wrong_order() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 500 12345678");
        assertFalse(actual);
    }

    @Test
    public void deposit_command_has_negative_id() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit -12345678 500");
        assertFalse(actual);
    }

    @Test
    public void deposit_command_has_non_integer_id() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12abc678 500");
        assertFalse(actual);
    }

    @Test
    public void account_id_too_short() {
        actual = commandValidator.validate("deposit 1234 500");
        assertFalse(actual);
    }

    @Test
    public void account_id_too_long() {
        actual = commandValidator.validate("deposit 1234567890 500");
        assertFalse(actual);
    }

    @Test
    public void deposit_command_misspelled() {
        actual = commandValidator.validate("desposito 12345678 500");
        assertFalse(actual);
    }

    @Test
    public void deposit_command_has_non_integer_amount() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 5.a0");
        assertFalse(actual);
    }


    @Test
    public void checking_account_does_exist() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    public void savings_account_does_exist() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    public void valid_deposit_checking_command() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    public void valid_deposit_savings_command() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    public void command_valid_case_insensitivity() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("dePOSit 12345678 500");
        assertTrue(actual);
    }

    @Test
    public void valid_maximum_deposit_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    public void valid_minimum_deposit_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    public void valid_maximum_deposit_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 2500");
        assertTrue(actual);
    }

    @Test
    public void valid_minimum_deposit_savings_account() {
        bank.createSavingsAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    public void valid_upper_boundary_deposit_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 999");
        assertTrue(actual);
    }

    @Test
    public void valid_lower_boundary_deposit_checking_account() {
        bank.createCheckingAccount(12345678, 0.1);
        actual = commandValidator.validate("deposit 12345678 1");
        assertTrue(actual);
    }
}
