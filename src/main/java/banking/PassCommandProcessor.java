package banking;

public class PassCommandProcessor {

    private Bank bank;

    public PassCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void passCommandProcessor(String commandString) {
        String[] commandMonthsInput = commandString.split(" ");
        bank.passMonths(Integer.parseInt(commandMonthsInput[1]));
    }
}
