/**
 * This class is part of the "Adventure" application. "Adventure" is a
 * text based adventure game.
 * 
 * This class holds information about a command that was issued by the user.
 * A command currently consists of three strings: a command word, a second
 * word, and a third word (for example, if the command was "give bread 
 * dwarf", then the three strings are "give", "bread", and "dwarf").
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is 
 * not known) then the command word is <null>.
 * 
 * If the command had only one word, then the second and third words are
 * <null>. If the command had only two words, then the third word is <null>.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class Command {
    private String commandWord;
    private String secondWord;
    private String thirdWord;
    
    /**
     * Create a command object. First, second, and third word must be
     * supplied, but one, two, or three of them can be null.
     * @param firstWord The first word of the command. Null is the command
     *                  was not recognized.
     * @param secondWord The second word of the command.
     * @param thirdWord the third word of the command.
     */
    public Command(String firstWord, String secondWord, String thirdWord) {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord() {
        return commandWord;
    }
    
    /**
     * Return the second word of this command. Returns null if there was
     * no second word.
     * @return The second word.
     */
    public String getSecondWord() {
        return secondWord;
    }
    
    /**
     * Return the third word of this command. Returns null if there was
     * no third word.
     * @return The third word.
     */
    public String getThirdWord() {
        return thirdWord;
    }
    
    /**
     * Return whether or not this command was understood.
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return (commandWord == null);
    }
    
    /**
     * Return whether or not the command has a second word.
     * @return true if this command has a second word
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
    
    /**
     * Return whether or not the command has a third word.
     * @return true if this command has a third word
     */
    public boolean hasThirdWord() {
        return (thirdWord != null);
    }
}