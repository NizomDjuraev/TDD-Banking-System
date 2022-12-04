package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandStorage {

    Bank bank;
    public ArrayList<String> invalidCommands;
    public ArrayList<String> outputList;

    public CommandStorage(Bank bank) {
        this.bank = bank;
        invalidCommands = new ArrayList<>();
        outputList = new ArrayList<>();
    }


    public List<String> getInvalidCommands() {
        return invalidCommands;

    }

    public void addInvalidCommands(String stringInput) {
        invalidCommands.add(stringInput);
    }


    public List<String> returnOutput() {
        addValidCommands();
        returnInvalidCommands();
        return outputList;
    }

    private void addValidCommands() {
        for (Map.Entry<String, Account> accountEntry : bank.getAccounts().entrySet()) {
            String id = String.valueOf(accountEntry.getValue().getId());
            List<String> validInputs = bank.getAccounts().get(id).getOldInputs();
            outputList.addAll(validInputs);
        }
    }


    private void returnInvalidCommands() {
        outputList.addAll(invalidCommands);
    }
}



