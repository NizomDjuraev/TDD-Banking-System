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
        return bank.doesIdExist(commandId);
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
        return bank.getType(commandString).equalsIgnoreCase("checking");
    }

    private boolean isTypeSavings(String commandString) {
        return bank.getType(commandString).equalsIgnoreCase("savings");
    }

    private boolean isTypeCd(String commandString) {
        return bank.getType(commandString).equalsIgnoreCase("cd");
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
        return isDouble(commandAmount) && isDoublePositive(commandAmount);
    }

    private boolean validCheckingAmount(String checkingAmount) {
        return isDouble(checkingAmount) && Double.parseDouble(checkingAmount) >= 0 && Double.parseDouble(checkingAmount) <= 1000;
    }

    private boolean validSavingsAmount(String savingsAmount) {
        return isDouble(savingsAmount) && Double.parseDouble(savingsAmount) >= 0 && Double.parseDouble(savingsAmount) <= 2500;
    }

    private boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

    private boolean isDouble(String commandId) {
        try {
            Double.parseDouble(commandId);
        } catch (Exception notDouble) {
            return false;
        }
        return true;
    }

    private boolean isInteger(String commandId) {
        try {
            Integer.parseInt(commandId);
        } catch (Exception notInteger) {
            return false;
        }
        return true;
    }

    private boolean isDoublePositive(String inputAmount) {
        return (Double.parseDouble(inputAmount) >= 0);
    }

    private boolean isIntPositive(String inputAmount) {
        return (Integer.parseInt(inputAmount) >= 0);
    }

}
