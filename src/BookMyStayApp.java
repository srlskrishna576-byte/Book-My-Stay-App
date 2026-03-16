abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    // Constructor
    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq.ft");
        System.out.println("Price     : $" + price);
    }

    // Abstract method
    public abstract void roomInfo();
}


/* -------- Single Room Class -------- */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }

    public void roomInfo() {
        System.out.println("Ideal for one guest.");
    }
}


/* -------- Double Room Class -------- */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }

    public void roomInfo() {
        System.out.println("Suitable for two guests.");
    }
}


/* -------- Suite Room Class -------- */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 350);
    }

    public void roomInfo() {
        System.out.println("Luxury room with premium facilities.");
    }
}

public class BookMyStayApp{
    public static void main(String[] args) {

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("===== HOTEL ROOM AVAILABILITY =====\n");

        // Display Single Room details
        single.displayRoomDetails();
        single.roomInfo();
        System.out.println("Available Rooms: " + singleAvailable);
        System.out.println("-----------------------------------");

        // Display Double Room details
        doubleRoom.displayRoomDetails();
        doubleRoom.roomInfo();
        System.out.println("Available Rooms: " + doubleAvailable);
        System.out.println("-----------------------------------");

        // Display Suite Room details
        suite.displayRoomDetails();
        suite.roomInfo();
        System.out.println("Available Rooms: " + suiteAvailable);
        System.out.println("-----------------------------------");

        System.out.println("Application Terminated.");
    }

}
