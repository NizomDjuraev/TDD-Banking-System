package banking;

public class DepositCommandValidator {
    Bank bank;

    public DepositCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateDepositCommand(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[1]) && validAmount(command[2])) {
            if (depositCheckingValidation(commandString)) {
                return true;
            }
            return depositSavingsValidation(commandString);
        }
        return false;
    }


    private boolean doesBankExistAlready(String commandId) {

        return bank.doesIdExist(Integer.parseInt(commandId));
    }

    private boolean depositCheckingValidation(String commandString) {
        String[] command = commandString.split(" ");
        return isTypeChecking(command[1]) && validCheckingAmount(command[2]);
    }

    private boolean depositSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return isTypeSavings(command[1]) && validSavingsAmount(command[2]);
    }


    private boolean isTypeChecking(String commandString) {
        return bank.getType(Integer.parseInt(commandString)).equalsIgnoreCase("checking");
    }

    private boolean isTypeSavings(String commandString) {
        return bank.getType(Integer.parseInt(commandString)).equalsIgnoreCase("savings");
    }

    private boolean isTypeCd(String commandString) {
        return bank.getType(Integer.parseInt(commandString)).equalsIgnoreCase("cd");
    }

    private boolean validAccountId(String commandId) {
        return commandId.length() == 8
                && isInteger(commandId)
                && doesBankExistAlready(commandId)
                && !isTypeCd(commandId)
                && (containsNoSpecialCharacters(commandId))
                && isIntPositive(commandId);
    }

    private boolean validAmount(String commandAmount) {
        return isInteger(commandAmount)
                && isIntPositive(commandAmount)
                && (containsNoSpecialCharacters(commandAmount));
    }

    private boolean validCheckingAmount(String checkingAmount) {
        return isInteger(checkingAmount) && Integer.parseInt(checkingAmount) >= 0 && Integer.parseInt(checkingAmount) <= 1000;
    }

    private boolean validSavingsAmount(String savingsAmount) {
        return isInteger(savingsAmount) && Integer.parseInt(savingsAmount) >= 0 && Integer.parseInt(savingsAmount) <= 2500;
    }

    private boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

    private boolean isInteger(String commandId) {
        try {
            Integer.parseInt(commandId);
        } catch (Exception notInt) {
            return false;
        }
        return true;
    }

    private boolean isIntPositive(String inputAmount) {
        return (Integer.parseInt(inputAmount) >= 0);
    }

}
