package banking;

public class TransferCommandProcessor {

    private Bank bank;

    public TransferCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void transferCommandProcessor(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.withdrawFromAccount(Integer.parseInt(commandParts[1]), Double.parseDouble(commandParts[3]));
        bank.depositIntoAccount(Integer.parseInt(commandParts[2]), Double.parseDouble(commandParts[3]));
    }
}
