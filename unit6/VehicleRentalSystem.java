import java.util.ArrayList;
import java.util.Scanner;

/**
 * VehicleRentalSystem Class
 * 
 * This is the main program for the Car Rental Agency System.
 * It provides an interactive interface for users to create and manage
 * different types of vehicles (cars, motorcycles, and trucks).
 * The program demonstrates the use of interfaces to enforce common behavior
 * across different vehicle types while allowing each type to have specific attributes.
 */
public class VehicleRentalSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    
    /**
     * Main method - Entry point of the program
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   Welcome to the Car Rental Agency");
        System.out.println("   Vehicle Information System");
        System.out.println("==========================================");
        System.out.println();
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1 -> createCar();
                case 2 -> createMotorcycle();
                case 3 -> createTruck();
                case 4 -> displayAllVehicles();
                case 5 -> {
                    running = false;
                    System.out.println("\nThank you for using the Vehicle Rental System. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a Car");
        System.out.println("2. Add a Motorcycle");
        System.out.println("3. Add a Truck");
        System.out.println("4. Display All Vehicles");
        System.out.println("5. Exit");
        System.out.print("Please select an option (1-5): ");
    }
    
    /**
     * Gets and validates the menu choice from the user
     * 
     * @return The validated menu choice (1-5)
     */
    private static int getMenuChoice() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between 1 and 5: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 5: ");
            }
        }
    }
    
    /**
     * Creates a new Car object with user input
     * Handles errors gracefully and provides user feedback
     */
    private static void createCar() {
        System.out.println("\n--- Add a Car ---");
        
        try {
            System.out.print("Enter make (manufacturer): ");
            String make = scanner.nextLine().trim();
            
            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();
            
            System.out.print("Enter year of manufacture: ");
            int year = getValidInteger();
            
            Car car = new Car(make, model, year);
            
            System.out.print("Enter number of doors (2-5): ");
            int doors = getValidInteger();
            car.setNumberOfDoors(doors);
            
            System.out.print("Enter fuel type (petrol/diesel/electric): ");
            String fuelType = scanner.nextLine().trim();
            car.setFuelType(fuelType);
            
            vehicles.add(car);
            System.out.println("\n✓ Car added successfully!");
            System.out.println(car);
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            System.out.println("Please try again with valid input.");
        } catch (Exception e) {
            System.out.println("\n✗ An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Creates a new Motorcycle object with user input
     * Handles errors gracefully and provides user feedback
     */
    private static void createMotorcycle() {
        System.out.println("\n--- Add a Motorcycle ---");
        
        try {
            System.out.print("Enter make (manufacturer): ");
            String make = scanner.nextLine().trim();
            
            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();
            
            System.out.print("Enter year of manufacture: ");
            int year = getValidInteger();
            
            Motorcycle motorcycle = new Motorcycle(make, model, year);
            
            System.out.print("Enter number of wheels (2-3): ");
            int wheels = getValidInteger();
            motorcycle.setNumberOfWheels(wheels);
            
            System.out.print("Enter motorcycle type (sport/cruiser/off-road): ");
            String type = scanner.nextLine().trim();
            motorcycle.setMotorcycleType(type);
            
            vehicles.add(motorcycle);
            System.out.println("\n✓ Motorcycle added successfully!");
            System.out.println(motorcycle);
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            System.out.println("Please try again with valid input.");
        } catch (Exception e) {
            System.out.println("\n✗ An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Creates a new Truck object with user input
     * Handles errors gracefully and provides user feedback
     */
    private static void createTruck() {
        System.out.println("\n--- Add a Truck ---");
        
        try {
            System.out.print("Enter make (manufacturer): ");
            String make = scanner.nextLine().trim();
            
            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();
            
            System.out.print("Enter year of manufacture: ");
            int year = getValidInteger();
            
            Truck truck = new Truck(make, model, year);
            
            System.out.print("Enter cargo capacity (in tons): ");
            double capacity = getValidDouble();
            truck.setCargoCapacity(capacity);
            
            System.out.print("Enter transmission type (manual/automatic): ");
            String transmission = scanner.nextLine().trim();
            truck.setTransmissionType(transmission);
            
            vehicles.add(truck);
            System.out.println("\n✓ Truck added successfully!");
            System.out.println(truck);
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            System.out.println("Please try again with valid input.");
        } catch (Exception e) {
            System.out.println("\n✗ An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Displays all vehicles in the system
     * Demonstrates polymorphism by treating all vehicles through the Vehicle interface
     */
    private static void displayAllVehicles() {
        System.out.println("\n--- All Vehicles in the System ---");
        
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles have been added yet.");
            return;
        }
        
        System.out.println("Total vehicles: " + vehicles.size());
        System.out.println("==========================================");
        
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.println("\nVehicle #" + (i + 1));
            System.out.println("------------------------------------------");
            
            // Display common vehicle information using the Vehicle interface
            System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Year: " + vehicle.getYear());
            
            // Display specific information based on vehicle type
            if (vehicle instanceof Car car) {
                System.out.println("Type: Car");
                System.out.println("Number of Doors: " + car.getNumberOfDoors());
                System.out.println("Fuel Type: " + car.getFuelType());
            } else if (vehicle instanceof Motorcycle motorcycle) {
                System.out.println("Type: Motorcycle");
                System.out.println("Number of Wheels: " + motorcycle.getNumberOfWheels());
                System.out.println("Motorcycle Type: " + motorcycle.getMotorcycleType());
            } else if (vehicle instanceof Truck truck) {
                System.out.println("Type: Truck");
                System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
                System.out.println("Transmission Type: " + truck.getTransmissionType());
            }
        }
        
        System.out.println("\n==========================================");
    }
    
    /**
     * Gets and validates an integer input from the user
     * 
     * @return A valid integer value
     */
    private static int getValidInteger() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }
    
    /**
     * Gets and validates a double input from the user
     * 
     * @return A valid double value
     */
    private static double getValidDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}

