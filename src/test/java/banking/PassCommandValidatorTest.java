package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassCommandValidatorTest {
    CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_pass_one_month() {
        actual = commandValidator.validate("pass 1");
        assertTrue(actual);
    }

    @Test
    void valid_pass_two_months() {
        actual = commandValidator.validate("pass 2");
        assertTrue(actual);
    }

    @Test
    void valid_pass_max_months() {
        actual = commandValidator.validate("pass 60");
        assertTrue(actual);
    }

    @Test
    void valid_pass_below_max_boundary_months() {
        actual = commandValidator.validate("pass 59");
        assertTrue(actual);
    }

    @Test
    void invalid_pass_above_max_boundary_months() {
        actual = commandValidator.validate("pass 61");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_negative_months() {
        actual = commandValidator.validate("pass -1");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_zero_months() {
        actual = commandValidator.validate("pass 0");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_months_input_noninteger_value() {
        actual = commandValidator.validate("pass 2b");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_months_input_contains_special_characters() {
        actual = commandValidator.validate("pass @2");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_months_input_is_a_decimal() {
        actual = commandValidator.validate("pass 2.0");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_no_months_input() {
        actual = commandValidator.validate("pass");
        assertFalse(actual);
    }

    @Test
    void invalid_command_no_input() {
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    void invalid_command_too_many_inputs() {
        actual = commandValidator.validate("pass 3 months ");
        assertFalse(actual);
    }
    

}
