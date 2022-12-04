package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {


    CommandStorage commandStorage;
    List<String> inputs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Bank bank = new Bank();
        commandStorage = new CommandStorage(bank);

    }

    @Test
    void is_invalid_list_empty() {
        assertTrue(commandStorage.getInvalidCommands().isEmpty());
    }

    @Test
    void add_to_list_of_invalid_commands() {
        commandStorage.addInvalidCommands("creatoration TDD");
        assertEquals("creatoration TDD", commandStorage.getInvalidCommands().get(0));
    }

    @Test
    void add_multiple_to_list_of_invalid_commands() {
        commandStorage.addInvalidCommands("Bob Ross");
        commandStorage.addInvalidCommands("Create account maybe");
        inputs.add("Bob Ross");
        inputs.add("Create account maybe");
        assertEquals(inputs, commandStorage.getInvalidCommands());
    }

    @Test
    void get_list_in_sequential_order() {
        commandStorage.addInvalidCommands("Bob Ross");
        commandStorage.addInvalidCommands("Create account maybe");
        inputs.add("Bob Ross");
        inputs.add("Create account maybe");
        assertEquals(inputs, commandStorage.getInvalidCommands());
        assertEquals(inputs.get(1), commandStorage.getInvalidCommands().get(1));
    }
}
