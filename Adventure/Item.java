/**
 * Class Item - an item in an adventure game.
 * 
 * This class is part of the "Adventure" application. "Adventure" is a text based
 * adventure game.
 * 
 * An "Item" represents one object that may or may not be movable by the player. 
 * It is stored in the player's inventory, a character's inventory, or in a room.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class Item {
    String name;
    int weight;
    boolean movable;

    /**
     * Create a new Item with a given name and weight. Items are either movable or
     * not movable.
     * @param name The name of the item.
     * @param weight The weight of the item.
     * @param movable Whether or not the tiem is movable.
     */
    public Item (String name, int weight, boolean movable) {
        this.name = name;
        this.weight = weight;
        this.movable = movable;
    }

    /**
     * Returns the name of an item.
     * @return The name.
     */
    public String getName() {
        return name; 
    }
    
    /**
     * Returns the weight of an item.
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Returns whether or not an item is movable.
     * @return true if the item is movable, false if the item is not movable.
     */
    public boolean getMovable() {
        return movable;
    }
}