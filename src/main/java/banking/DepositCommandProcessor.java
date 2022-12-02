package banking;

public class DepositCommandProcessor {

    private Bank bank;

    public DepositCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processDepositCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.depositIntoAccount(Integer.parseInt(commandParts[1]), Double.parseDouble(commandParts[2]));
    }
}
