import java.io.*;
import java.util.*;

abstract class Booking {
    // Common attributes for any type of booking
    protected String customerName;
    protected String destination;
    protected String date;
    protected int numTravelers;

    public Booking(String customerName, String destination, String date, int numTravelers) {
        this.customerName = customerName;
        this.destination = destination;
        this.date = date;
        this.numTravelers = numTravelers;
    }

    public abstract String getType();

    public String toCSV() {
        return customerName + "," + destination + "," + date + "," + numTravelers + "," + getType();
    }

    public void display() {
        System.out.println("Name: " + customerName);
        System.out.println("Destination: " + destination);
        System.out.println("Date: " + date);
        System.out.println("Number of Travelers: " + numTravelers);
        System.out.println("Booking Type: " + getType());
        System.out.println("-----------------------------");
    }
    
    public static Booking fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return null;

        String name = parts[0];
        String dest = parts[1];
        String date = parts[2];
        int num = Integer.parseInt(parts[3]);
        String type = parts[4];

        if (type.equals("Flight")) {
            Booking booking  = new FlightBooking(name, dest, date, num);
            return booking;
        } else if (type.equals("Hotel")) {
            Booking booking  = new HotelBooking(name, dest, date, num);
            return booking;
        } else if (type.equals("Tour")) {
            Booking booking  = new TourBooking(name, dest, date, num);
            return booking;
        } else {
            return null;
        }
    }
}

class FlightBooking extends Booking {
    public FlightBooking(String customerName, String destination, String date, int numTravelers) {
        super(customerName, destination, date, numTravelers);
    }

    public String getType() {
        return "Flight";
    }
}

class HotelBooking extends Booking {
    public HotelBooking(String customerName, String destination, String date, int numTravelers) {
        super(customerName, destination, date, numTravelers);
    }

    public String getType() {
        return "Hotel";
    }
}

class TourBooking extends Booking {
    public TourBooking(String customerName, String destination, String date, int numTravelers) {
        super(customerName, destination, date, numTravelers);
    }

    public String getType() {
        return "Tour";
    }
}

class BookingManager {
    private ArrayList<Booking> bookings;
    private final String fileName = "bookings.csv";

    public BookingManager() {
        bookings = new ArrayList<>();
        loadFromFile();
    }

     private void loadFromFile() {
        File file = new File(fileName);

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            // skip the header
            line = br.readLine();
            while (line != null) {
                Booking b = Booking.fromCSV(line);
                if (b != null)
                bookings.add(b);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

    public void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking b : bookings) {
            b.display();
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        saveToFile();
        System.out.println("Booking added successfully.");
    }

    private void saveToFile() {
        try {
            FileWriter  fileWriter = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("Name,Destination,Date,numTravelers,Type");
            bw.newLine();
            for (Booking b : bookings) {
                bw.write(b.toCSV());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}

class Codechef {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingManager manager = new BookingManager();

        while (true) {
            System.out.println("\n=== Travel Booking Menu ===");
            System.out.println("1. Create Booking");
            System.out.println("2. Show All Bookings");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter customer name: ");
                String name = sc.nextLine();

                System.out.print("Enter destination: ");
                String destination = sc.nextLine();

                System.out.print("Enter travel date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                System.out.print("Enter number of travelers: ");
                int numTravelers = Integer.parseInt(sc.nextLine());

                System.out.println("Select Booking Type:");
                System.out.println("1. Flight");
                System.out.println("2. Hotel");
                System.out.println("3. Tour");
                int type = Integer.parseInt(sc.nextLine());

                Booking booking = null;
                if (type == 1) {    
                    booking = new FlightBooking(name, destination, date, numTravelers);
                } else if (type == 2) {
                    booking = new HotelBooking(name, destination, date, numTravelers);
                } else if (type == 3) {
                    booking = new TourBooking(name, destination, date, numTravelers);
                } else {
                    System.out.println("Invalid booking type.");
                    continue;
                }

                manager.addBooking(booking);

            } else if (choice == 2) {
                manager.showBookings();
            } else if (choice == 3) {
                System.out.println("Thank you for using the Travel Booking System!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}