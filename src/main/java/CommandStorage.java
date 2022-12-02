import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    public List<String> invalidCommands;

    public CommandStorage() {
        invalidCommands = new ArrayList<>();
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;

    }

    public void addInvalidCommands(String stringInput) {
        invalidCommands.add(stringInput);
    }
}
