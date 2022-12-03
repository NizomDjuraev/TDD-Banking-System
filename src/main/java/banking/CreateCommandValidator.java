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


    private boolean createCheckingValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("checking")) {
            return command.length == 4;
        }
        return false;
    }

    private boolean createSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("savings")) {
            return command.length == 4;
        }
        return false;
    }

    private boolean createCdAccountValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("cd")) {
            return command.length == 5;
        }
        return false;
    }


    private boolean checkingDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2])
                && validAPR(command[3]);
    }

    private boolean savingsDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2]) && validAPR(command[3]);
    }

    private boolean cdDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        return validAccountId(command[2]) && validAPR(command[3]) && validCdAmount(command[4]);
    }

    private boolean validAPR(String commandString) {
        return isDouble(commandString) && isAprPositive(commandString) && isAprBetweenMaxMin(commandString);
    }


    private boolean isAprBetweenMaxMin(String commandApr) {
        return (Double.parseDouble(commandApr) >= 0 && Double.parseDouble(commandApr) <= 10);
    }

    private boolean validAccountId(String commandId) {

        return commandId.length() == 8
                && isInteger(commandId)
                && isIdPositive(commandId)
                && (containsNoSpecialCharacters(commandId))
                && !(bank.doesIdExist(Integer.parseInt(commandId)));
    }

    private boolean isIdPositive(String commandId) {
        return (Integer.parseInt(commandId) > 0);
    }

    private boolean isAprPositive(String commandApr) {
        return (Double.parseDouble(commandApr) > 0);
    }

    private boolean validCdAmount(String cdAmount) {
        return isDouble(cdAmount) && Double.parseDouble(cdAmount) >= 1000 && Double.parseDouble(cdAmount) <= 10000;
    }

    private boolean containsNoSpecialCharacters(String commandId) {
        return commandId.matches("[0-9]+");
    }

    private boolean isInteger(String value) {
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


