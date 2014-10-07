import java.util.ArrayList;
import java.util.Arrays;
/**
 * Class Character - a non-player character in an adventure game.
 * 
 * This class is part of the "Adventure" application. "Adventure" is a text based
 * adventure game.
 * 
 * A "character" represents a person that moves around based on the player's 
 * actions. Characters can hold items and have a message that they tell the
 * player when the "talk" command is used.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class Character {
    private String name;
    private ArrayList<Item> items;
    private String message;
    
    /**
     * Create a new Character with a given name.
     * @param name the character's name
     * @param items the items the character carries
     * @param message the message the character tells the player
     */
    public Character (String name, Item[] items, String message) {
        this.name = name;
        this.items = new ArrayList<Item>(Arrays.asList(items));
        this.message = message;
    }
    
    /**
     * Return the name of the character
     * @return the name of the character
     */
    public String getName() {
        return name;
    }
    
    /**
     * Return the items that this character is holding.
     * @return the list of items this character is holding
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * Return the message that this character has
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Remove an item from the character's item list
     * @param item the item to be removed
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * Add an item to the character's item list
     * @param item the item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Set the character's message to something new
     * @param newMessage the new message
     */
    public void setMessage(String newMessage) {
        message = newMessage;
    }
}