package banking;

public class WithdrawCommandValidator {
    private Bank bank;

    public WithdrawCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateWithdrawCommand(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[1]) && validAmount(command[2])) {
            if (withdrawCheckingValidation(commandString)) {
                return true;
            }
            if (withdrawSavingsValidation(commandString)) {
                return true;
            }
            return withdrawCdValidation(commandString);
        }
        return false;
    }

    private boolean withdrawCheckingValidation(String commandString) {
        String[] command = commandString.split(" ");
        return isTypeChecking(command[1]) && validCheckingAmount(command[2]);
    }


    private boolean withdrawSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return isTypeSavings(command[1]) && validSavingsAmount(command[2]);
    }

    private boolean withdrawCdValidation(String commandString) {
        String[] command = commandString.split(" ");
        return isTypeCd(command[1]) && validCdAmount(command[1], command[2]);
    }


    private boolean validAccountId(String commandId) {
        return commandId.length() == 8
                && isInteger(commandId)
                && doesBankExistAlready(commandId)
                && (containsNoSpecialCharacters(commandId))
                && isIntPositive(commandId);
    }

    private boolean validAmount(String commandAmount) {
        return isInteger(commandAmount)
                && isIntPositive(commandAmount)
                && (containsNoSpecialCharacters(commandAmount));
    }

    private boolean isInteger(String commandId) {
        try {
            Integer.parseInt(commandId);
        } catch (Exception notInt) {
            return false;
        }
        return true;
    }

    private boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

    private boolean isIntPositive(String inputAmount) {
        return (Integer.parseInt(inputAmount) >= 0);
    }

    private boolean doesBankExistAlready(String commandId) {
        return bank.doesIdExist(Integer.parseInt(commandId));
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

    private boolean validCheckingAmount(String checkingAmount) {
        return isInteger(checkingAmount) && Integer.parseInt(checkingAmount) >= 0 && Integer.parseInt(checkingAmount) <= 400;
    }

    private boolean validSavingsAmount(String savingsAmount) {
        return isInteger(savingsAmount) && Integer.parseInt(savingsAmount) >= 0 && Integer.parseInt(savingsAmount) <= 1000;
    }

    private boolean validCdAmount(String cdId, String cdAmount) {
        return isInteger(cdAmount) && Integer.parseInt(cdAmount) == bank.getAccounts().get(Integer.parseInt(cdId)).getBalance();
    }

}
