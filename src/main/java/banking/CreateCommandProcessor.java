package banking;

public class CreateCommandProcessor extends CommandProcessor {


    public CreateCommandProcessor(Bank bank) {
        super(bank);

    }

    public void processCreateCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        if (commandParts[1].equalsIgnoreCase("checking")) {
            bank.createCheckingAccount(commandParts[2], Double.parseDouble(commandParts[3]));
        }
        if (commandParts[1].equalsIgnoreCase("savings")) {
            bank.createSavingsAccount(commandParts[2], Double.parseDouble(commandParts[3]));
        }
        if (commandParts[1].equalsIgnoreCase("cd")) {
            bank.createCdAccount(commandParts[2], Double.parseDouble(commandParts[3]), Double.parseDouble(commandParts[4]));
        }
        addToInputHistory(commandParts[2], commandString);

    }
}
