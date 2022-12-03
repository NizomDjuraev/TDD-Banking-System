package banking;

public class CommandValidator {
    private Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }


    public boolean validate(String command) {
        CreateCommandValidator createCommandValidator = new CreateCommandValidator(bank);
        DepositCommandValidator depositCommandValidator = new DepositCommandValidator(bank);
        WithdrawCommandValidator withdrawCommandValidator = new WithdrawCommandValidator(bank);
        TransferCommandValidator transferCommandValidator = new TransferCommandValidator(bank);

        String[] inputCommand = command.split(" ");
        if (inputCommand.length == 0) {
            return false;
        }
        if (inputCommand[0].equalsIgnoreCase("create")) {
            return createCommandValidator.validateCreateCommand(command);
        } else if (inputCommand[0].equalsIgnoreCase("deposit")) {
            return depositCommandValidator.validateDepositCommand(command);
        } else if (inputCommand[0].equalsIgnoreCase("withdraw")) {
            return withdrawCommandValidator.validateWithdrawCommand(command);
        } else if (inputCommand[0].equalsIgnoreCase("transfer")) {
            return transferCommandValidator.validateTransferCommand(command);
        } else if (inputCommand[0].equalsIgnoreCase("pass")) {
            return false;//depositCommandValidator.validateDepositCommand(command);
        }
        return false;
    }
}

