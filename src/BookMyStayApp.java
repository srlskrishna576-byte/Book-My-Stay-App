import java.util.*;

/* -------- Reservation Class -------- */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/* -------- Inventory Service -------- */
class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 3);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory: " + inventory);
    }
}


/* -------- Booking Service -------- */
class BookingService {

    private InventoryService inventoryService;

    // Map room type -> allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    // Set to guarantee global uniqueness of room IDs
    private Set<String> usedRoomIds;

    private int roomCounter = 1;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        allocatedRooms = new HashMap<>();
        usedRoomIds = new HashSet<>();
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        String id;
        do {
            id = roomType.substring(0, 2).toUpperCase() + roomCounter++;
        } while (usedRoomIds.contains(id));

        usedRoomIds.add(id);
        return id;
    }

    // Process booking requests
    public void processBookings(Queue<Reservation> queue) {

        System.out.println("\n===== PROCESSING BOOKINGS =====");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();
            String roomType = request.getRoomType();

            if (inventoryService.getAvailability(roomType) > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                // Update inventory immediately
                inventoryService.decreaseRoom(roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println("-----------------------------");

            } else {
                System.out.println("No rooms available for " + roomType +
                        " (Guest: " + request.getGuestName() + ")");
            }
        }
    }

    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms: " + allocatedRooms);
    }
}

public class BookMyStayApp{
    public static void main(String[] args) {
        // Create booking queue
        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.add(new Reservation("Alice", "Single Room"));
        requestQueue.add(new Reservation("Bob", "Double Room"));
        requestQueue.add(new Reservation("Charlie", "Suite Room"));
        requestQueue.add(new Reservation("David", "Single Room"));

        // Initialize services
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        // Process queue
        bookingService.processBookings(requestQueue);

        // Display final state
        bookingService.displayAllocatedRooms();
        inventory.displayInventory();
    }

}
