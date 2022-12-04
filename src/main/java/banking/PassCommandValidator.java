package banking;

public class PassCommandValidator {

    private Bank bank;

    public PassCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validatePassCommand(String command) {
        String[] commandMonths = command.split(" ");
        return (commandMonths.length <= 2 && isValidPassCommandMonths(commandMonths[1]));
    }

    private boolean isValidPassCommandMonths(String months) {
        return months.length() <= 2
                && isInteger(months)
                && months.matches("[0-9]+")
                && isMonthsBetweenBoundaries(months);
    }

    private boolean isInteger(String commandId) {
        try {
            Integer.parseInt(commandId);
        } catch (Exception notInteger) {
            return false;
        }
        return true;
    }

    private boolean isMonthsBetweenBoundaries(String months) {
        return (Integer.parseInt(months) >= 1 && Integer.parseInt(months) <= 60);
    }
}
