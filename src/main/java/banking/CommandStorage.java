package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    public List<String> invalidCommands;
    public List<String> validCommands;

    public CommandStorage() {
        invalidCommands = new ArrayList<>();
//        validCommands = new ArrayList<>();
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;

    }

    public void addInvalidCommands(String stringInput) {
        invalidCommands.add(stringInput);
    }

//    public void addValidCommands(String stringInput) {
//        validCommands.add(stringInput);
//    }
}
