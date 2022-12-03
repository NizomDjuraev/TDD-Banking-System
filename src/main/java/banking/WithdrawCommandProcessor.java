package banking;

public class WithdrawCommandProcessor {

    private Bank bank;

    public WithdrawCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processWithdrawCommand(String commandString) {
        String[] commandParts = commandString.split(" ");
        bank.withdrawFromAccount(Integer.parseInt(commandParts[1]), Double.parseDouble(commandParts[2]));
    }
}
