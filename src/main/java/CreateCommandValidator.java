public class CreateCommandValidator {
    private Bank bank;// = new Bank();

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
            if (command.length == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean createSavingsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("savings")) {
            if (command.length == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean createCdAccountValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (command[1].equalsIgnoreCase("cd")) {
            if (command.length == 5) {
                return true;
            }
        }
        return false;
    }


    private boolean checkingDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[2])
                && validAPR(command[3])) {
            return true;
        }
        return false;
    }

    private boolean savingsDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[2]) && validAPR(command[3])) {
            return true;
        }
        return false;
    }

    private boolean cdDetailsValidation(String commandString) {
        String[] command = commandString.split(" ");
        if (validAccountId(command[2]) && validAPR(command[3]) && validCdAmount(command[4])) {
            return true;
        }
        return false;
    }

    private boolean validAPR(String commandString) {
        if (isDouble(commandString) && isAprPositive(commandString) && isAprBetweenMaxMin(commandString)) {
            return true;
        }
        return false;
    }


    private boolean isAprBetweenMaxMin(String commandApr) {
        return (Double.parseDouble(commandApr) > 0 && Double.parseDouble(commandApr) < 10.000000001);
    }

    private boolean validAccountId(String commandId) {

        if (commandId.length() == 8
                && isInteger(commandId)
                && (commandId.matches("[0-9]+"))
                && !(bank.doesIdExist(Integer.parseInt(commandId)))
                && isIdPositive(commandId)) {
            return true;
        }
        return false;
    }

    private boolean isIdPositive(String commandId) {
        return (Integer.parseInt(commandId) > 0);
    }

    private boolean isAprPositive(String commandApr) {
        return (Double.parseDouble(commandApr) > 0);
    }

    private boolean validCdAmount(String cdAmount) {
        if (isInteger(cdAmount) && Integer.parseInt(cdAmount) >= 1000 && Integer.parseInt(cdAmount) <= 10000) {
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

    private boolean isDouble(String commandString) {
        try {
            Double.parseDouble(commandString);
        } catch (Exception notFloat) {
            return false;
        }
        return true;


    }
}


