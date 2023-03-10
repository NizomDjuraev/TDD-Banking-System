package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandProcessorTest {

    CommandProcessor commandProcessor;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }


    @Test
    void valid_deposit_checking_account_with_zero_balance_processed() {
        bank.createCheckingAccount("12345678", 1.0);
        commandProcessor.process("deposit 12345678 100");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_deposit_savings_account_with_zero_balance_processed() {
        bank.createSavingsAccount("12345678", 1.0);
        commandProcessor.process("deposit 12345678 100");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_deposit_checking_account_with_existing_balance_processed() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 100);
        commandProcessor.process("deposit 12345678 100");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(200, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_deposit_savings_account_with_existing_balance_processed() {
        bank.createSavingsAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 100);
        commandProcessor.process("deposit 12345678 100");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(200, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void multiple_valid_deposits_into_checking_account() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 100);
        commandProcessor.process("deposit 12345678 100");
        commandProcessor.process("deposit 12345678 300");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(500, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void multiple_valid_deposits_into_savings_account() {
        bank.createSavingsAccount("12345678", 1.0);
        commandProcessor.process("deposit 12345678 300");
        commandProcessor.process("deposit 12345678 300");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(600, bank.getAccounts().get("12345678").getBalance());
    }
}
