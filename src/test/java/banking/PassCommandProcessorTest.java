package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassCommandProcessorTest {

    CommandProcessor commandProcessor;
    private Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void valid_pass_one_month_with_balance_greater_than_minimum_balance_threshold() {
        bank.createCheckingAccount(12345678, 1);
        bank.depositIntoAccount(12345678, 200);
        commandProcessor.process("pass 1");
        assertEquals(1, bank.getAccounts().get(12345678).getMonthsPassed());
    }

    @Test
    void valid_pass_two_months_with_balance_greater_than_minimum_balance_threshold() {
        bank.createCheckingAccount(12345678, 1);
        bank.depositIntoAccount(12345678, 200);
        commandProcessor.process("pass 2");
        assertEquals(2, bank.getAccounts().get(12345678).getMonthsPassed());
    }

    @Test
    void valid_pass_three_months_with_balance_greater_than_minimum_balance_threshold() {
        bank.createCheckingAccount(12345678, 1);
        bank.depositIntoAccount(12345678, 200);
        commandProcessor.process("pass 3");
        assertEquals(3, bank.getAccounts().get(12345678).getMonthsPassed());
    }


}
