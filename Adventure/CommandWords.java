/**
 * This class is part of the "Adventure" application. "Adventure" is a text
 * based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class CommandWords {
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "help", "go", "back", "inventory", "search", "talk", "take", "drop", "give", "quit"
        };

    /**
     * Constructor - initialize the command words.
     */
    public CommandWords() {
    }

    /**
     * Check whether a given String is a valid command word.
     * @param str the String to be checked
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String str) {
        for (String command : validCommands) {
            if (command.equals(str))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Print all valid commands to System.out.
     */
    public void showAll() {
        for (String command : validCommands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}