package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandProcessorTest {

    CommandProcessor commandProcessor;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    public void valid_create_checking_account_processed() {
        commandProcessor.process("create checking 12345678 1.0");
        assertTrue(bank.doesIdExist(12345678));
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(1.0, bank.getAccounts().get(12345678).getApr());
    }

    @Test
    public void valid_create_deposit_account_processed() {
        commandProcessor.process("create savings 12345678 1.0");
        assertTrue(bank.doesIdExist(12345678));
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(1.0, bank.getAccounts().get(12345678).getApr());
    }

    @Test
    public void valid_create_cd_account_processed() {
        commandProcessor.process("create cd 12345678 1.0 500");
        assertTrue(bank.doesIdExist(12345678));
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(1.0, bank.getAccounts().get(12345678).getApr());
        assertEquals(500, bank.getAccounts().get(12345678).getBalance());
    }


}
