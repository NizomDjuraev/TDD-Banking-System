package banking;

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
                new CommandStorage(bank));
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
    void create_command_is_valid() {
        input.add("create checking 12345678 1.5");
        List<String> actual = masterControl.start(input);
        assertEquals("Checking 12345678 0.00 1.50", actual.get(0));
    }


    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }


    @Test
    void invalid_command_checking_account_already_exists() {
        input.add("Create checking 12345678 0.3");
        input.add("Create checking 12345678 0.3");
        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 12345678 0.00 0.30", actual.get(0));
    }

    @Test
    void invalid_command_savings_account_already_exists() {
        input.add("Create savings 12345678 0.3");
        input.add("Create savings 12345678 0.3");
        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Savings 12345678 0.00 0.30", actual.get(0));
    }

    @Test
    void invalid_command_cd_account_already_exists() {
        input.add("Create cd 12345678 0.3 1500");
        input.add("Create cd 12345678 0.3 1500");
        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Cd 12345678 1500.00 0.30", actual.get(0));
    }

    @Test
    void invalid_command_input_contains__typo() {
        input.add("Creature savings 12345678 0.3");

        assertSingleCommand("Creature savings 12345678 0.3", masterControl.start(input));
    }

    @Test
    void duplicate_input_still_displays_properly() {
        input.add("Create savings 12345678 0.6");
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(6, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Create savings 12345678 0.6", actual.get(4));
        assertEquals("Deposit 12345678 5000", actual.get(5));
    }

}
