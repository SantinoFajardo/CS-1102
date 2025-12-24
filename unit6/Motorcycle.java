/**
 * Motorcycle Class
 * 
 * This class represents a motorcycle vehicle in the car rental agency system.
 * It implements both the Vehicle and MotorVehicle interfaces, providing
 * concrete implementations for all required methods. It stores and manages
 * motorcycle-specific information including make, model, year, number of wheels, and motorcycle type.
 */
public class Motorcycle implements Vehicle, MotorVehicle {
    // Private instance variables
    private String make;
    private String model;
    private int year;
    private int numberOfWheels;
    private String motorcycleType;
    
    // Valid motorcycle types
    private static final String[] VALID_MOTORCYCLE_TYPES = {"sport", "cruiser", "off-road"};
    
    /**
     * Constructor to initialize a Motorcycle object
     * 
     * @param make The make (manufacturer) of the motorcycle
     * @param model The model of the motorcycle
     * @param year The year of manufacture
     * @throws IllegalArgumentException if year is invalid (less than 1900 or greater than current year + 1)
     */
    public Motorcycle(String make, String model, int year) {
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
     * Retrieves the make of the motorcycle
     * 
     * @return The make of the motorcycle
     */
    @Override
    public String getMake() {
        return make;
    }
    
    /**
     * Retrieves the model of the motorcycle
     * 
     * @return The model of the motorcycle
     */
    @Override
    public String getModel() {
        return model;
    }
    
    /**
     * Retrieves the year of manufacture of the motorcycle
     * 
     * @return The year of manufacture
     */
    @Override
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the number of wheels for the motorcycle
     * 
     * @param wheels The number of wheels (typically 2 or 3)
     * @throws IllegalArgumentException if wheels is less than 2 or greater than 3
     */
    @Override
    public void setNumberOfWheels(int wheels) {
        if (wheels < 2 || wheels > 3) {
            throw new IllegalArgumentException("Number of wheels must be between 2 and 3");
        }
        this.numberOfWheels = wheels;
    }
    
    /**
     * Retrieves the number of wheels of the motorcycle
     * 
     * @return The number of wheels
     */
    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    
    /**
     * Sets the type of motorcycle
     * 
     * @param type The motorcycle type (sport, cruiser, or off-road)
     * @throws IllegalArgumentException if type is not one of the valid options
     */
    @Override
    public void setMotorcycleType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Motorcycle type cannot be null or empty");
        }
        
        String normalizedType = type.trim().toLowerCase();
        boolean isValid = false;
        for (String validType : VALID_MOTORCYCLE_TYPES) {
            if (validType.equals(normalizedType)) {
                isValid = true;
                break;
            }
        }
        
        if (!isValid) {
            throw new IllegalArgumentException("Motorcycle type must be one of: sport, cruiser, or off-road");
        }
        
        this.motorcycleType = normalizedType;
    }
    
    /**
     * Retrieves the type of motorcycle
     * 
     * @return The motorcycle type (sport, cruiser, or off-road)
     */
    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
    
    /**
     * Returns a string representation of the motorcycle
     * 
     * @return A formatted string with all motorcycle details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Motorcycle Details:\n");
        sb.append("  Make: ").append(make).append("\n");
        sb.append("  Model: ").append(model).append("\n");
        sb.append("  Year: ").append(year).append("\n");
        sb.append("  Number of Wheels: ").append(numberOfWheels).append("\n");
        sb.append("  Motorcycle Type: ").append(motorcycleType != null ? motorcycleType : "Not set").append("\n");
        return sb.toString();
    }
}

