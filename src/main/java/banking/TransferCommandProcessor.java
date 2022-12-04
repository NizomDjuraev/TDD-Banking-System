package banking;

public class TransferCommandProcessor extends CommandProcessor {


    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    public void transferCommandProcessor(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.withdrawFromAccount(commandParts[1], Double.parseDouble(commandParts[3]));
        bank.depositIntoAccount(commandParts[2], Double.parseDouble(commandParts[3]));
        addToInputHistory(commandParts[1], commandString);
        addToInputHistory(commandParts[2], commandString);
    }
}
