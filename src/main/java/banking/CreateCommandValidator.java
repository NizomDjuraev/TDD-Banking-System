package banking;

public class CreateCommandValidator {
    Bank bank;

    public CreateCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateCreateCommand(String commandString) {

        if (createCheckingValidation(commandString)) {
            return checkingDetailsValidation(commandString);
        }
        if (createSavingsValidation(commandString)) {
            return savingsDetailsValidation(commandString);
        }

        if (createCdAccountValidation(commandString)) {
            return cdDetailsValidation(commandString);
        } else {
            return false;
        }

    }


    public boolean createCheckingValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("checking")) {
            return command.length == 4;
        }
        return false;
    }

    public boolean createSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("savings")) {
            return command.length == 4;
        }
        return false;
    }

    public boolean createCdAccountValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("cd")) {
            return command.length == 5;
        }
        return false;
    }


    public boolean checkingDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2])
                && validAPR(command[3]);
    }

    public boolean savingsDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2]) && validAPR(command[3]);
    }

    public boolean cdDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2]) && validAPR(command[3]) && validCdAmount(command[4]);
    }

    public boolean validAPR(String commandString) {
        return isDouble(commandString) && isAprPositive(commandString) && isAprBetweenMaxMin(commandString);
    }


    public boolean isAprBetweenMaxMin(String commandApr) {
        return (Double.parseDouble(commandApr) >= 0 && Double.parseDouble(commandApr) <= 10);
    }

    public boolean validAccountId(String commandId) {

        return commandId.length() == 8
                && isInteger(commandId)
                && isIdPositive(commandId)
                && (containsNoSpecialCharacters(commandId))
                && !(bank.doesIdExist(commandId));
    }

    public boolean isIdPositive(String commandId) {
        return (Integer.parseInt(commandId) > 0);
    }

    public boolean isAprPositive(String commandApr) {
        return (Double.parseDouble(commandApr) > 0);
    }

    public boolean validCdAmount(String cdAmount) {
        return isDouble(cdAmount) && Double.parseDouble(cdAmount) >= 1000 && Double.parseDouble(cdAmount) <= 10000;
    }

    public boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (Exception notInt) {
            return false;
        }
        return true;
    }


    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
        } catch (Exception notFloat) {
            return false;
        }
        return true;


    }
}


