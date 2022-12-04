package banking;

public class DepositCommandProcessor extends CommandProcessor {


    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    public void processDepositCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.depositIntoAccount(commandParts[1], Double.parseDouble(commandParts[2]));
        addToInputHistory(commandParts[1], commandString);
    }

}
