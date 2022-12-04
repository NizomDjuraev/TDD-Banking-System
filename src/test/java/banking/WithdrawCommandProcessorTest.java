package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandProcessorTest {

    CommandProcessor commandProcessor;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void valid_withdraw_command_from_checking_account_with_zero_balance_processed() {
        bank.createCheckingAccount("12345678", 1.0);
        commandProcessor.process("withdraw 12345678 300");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_withdraw_command_from_savings_account_with_zero_balance_processed() {
        bank.createSavingsAccount("12345678", 1.0);
        commandProcessor.process("withdraw 12345678 300");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_withdraw_command_from_checking_account_with_existing_balance_processed() {
        bank.createCheckingAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 300);
        commandProcessor.process("withdraw 12345678 200");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_withdraw_command_from_savings_account_with_existing_balance_processed() {
        bank.createSavingsAccount("12345678", 1.0);
        bank.depositIntoAccount("12345678", 300);
        commandProcessor.process("withdraw 12345678 100");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(200, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void valid_withdraw_command_from_cd_account_with_existing_balance_processed() {
        bank.createCdAccount("12345678", 1.0, 1000);
        bank.withdrawFromAccount("12345678", 1000);
        commandProcessor.process("withdraw 12345678 1000");
        assertTrue(bank.doesIdExist("12345678"));
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

}
