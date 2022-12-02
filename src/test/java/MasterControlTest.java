import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    MasterControl masterControl;
    List<String> input;

    @BeforeEach
    void setUp() {

        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(
                new CommandValidator(bank),
                new CommandProcessor(bank),
                new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void create_command_is_invalid_misspelled() {
        input.add("crate checking 12345678 1.5");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("crate checking 12345678 1.5", actual);
    }

    @Test
    void deposit_command_is_invalid_misspelled() {
        input.add("desposito 12345678 10");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("desposito 12345678 10", actual);
    }

    @Test
    void two_invalid_commands_misspelled() {
        input.add("crate checking 12345678 1.5");
        input.add("desposito 12345678 10");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("crate checking 12345678 1.5", actual.get(0));
        assertEquals("desposito 12345678 10", actual.get(1));
    }

    @Test
    void invalid_command_account_id_already_exists() {
        input.add("create checking 12345678 1.0");
        input.add("create checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("create checking 12345678 1.0", actual);
    }


}
