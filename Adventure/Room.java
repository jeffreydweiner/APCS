import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class Room - a room in an adventure game.
 * 
 * This class is part of the "Adventure" application. Adventure is a text based
 * adventure game.
 * 
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. For each existing exit, the room stores a reference
 * to the neighboring room. Rooms can contain any number of characters or items.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private boolean locked;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open courtyard".
     * @param description The room's description.
     * @param items The items in the room
     * @param characters The characters in the room
     * @param locked true if the room is locked, false if otherwise
     */
    public Room(String description, Item[] items, Character[] characters, boolean locked) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.items = new ArrayList<Item>(Arrays.asList(items));
        this.characters = new ArrayList<Character>(Arrays.asList(characters));
        this.locked = locked;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room.
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *      You are in the kitchen.
     *      Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Remove an item from a room.
     * @param item the item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Add an item to a room.
     * @param item the item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Return the items in a room
     * @return the items in a room
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Print out a description of the items in the room.
     */
    public void printItemDescription() {
        if (items != null && !items.isEmpty()) {
            System.out.println("The items in this room are: ");
            for (Item item : items) {
                System.out.print(item.getName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("There are no items here.");
        }
    }

    /**
     * Remove a character from a room.
     * @param character the character to be removed
     */
    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    /**
     * Add a character to a room.
     * @param character the character to be added
     */
    public void addCharacter(Character character) {
        characters.add(character);
    }

    /**
     * Return the characters in a room
     * @return the characters in a room
     */
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * Print out a description of the characters in the room.
     */
    public void printCharacterDescription() {
        if (characters != null && !characters.isEmpty()) {
            System.out.println("The characters in this room are: ");
            for (Character character : characters) {
                System.out.println(character.getName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("There are no characters here.");
        }
    }
    
    /**
     * Toggle locked
     */
    public void toggleLock() {
        locked = !locked;
    }
}