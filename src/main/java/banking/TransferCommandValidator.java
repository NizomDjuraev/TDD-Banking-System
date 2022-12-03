package banking;


public class TransferCommandValidator {

    Bank bank;

    public TransferCommandValidator(Bank bank) {
        this.bank = bank;
    }


    public boolean validateTransferCommand(String commandString) {
        String[] commandId = commandString.split(" ");
        if (bothAccountIdValid(commandId[1], commandId[2])) {
            return (validWithdrawRules(commandId[1], commandId[3]) && validDepositRules(commandId[2], commandId[3]));
        }
        return false;
    }

    private boolean bothAccountIdValid(String fromId, String toId) {
        return bothIdLengthValid(fromId, toId)
                && bothAreIntegers(fromId, toId)
                && bothAccountsExistAlready(fromId, toId)
                && containsNoSpecialCharacters(fromId)
                && containsNoSpecialCharacters(toId)
                && areBothIntPositive(fromId, toId);
    }

    private boolean validWithdrawRules(String fromId, String amountWithdrawn) {
        WithdrawCommandValidator withdrawCommandValidator = new WithdrawCommandValidator(bank);
        String withdrawCommandString = String.join(" ", "withdraw", fromId, amountWithdrawn);
        return (withdrawCommandValidator.validateWithdrawCommand(withdrawCommandString));
    }

    private boolean validDepositRules(String toId, String amountDeposited) {
        DepositCommandValidator depositCommandValidator = new DepositCommandValidator(bank);
        String depositCommandString = String.join(" ", "deposit", toId, amountDeposited);
        return (depositCommandValidator.validateDepositCommand(depositCommandString));
    }

    private boolean doesBankExistAlready(String commandId) {
        return bank.doesIdExist(Integer.parseInt(commandId));
    }

    private boolean bothAccountsExistAlready(String fromId, String toId) {
        return (doesBankExistAlready(fromId) && doesBankExistAlready(toId));
    }


    private boolean isInteger(String commandId) {
        try {
            Integer.parseInt(commandId);
        } catch (Exception notInteger) {
            return false;
        }
        return true;
    }

    private boolean bothIdLengthValid(String fromId, String ToId) {
        return (fromId.length() == 8 && ToId.length() == 8);
    }

    private boolean bothAreIntegers(String fromId, String toId) {
        return (isInteger(fromId) && isInteger(toId));
    }

    private boolean isIntPositive(String inputAmount) {
        return (Integer.parseInt(inputAmount) >= 0);
    }

    private boolean areBothIntPositive(String fromId, String toId) {
        return (isIntPositive(fromId) && isIntPositive(toId));
    }

    private boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

}
