package banking;

public class WithdrawCommandProcessor extends CommandProcessor {


    public WithdrawCommandProcessor(Bank bank) {
        super(bank);

    }

    public void processWithdrawCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.withdrawFromAccount(commandParts[1], Double.parseDouble(commandParts[2]));
        addToInputHistory(commandParts[1], commandString);
    }
}
