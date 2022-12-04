package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandValidatorTest {

    private CommandValidator commandValidator;
    private Bank bank;
    private boolean actual;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
    }


    @Test
    void no_inputs_given_in_command() {
        actual = commandValidator.validate(" ");
        assertFalse(actual);
    }

    @Test
    void command_missing_command_input() {
        actual = commandValidator.validate("checking 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void command_has_too_many_inputs() {
        actual = commandValidator.validate("create deposit credit investment ira 12345678 0.1");
        assertFalse(actual);
    }

    @Test
    void command_input_has_invalid_command() {
        actual = commandValidator.validate("digiorno checking 12345678 0.1");
        assertFalse(actual);
    }
}


    