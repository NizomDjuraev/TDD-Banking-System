public class CreateCommandProcessor {

    private Bank bank;

    public CreateCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCreateCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        if (commandParts[1].equalsIgnoreCase("checking")) {
            bank.createCheckingAccount(Integer.parseInt(commandParts[2]), Double.parseDouble(commandParts[3]));
        }
        if (commandParts[1].equalsIgnoreCase("savings")) {
            bank.createSavingsAccount(Integer.parseInt(commandParts[2]), Double.parseDouble(commandParts[3]));
        }
        if (commandParts[1].equalsIgnoreCase("cd")) {
            bank.createCdAccount(Integer.parseInt(commandParts[2]), Double.parseDouble(commandParts[3]), Double.parseDouble(commandParts[4]));
        }

    }
}
