package banking;

public class CommandValidator {
    private Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }


    public boolean validate(String command) {
        CreateCommandValidator createCommandValidator = new CreateCommandValidator(bank);
        DepositCommandValidator depositCommandValidator = new DepositCommandValidator(bank);

        String[] inputCommand = command.split(" ");
        if (inputCommand.length == 0) {
            return false;
        }
        if (inputCommand[0].equalsIgnoreCase("create")) {
            return createCommandValidator.validateCreateCommand(command);
        } else if (inputCommand[0].equalsIgnoreCase("deposit")) {
            return depositCommandValidator.validateDepositCommand(command);
        }
        return false;
    }
}

