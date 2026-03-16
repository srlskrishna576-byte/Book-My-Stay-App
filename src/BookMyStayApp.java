import java.util.HashMap;
import java.util.Map;

/* -------- Inventory Management Class -------- */
class RoomInventory {

    // HashMap to store room type and availability
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with available counts
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Method to get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update room availability
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found in inventory.");
        }
    }

    // Method to display entire inventory
    public void displayInventory() {
        System.out.println("===== CURRENT ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}
public class BookMyStayApp{
    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability for Double Room...");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        // Update availability
        System.out.println("\nUpdating Double Room availability...");
        inventory.updateAvailability("Double Room", 4);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }


}
