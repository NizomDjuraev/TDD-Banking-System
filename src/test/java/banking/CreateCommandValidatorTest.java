package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;
    private CreateCommandValidator createCommandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        createCommandValidator = new CreateCommandValidator(bank);
    }

    @Test
    void valid_create_checking_account() {
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void valid_create_savings_account() {
        actual = commandValidator.validate("create savings 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_account() {
        actual = commandValidator.validate("create cd 12345678 0.1 1500");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_account_with_decimal_amount() {
        actual = commandValidator.validate("create cd 12345678 0.1 1500.05");
        assertTrue(actual);
    }

    @Test
    void create_checking_id_too_short() {
        actual = commandValidator.validate("create checking 1234567 0.1");
        assertFalse(actual);
    }

    @Test
    void create_savings_id_too_short() {
        actual = commandValidator.validate("create savings 1234567 0.1");
        assertFalse(actual);
    }

    @Test
    void create_cd_id_too_short() {
        actual = commandValidator.validate("create savings 1234567 0.1 500");
        assertFalse(actual);
    }

    @Test
    void create_id_too_long() {
        actual = commandValidator.validate("create checking 123456789 0.1");
        assertFalse(actual);
    }

    @Test
    void create_id_is_negative() {
        actual = commandValidator.validate("create checking -1234567 0.1");
        assertFalse(actual);
    }

    @Test
    void create_id_is_positive() {
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void create_id_has_no_id_input() {
        actual = commandValidator.validate("create checking 0.1");
        assertFalse(actual);
    }

    @Test
    void create_id_already_exists() {
        bank.createCheckingAccount("12345678", 0.1);
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void create_apr_is_negative() {
        actual = commandValidator.validate("create checking 12345678 -0.1");
        assertFalse(actual);
    }

    @Test
    void create_apr_has_no_apr_input() {
        actual = commandValidator.validate("create checking 12345678");
        assertFalse(actual);
    }

    @Test
    void create_apr_is_invalid() {
        actual = commandValidator.validate("create checking 12345678 0,0");
        assertFalse(actual);
    }

    @Test
    void create_apr_value_too_big() {
        actual = commandValidator.validate("create checking 12345678 50.0");
        assertFalse(actual);
    }

    @Test
    void create_cd_amount_too_big() {
        actual = commandValidator.validate("create cd 12345678 0.1 25000");
        assertFalse(actual);
    }

    @Test
    void create_cd_amount_too_small() {
        actual = commandValidator.validate("create cd 12345678 25");
        assertFalse(actual);
    }

    @Test
    void create_cd_without_initial_amount_entered() {
        actual = commandValidator.validate("create cd 12345678");
        assertFalse(actual);
    }

    @Test
    void create_cd_command_initial_balance_zero() {
        actual = commandValidator.validate("create cd 12345678 0");
        assertFalse(actual);
    }

    @Test
    void create_cd_amount_not_integer() {
        actual = commandValidator.validate("create cd 12345678 abc");
        assertFalse(actual);
    }

    @Test
    void create_command_missing_create_input() {
        actual = commandValidator.validate("checking 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void create_missing_account_type() {
        actual = commandValidator.validate("create 1234fe5678 0.1");
        assertFalse(actual);
    }


    @Test
    void create_contains_invalid_account_type() {
        actual = commandValidator.validate("create investment 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void create_command_has_too_many_inputs() {
        actual = commandValidator.validate("create checking investment ira 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void create_command_has_too_few_inputs() {
        actual = commandValidator.validate("create 12345678");
        assertFalse(actual);
    }

    @Test
    void inputs_out_of_order() {
        actual = commandValidator.validate("checking create 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void cd_amount_below_min() {
        actual = commandValidator.validate("checking cd 12345678 0.1 500");
        assertFalse(actual);
    }

    @Test
    void cd_amount_above_max() {
        actual = commandValidator.validate("checking cd 12345678 0.1 50000");
        assertFalse(actual);
    }

    @Test
    void cd_amount_boundary_below_min() {
        actual = commandValidator.validate("checking cd 12345678 0.1 999");
        assertFalse(actual);
    }

    @Test
    void cd_amount_boundary_above_max() {
        actual = commandValidator.validate("checking cd 12345678 0.1 10001");
        assertFalse(actual);
    }

    @Test
    void create_id_is_unique() {
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void invalid_create_id_is_non_integer() {
        actual = commandValidator.validate("create checking 1234a678 0.1");
        assertFalse(actual);
    }


    @Test
    void valid_apr_lower_boundary() {
        actual = commandValidator.validate("create checking 12345678 0.01");
        assertTrue(actual);
    }

    @Test
    void valid_apr_upper_boundary() {
        actual = commandValidator.validate("create checking 12345678 0.09");
        assertTrue(actual);
    }

    @Test
    void valid_apr_minimum_value() {
        actual = commandValidator.validate("create checking 12345678 0.000000000001");
        assertTrue(actual);
    }

    @Test
    void valid_apr_maximum_value() {
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void valid_cd_minimum_amount() {
        actual = commandValidator.validate("create cd 12345678 0.1 1000");
        assertTrue(actual);
    }

    @Test
    void valid_cd_minimum_boundary_amount() {
        actual = commandValidator.validate("create cd 12345678 0.1 1001");
        assertTrue(actual);
    }

    @Test
    void valid_cd_maximum_amount() {
        actual = commandValidator.validate("create cd 12345678 0.1 10000");
        assertTrue(actual);
    }

    @Test
    void valid_cd_maximum_boundary_amount() {
        actual = commandValidator.validate("create cd 12345678 0.1 9999");
        assertTrue(actual);
    }

    @Test
    void command_valid_case_insensitivity() {
        actual = commandValidator.validate("create chECKing 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void apr_below_min_boundary() {
        actual = commandValidator.validate("create checking 12345678 -0.1");
        assertFalse(actual);
    }

    @Test
    void apr_above_min_boundary() {
        actual = commandValidator.validate("create checking 12345678 0.1");
        assertTrue(actual);
    }

    @Test
    void apr_below_max_boundary() {
        actual = commandValidator.validate("create checking 12345678 9.9");
        assertTrue(actual);
    }

    @Test
    void apr_above_max_boundary() {
        actual = commandValidator.validate("create checking 12345678 10.1");
        assertFalse(actual);
    }

    @Test
    void create_command_is_id_positive() {
        actual = createCommandValidator.isIdPositive("12345678");
        assertTrue(actual);
    }

    @Test
    void create_command_is_apr_positive() {
        actual = createCommandValidator.isAprPositive("1.0");
        assertTrue(actual);
    }


}
