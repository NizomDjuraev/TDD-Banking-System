package banking;

public class DepositCommandValidator {
    private Bank bank;

    public DepositCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateDepositCommand(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[1]) && validAmount(command[2])) {
            if (depositCheckingValidation(commandString)) {
                return true;
            }
            if (depositSavingsValidation(commandString)) {
                return true;
            }
        }
        return false;
    }


    private boolean doesBankExistAlready(String commandId) {

        if (bank.doesIdExist(Integer.parseInt(commandId))) {
            return true;
        }
        return false;
    }

    private boolean depositCheckingValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (isTypeChecking(command[1]) && validCheckingAmount(command[2])) {
            return true;
        }
        return false;
    }

    private boolean depositSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (isTypeSavings(command[1]) && validSavingsAmount(command[2])) {
            return true;
        }
        return false;
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
        if (commandId.length() == 8
                && isInteger(commandId)
                && doesBankExistAlready(commandId)
                && !isTypeCd(commandId)
                && (commandId.matches("[0-9]+"))
                && isIntPositive(commandId)) {
            return true;
        }
        return false;
    }

    private boolean validAmount(String commandAmount) {
        if (isInteger(commandAmount)
                && (commandAmount.matches("[0-9]+"))
                && isIntPositive(commandAmount)) {
            return true;
        }
        return false;
    }

    private boolean validCheckingAmount(String checkingAmount) {
        if (isInteger(checkingAmount) && Integer.parseInt(checkingAmount) >= 0 && Integer.parseInt(checkingAmount) <= 1000) {
            return true;
        }
        return false;
    }

    private boolean validSavingsAmount(String savingsAmount) {
        if (isInteger(savingsAmount) && Integer.parseInt(savingsAmount) >= 0 && Integer.parseInt(savingsAmount) <= 2500) {
            return true;
        }
        return false;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (Exception notInt) {
            return false;
        }
        return true;
    }

    private boolean isIntPositive(String inputAmount) {
        return (Integer.parseInt(inputAmount) >= 0);
    }

}
