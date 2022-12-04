package banking;

public class CommandProcessor {
    private Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }


    public void process(String command) {
        CreateCommandProcessor createCommandProcessor = new CreateCommandProcessor(bank);
        DepositCommandProcessor depositCommandProcessor = new DepositCommandProcessor(bank);
        WithdrawCommandProcessor withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
        TransferCommandProcessor transferCommandProcessor = new TransferCommandProcessor(bank);
        PassCommandProcessor passCommandProcessor = new PassCommandProcessor(bank);

        String[] inputCommand = command.split(" ");
        if (inputCommand[0].equalsIgnoreCase("create")) {
            createCommandProcessor.processCreateCommand(command);
        }
        if (inputCommand[0].equalsIgnoreCase("deposit")) {
            depositCommandProcessor.processDepositCommand(command);
        }
        if (inputCommand[0].equalsIgnoreCase("withdraw")) {
            withdrawCommandProcessor.processWithdrawCommand(command);
        }
        if (inputCommand[0].equalsIgnoreCase("transfer")) {
            transferCommandProcessor.transferCommandProcessor(command);
        }
        if (inputCommand[0].equalsIgnoreCase("pass")) {
            passCommandProcessor.passCommandProcessor(command);
        }
    }

}
