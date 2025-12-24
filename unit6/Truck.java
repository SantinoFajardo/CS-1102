/**
 * Truck Class
 * 
 * This class represents a truck vehicle in the car rental agency system.
 * It implements both the Vehicle and TruckVehicle interfaces, providing
 * concrete implementations for all required methods. It stores and manages
 * truck-specific information including make, model, year, cargo capacity, and transmission type.
 */
public class Truck implements Vehicle, TruckVehicle {
    // Private instance variables
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;
    
    // Valid transmission types
    private static final String[] VALID_TRANSMISSION_TYPES = {"manual", "automatic"};
    
    /**
     * Constructor to initialize a Truck object
     * 
     * @param make The make (manufacturer) of the truck
     * @param model The model of the truck
     * @param year The year of manufacture
     * @throws IllegalArgumentException if year is invalid (less than 1900 or greater than current year + 1)
     */
    public Truck(String make, String model, int year) {
        if (make == null || make.trim().isEmpty()) {
            throw new IllegalArgumentException("Make cannot be null or empty");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (year < 1900 || year > 2025) {
            throw new IllegalArgumentException("Year must be between 1900 and 2025");
        }
        
        this.make = make.trim();
        this.model = model.trim();
        this.year = year;
    }
    
    /**
     * Retrieves the make of the truck
     * 
     * @return The make of the truck
     */
    @Override
    public String getMake() {
        return make;
    }
    
    /**
     * Retrieves the model of the truck
     * 
     * @return The model of the truck
     */
    @Override
    public String getModel() {
        return model;
    }
    
    /**
     * Retrieves the year of manufacture of the truck
     * 
     * @return The year of manufacture
     */
    @Override
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the cargo capacity for the truck
     * 
     * @param capacity The cargo capacity in tons (must be positive)
     * @throws IllegalArgumentException if capacity is less than or equal to 0
     */
    @Override
    public void setCargoCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be greater than 0");
        }
        this.cargoCapacity = capacity;
    }
    
    /**
     * Retrieves the cargo capacity of the truck
     * 
     * @return The cargo capacity in tons
     */
    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }
    
    /**
     * Sets the transmission type for the truck
     * 
     * @param transmission The transmission type (manual or automatic)
     * @throws IllegalArgumentException if transmission is not one of the valid options
     */
    @Override
    public void setTransmissionType(String transmission) {
        if (transmission == null || transmission.trim().isEmpty()) {
            throw new IllegalArgumentException("Transmission type cannot be null or empty");
        }
        
        String normalizedTransmission = transmission.trim().toLowerCase();
        boolean isValid = false;
        for (String validType : VALID_TRANSMISSION_TYPES) {
            if (validType.equals(normalizedTransmission)) {
                isValid = true;
                break;
            }
        }
        
        if (!isValid) {
            throw new IllegalArgumentException("Transmission type must be one of: manual or automatic");
        }
        
        this.transmissionType = normalizedTransmission;
    }
    
    /**
     * Retrieves the transmission type of the truck
     * 
     * @return The transmission type (manual or automatic)
     */
    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
    
    /**
     * Returns a string representation of the truck
     * 
     * @return A formatted string with all truck details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Truck Details:\n");
        sb.append("  Make: ").append(make).append("\n");
        sb.append("  Model: ").append(model).append("\n");
        sb.append("  Year: ").append(year).append("\n");
        sb.append("  Cargo Capacity: ").append(cargoCapacity).append(" tons\n");
        sb.append("  Transmission Type: ").append(transmissionType != null ? transmissionType : "Not set").append("\n");
        return sb.toString();
    }
}

