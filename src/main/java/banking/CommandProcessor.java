package banking;

public class CommandProcessor {
    private Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }


    public void process(String command) {
        CreateCommandProcessor createCommandProcessor = new CreateCommandProcessor(bank);
        DepositCommandProcessor depositCommandProcessor = new DepositCommandProcessor(bank);

        String[] inputCommand = command.split(" ");
        if (inputCommand[0].equalsIgnoreCase("create")) {
            createCommandProcessor.processCreateCommand(command);
        }
        if (inputCommand[0].equalsIgnoreCase("deposit")) {
            depositCommandProcessor.processDepositCommand(command);
        }
    }

}
