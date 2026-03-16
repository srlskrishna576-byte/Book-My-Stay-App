import java.util.LinkedList;
import java.util.Queue;

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

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}


/* -------- Booking Request Queue -------- */
class BookingQueue {

    private Queue<Reservation> requestQueue;

    public BookingQueue() {
        requestQueue = new LinkedList<>();
    }

    // Accept booking request
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // Display queued requests
    public void displayQueue() {
        System.out.println("\n===== BOOKING REQUEST QUEUE =====");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}



public class BookMyStayApp{
    public static void main(String[] args) {
        // Initialize booking queue
        BookingQueue queue = new BookingQueue();

        // Guests submit booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Double Room");

        // Add requests to queue
        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);

        // Display queue (arrival order preserved)
        queue.displayQueue();

        System.out.println("\nRequests stored and waiting for processing.");
    }
}
