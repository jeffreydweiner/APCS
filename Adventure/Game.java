import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * This class is the main class of the "Adventure" application. "Adventure"
 * is a text-based adventure game based on the game "World of Zuul" by 
 * Michael Kolling and David J. Barnes. 
 * 
 * You are at Horace Mann School in Bronx, NY. You have to find the missing
 * parts of a robot. To find them, you have to talk to the former robotics
 * team captains Teddy Reiss and Jay Fleischer as well as the computer
 * science teachers Ms. Smith and Mr. Torres. If you bring the missing
 * parts back to the robotics lab before 150 turns have passed, the robot
 * is successfully completed before the upcoming competition. Otherwise,
 * the Basement Lions are unable to participate! :( Watch out for Dr.
 * Palfrey's transporter room in the physics lab!
 * 
 * This main class creates and initializes all the others: it creates all
 * rooms, items, and characters, creates the parser, and starts the game.
 * It also evaluates and executes the commands that the parser returns.
 * 
 * @author Jeffrey Weiner and Nikhil Rajan
 * @version 2.0
 */
public class Game {
    private Parser parser;
    private Room previousRoom, currentRoom;
    private ArrayList<Item> inventory;
    private Room[] rooms;
    private int numTurns;

    /**
     * Create the game and initialize its internal map
     */
    public Game() {
        create();
        inventory = new ArrayList<Item>();
        parser = new Parser();
    }

    /**
     * Create all the items, characters, and rooms with their exits linked together
     */
    private void create() {
        Item keys, notebook, chassis, lifter, motor, grass, desk;
        // create the items
        keys = new Item("keys", 1, true);
        chassis = new Item("chassis", 33, true);
        lifter = new Item("lifter", 33, true);
        motor = new Item("motor", 33, true);
        grass = new Item("grass", 101, false);
        desk = new Item("desk", 101, false);
        notebook = new Item("notebook", 10, true);
        Character smith, torres, teddy, jay;
        // create the characters
        smith = new Character("Ms.Smith", 
            new Item[]{notebook}, 
            "Find the robot parts before entering. Make sure to document your journey!");
        torres = new Character("Mr.Torres", 
            new Item[]{motor, keys}, 
            "I am holding the motor. I just bought a new one!");
        teddy = new Character("Teddy", 
            new Item[]{lifter}, 
            "I am holding the lifter. I was making some last-minute adjustments!");
        jay = new Character("Jay", 
            new Item[]{chassis}, 
            "I am holding the chassis. I was trying to change the design during physics class!");
        Room field, office, library, lowerGym, upperGym, cafeteria, recitalHall, physicsLab, roboLab, transporter;
        // create the rooms
        field = new Room("on the main field", 
            new Item[]{grass}, 
            new Character[]{}, 
            false);
        roboLab = new Room("in front of the robotics lab", 
            new Item[]{}, 
            new Character[]{smith}, 
            true);
        office = new Room("in the main office", 
            new Item[]{desk}, 
            new Character[]{}, 
            false);
        library = new Room("in Katz Library", 
            new Item[]{}, 
            new Character[]{teddy}, 
            false);
        lowerGym = new Room("in the lower gym", 
            new Item[]{}, 
            new Character[]{}, 
            false);
        upperGym = new Room("in the upper gym", 
            new Item[]{}, 
            new Character[]{torres}, 
            false);
        cafeteria = new Room("in the Cohen Dining Commons", 
            new Item[]{}, 
            new Character[]{}, 
            false);
        recitalHall = new Room("in the Recital Hall", 
            new Item[]{},
            new Character[]{}, 
            false);
        physicsLab = new Room("in the physics lab", 
            new Item[]{}, 
            new Character[]{jay}, 
            false);
        transporter = new Room("in Dr. Palfrey's transporter room! You hear a wooosh!", 
            new Item[]{}, 
            new Character[]{}, 
            false);
        rooms = new Room[] {field, roboLab, office, library, lowerGym, upperGym, cafeteria, recitalHall, physicsLab, transporter};
        // initialize room exits
        field.setExit("north", lowerGym);
        lowerGym.setExit("up", upperGym);
        lowerGym.setExit("out", field);
        upperGym.setExit("down", lowerGym);
        field.setExit("northeast", cafeteria);
        cafeteria.setExit("down", recitalHall);
        cafeteria.setExit("out", field);
        recitalHall.setExit("up", cafeteria);
        field.setExit("south", roboLab);
        roboLab.setExit("up", office);
        roboLab.setExit("out", field);
        office.setExit("up", library);
        office.setExit("down", roboLab);
        library.setExit("down", office);
        field.setExit("east", physicsLab);
        physicsLab.setExit("down", transporter);
        physicsLab.setExit("out", field);
        transporter.setExit("There are no exits here!", transporter);
        // initialize previousRoom and currentRoom
        previousRoom = null;
        currentRoom = field; // start game outside
    }

    /**
     * Transports the player to a random room upon entering the transporter room.
     */
    private void transport() {
        Random randGen = new Random();
        int randomIndex = randGen.nextInt(rooms.length);
        currentRoom = rooms[randomIndex];
        System.out.println(currentRoom.getLongDescription());
        updateTurns();
    }

    /**
     * Moves a character from one room to another.
     * @param character the character that is moving
     * @param currentRoom the room the character is currently in
     * @param newRoom the room the character is moving to
     */
    private void move(Character character, Room currentRoom, Room newRoom) {
        newRoom.addCharacter(character);
        currentRoom.removeCharacter(character);
    }

    /**
     * Prints the win message
     */
    private void printWinMessage() {
        System.out.println("You have won the game! The Basement Lions will be able to");
        System.out.println("attend the competition!");
    }

    /**
     * Prints the lose message
     */
    private void printLoseMessage() {
        System.out.println("You have lost the game :(. The Basement Lions missed");
        System.out.println("the competition.");
    }

    /**
     * Returns whether or not the robot parts are in the player's inventory
     * @return true if the player has the parts, false otherwise
     */
    private boolean hasRoboParts() {
        return (itemFromName("chassis", inventory) != null 
            && itemFromName("lifter", inventory) != null 
            && itemFromName("motor", inventory) != null);
    }

    /**
     * Returns whether or not the win condition is met
     * The win condition is met if the player has the robo parts,
     * is in the robolab, and has the keys
     * @return true if the player meets the win condition, false otherwise
     */
    private boolean meetsWinCondition() {
        return (hasRoboParts() 
            && itemFromName("keys", inventory) != null 
            && currentRoom == rooms[1]);
    }

    /**
     * Moves a character from one room to another
     * @param character the character that is to be moved
     * @param currentRoom the room the character is currently in
     * @param newRoom the room the character is moving to
     */
    private void moveCharacter(Character character, Room currentRoom, Room newRoom) {
        newRoom.addCharacter(character);
        currentRoom.removeCharacter(character);
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();
        // Enter the main command loop. Here we repeatedly read commands
        // and execute them until the game is over.
        boolean finished = false;
        boolean hasParts = false;
        while (!finished) {
            if (currentRoom == rooms[9]) {            
                transport();
            } else if (hasRoboParts() && currentRoom == rooms[1] && !hasParts) {
                Character smith = characterFromName("Ms.Smith", currentRoom.getCharacters());
                smith.setMessage("Before coming in, you need to get the keys from Mr.Torres."); 
                Character torres = characterFromName("Mr.Torres", rooms[5].getCharacters());
                torres.setMessage("I have the keys with me.");
                moveCharacter(torres, rooms[5], rooms[7]);
                hasParts = true;
            } else if (meetsWinCondition()) {
                if (numTurns < 151) {
                    printWinMessage();
                    finished = true;
                } else {
                    printLoseMessage();
                    finished = true;
                }
            } else {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("============================================");
        System.out.println("************Welcome to Adventure!***********");
        System.out.println("You are at Horace Mann School in Bronx, NY.");
        System.out.println("You have to find the missing parts of a");
        System.out.println("robot. To find them, you have to talk to");
        System.out.println("the former robotics team captains Teddy");
        System.out.println("Reiss and Jay Fleischer as well as the");
        System.out.println("computer science teachers Ms. Smith and ");
        System.out.println("Mr. Torres. If you bring the missing");
        System.out.println("parts back to the robotics lab before 150");
        System.out.println("turns have passed, the robot is successfully");
        System.out.println("completed before the upcoming competition.");
        System.out.println("Otherwise, the Basement Lions are unable to");
        System.out.println("participate! :( Watch out for Dr. Palfrey's");
        System.out.println("transporter room in the physics lab!");
        System.out.println("============================================");
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Adjust turns and print out current number of turns that has passed
     */
    private void updateTurns() {
        ++numTurns;
        System.out.println(numTurns + " turns have passed.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
            updateTurns();
        } else if (commandWord.equals("back")) {
            goBackRoom(command);
            updateTurns();
        } else if (commandWord.equals("inventory")) {
            printInventory();
        } else if (commandWord.equals("search")) {
            search();
        } else if (commandWord.equals("talk")) {
            talk(command);
        } else if (commandWord.equals("take")) {
            take(command);
        } else if (commandWord.equals("drop")) {
            drop(command);
        } else if (commandWord.equals("give")) {
            give(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print the welcome message again and a list of commands.
     */
    private void printHelp() {
        printWelcome();
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command the command message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Go to the previous room.
     * @param command the command message.
     */
    private void goBackRoom(Command command) {
        if (previousRoom == null) {
            System.out.println("You have not gone anywhere yet!");
        } else if (previousRoom == currentRoom) {
            System.out.println("You cannot go back more than once in a row!");
        } else {
            currentRoom = previousRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Print the names and weights of the items the player is currently holding
     */
    private void printInventory() {
        System.out.println("You are holding: ");
        if (inventory != null && !inventory.isEmpty()) {
            System.out.println("==============================");
            for (Item item : inventory) {
                System.out.println(item.getName() + ":" + item.getWeight() + ", ");
            }
            System.out.println("==============================");
        } else {
            System.out.println("nothing");
        }
    }

    /**
     * Print out a description of the items and characters in the current room.
     */
    private void search() {
        currentRoom.printItemDescription();
        currentRoom.printCharacterDescription();
    }

    /**
     * Talk to a character (view the characters message)
     * @param command the command message
     */
    private void talk(Command command) {
        if (currentRoom.getCharacters().isEmpty()) {
            System.out.println("There is no one to talk to here.");
        } else if (!command.hasSecondWord()) {
            System.out.println("Talk to whom?");
        } else {
            Character character = characterFromName(command.getSecondWord(), currentRoom.getCharacters());
            if (character == null) {
                System.out.println("That character is not in this room!");
            } else {
                System.out.println(character.getMessage());
            }
        }
    }

    /**
     * return an item given its name
     * @param name the item's name
     * @param items the list of items
     * @return the item of which the name was given, null if the item is not found
     */
    private Item itemFromName(String name, ArrayList<Item> items) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * return the total weight of the player's inventory
     * @return total weight
     */
    private int inventoryWeight() {
        if (inventory.size() == 0) {
            return 0;
        } else {
            int total = 0;
            for (Item item : inventory) {
                total += item.getWeight();
            }
            return total;
        }
    }

    /**
     * return a character given its name
     * @param name the character's name
     * @param characters the list of characters
     * @return the character whose name was given, null if the character is not found
     */
    private Character characterFromName(String name, ArrayList<Character> characters) {
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Add an item from a room to the player's inventory.
     * @param command the command message.
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what from whom/what?");
            return;
        } else if (!command.hasThirdWord()) {
            System.out.println("Take this item from whom/what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = null;
        boolean fromRoom = true;
        Character character = null;
        if (command.getThirdWord().equals("room")) {
            item = itemFromName(itemName, currentRoom.getItems());
        } else {
            String characterName = command.getThirdWord();
            character = characterFromName(characterName, currentRoom.getCharacters());
            if (character == null) {
                System.out.println("That is not a valid character name. To take an");
                System.out.println("item from this room write take <item> room. To"); 
                System.out.println("take an item from a character in this room write");
                System.out.println("take <item> <character's name>.");
                return;
            } else {
                item = itemFromName(itemName, character.getItems());
                fromRoom = false;
            }
        } 
        if (item == null) {
            System.out.println("You can't take that!");
        } else if (!item.getMovable()) {
            System.out.println("You can't move that item...");
        } else if (inventoryWeight() + item.getWeight() > 100) {
            System.out.println("Picking up that item would exceed your strength!");
            System.out.println("Try dropping other item(s)...");
        } else {
            inventory.add(item);
            if (fromRoom) {
                currentRoom.removeItem(item);
            } else {
                character.removeItem(item);
            }
            System.out.println("You have successfully taken " + itemName);
        }
    }

    /**
     * Transfer an item from the player's inventory to the current room.
     * @param command the command message.
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String name = command.getSecondWord();
        Item item = itemFromName(name, inventory);
        if (item == null) {
            System.out.println("You can't drop that!");
        } else {
            currentRoom.addItem(item);
            inventory.remove(item);
            System.out.println("You have successfully dropped " + name);
        }
    }

    /**
     * Transfer an item from the player to a character.
     * @param command the command message.
     */
    private void give(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Give what to whom?");
            return;
        } else if (!command.hasThirdWord()) {
            System.out.println("Give this item to whom?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = itemFromName(itemName, inventory);
        String characterName = command.getThirdWord();
        Character character = characterFromName(characterName, 
                currentRoom.getCharacters());
        if (item == null) {
            System.out.println("You can't give that!");
        } else if (character == null) {
            System.out.println("That character does not exist!");
        } else {
            character.addItem(item);
            inventory.remove(item);
            System.out.println("You have successfully given "
                + itemName + " to " + characterName);
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether
     * we really quit the game.
     * @param command the command message.
     * @return true if this command quits the game, false otherwise
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}