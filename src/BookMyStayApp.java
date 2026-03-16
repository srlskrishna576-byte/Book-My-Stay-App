import java.util.HashMap;
import java.util.Map;

/* -------- Abstract Room Class -------- */
abstract class Room {

    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}


/* -------- Concrete Room Classes -------- */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 100);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Amenities: 1 Bed, Free WiFi");
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 180);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Amenities: 2 Beds, Free WiFi, TV");
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 350);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Amenities: Luxury Suite, Living Area, Premium Services");
    }
}


/* -------- Inventory Class (State Holder) -------- */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }

    // Read-only access method
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to retrieve full inventory (read-only usage)
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}


/* -------- Search Service Class -------- */
class SearchService {

    public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {

        System.out.println("===== AVAILABLE ROOMS =====");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getType());

            // Validation: only display rooms with availability > 0
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("-----------------------------");
            }
        }
    }
}


public class BookMyStayApp{
    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room domain objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Search service
        SearchService search = new SearchService();

        // Guest performs search
        search.searchAvailableRooms(inventory, rooms);

        System.out.println("Search completed. Inventory state unchanged.");
    }

}
