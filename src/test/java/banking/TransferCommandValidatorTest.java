package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;
    private boolean actualTwo;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_transfer_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 200");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 200");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_zero_amount_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 0");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_zero_amount_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 0");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_max_amount_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 400");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_max_amount_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void valid_max_amount_below_boundary_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 399");
        assertTrue(actual);
    }

    @Test
    void valid_max_amount_below_boundary_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 999");
        assertTrue(actual);
    }

    @Test
    void valid_min_amount_above_boundary_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 1");
        assertTrue(actual);
    }

    @Test
    void valid_min_amount_above_boundary_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 1");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_decimal_amount_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 300.5");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_of_decimal_amount_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 500.5");
        assertTrue(actual);
    }

    @Test
    void valid_multiple_transfer_amount_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 300");
        actualTwo = commandValidator.validate("transfer 12345678 87654321 200");
        assertTrue(actual && actualTwo);
    }
    
    @Test
    void valid_multiple_transfer_amount_from_different_checking_account_to_same_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(12121212, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 300");
        actualTwo = commandValidator.validate("transfer 12121212 87654321 200");
        assertTrue(actual && actualTwo);
    }

    @Test
    void valid_transfer_from_checking_account_to_checking_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 200");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_from_savings_account_to_savings_account() {
        bank.createSavingsAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 200");
        assertTrue(actual);
    }

    @Test
    void valid_transfer_from_cd_account_to_savings_account_following_cd_withdraw_rules() {
        bank.createCdAccount(12345678, 1.0, 1500);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 1500");
        assertTrue(actual);
    }

    ///////////////////////////////////////////////////////////////////////
    @Test
    void invalid_transfer_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 500");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 2000");
        assertFalse(actual);
    }


    @Test
    void invalid_min_amount_below_boundary_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 -0.1");
        assertFalse(actual);
    }

    @Test
    void invalid_min_amount_below_boundary_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 -0.1");
        assertFalse(actual);
    }

    @Test
    void invalid_max_amount_above_boundary_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 401");
        assertFalse(actual);
    }

    @Test
    void valid_max_amount_above_boundary_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 1001");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_amount_input_contains_noninteger_values_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 @yo2");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_amount_input_contains_noninteger_values_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 87654321 12345678 JohnFry");
        assertFalse(actual);
    }

    @Test
    void invalid_multiple_over_max_limit_transfer_amount_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 1000");
        actualTwo = commandValidator.validate("transfer 12345678 87654321 1000");
        assertFalse(actual && actualTwo);
    }

    @Test
    void invalid_multiple_over_max_limit_transfer_amount_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 2000");
        actualTwo = commandValidator.validate("transfer 12345678 87654321 2000");
        assertFalse(actual && actualTwo);
    }

//    ADD TEST LATER AFTER PASS TIME
//    @Test
//    void invalid_multiple_transfer_amount_from_different_checking_account_to_same_savings_account() {
//        bank.createSavingsAccount(12345678, 1.0);
//        bank.createSavingsAccount(87654321, 1.0);
//        actual = commandValidator.validate("transfer 12345678 87654321 300");
//        actualTwo = commandValidator.validate("transfer 12345678 87654321 200");
//        assertFalse(actual && actualTwo);
//    }
//    @Test
//    void invalid_transfer_from_cd_account_to_savings_account_following_cd_withdraw_rules() {
//        bank.createCdAccount(12345678, 1.0, 1500);
//        bank.createSavingsAccount(87654321, 1.0);
//        actual = commandValidator.validate("transfer 12345678 87654321 1500");
//        assertFalse(actual);
//    }


    @Test
    void invalid_transfer_command_contains_no_inputs() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_command_contains_too_many_inputs() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87654321 54612378 300 ");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_command_fromId_too_short() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 1234 87654321 300 ");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_command_ToId_too_short() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 4321 300 ");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_command_fromId_contains_non_integer_values() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 1@abc678 87654321 300 ");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_command_ToId_contains_non_integer_values() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        actual = commandValidator.validate("transfer 12345678 87zx.21 300 ");
        assertFalse(actual);
    }
}
