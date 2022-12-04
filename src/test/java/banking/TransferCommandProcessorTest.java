package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {

    CommandProcessor commandProcessor;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void valid_transfer_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        bank.depositIntoAccount(12345678, 400);
        bank.depositIntoAccount(87654321, 600);
        commandProcessor.process("transfer 12345678 87654321 400");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
        assertEquals(1000, bank.getAccounts().get(87654321).getBalance());
    }

    @Test
    void valid_transfer_from_savings_account_to_checking_account() {
        bank.createSavingsAccount(87654321, 1.0);
        bank.createCheckingAccount(12345678, 1.0);
        bank.depositIntoAccount(87654321, 600);
        bank.depositIntoAccount(12345678, 400);
        commandProcessor.process("transfer 87654321 12345678 400");
        assertEquals(200, bank.getAccounts().get(87654321).getBalance());
        assertEquals(800, bank.getAccounts().get(12345678).getBalance());
    }

    @Test
    void valid_transfer_from_checking_account_to_checking_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createCheckingAccount(87654321, 1.0);
        bank.depositIntoAccount(12345678, 400);
        bank.depositIntoAccount(87654321, 600);
        commandProcessor.process("transfer 12345678 87654321 400");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
        assertEquals(1000, bank.getAccounts().get(87654321).getBalance());
    }

    @Test
    void valid_transfer_from_savings_account_to_savings_account() {
        bank.createSavingsAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        bank.depositIntoAccount(12345678, 400);
        bank.depositIntoAccount(87654321, 400);
        commandProcessor.process("transfer 12345678 87654321 400");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
        assertEquals(800, bank.getAccounts().get(87654321).getBalance());
    }

    @Test
    void multiple_valid_transfers_from_checking_account_to_savings_account() {
        bank.createCheckingAccount(12345678, 1.0);
        bank.createSavingsAccount(87654321, 1.0);
        bank.depositIntoAccount(12345678, 400);
        bank.depositIntoAccount(87654321, 600);
        commandProcessor.process("transfer 12345678 87654321 200");
        commandProcessor.process("transfer 12345678 87654321 100");
        assertEquals(100, bank.getAccounts().get(12345678).getBalance());
        assertEquals(900, bank.getAccounts().get(87654321).getBalance());
    }

}
