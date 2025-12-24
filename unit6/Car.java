/**
 * Car Class
 * 
 * This class represents a car vehicle in the car rental agency system.
 * It implements both the Vehicle and CarVehicle interfaces, providing
 * concrete implementations for all required methods. It stores and manages
 * car-specific information including make, model, year, number of doors, and fuel type.
 */
public class Car implements Vehicle, CarVehicle {
    // Private instance variables
    private String make;
    private String model;
    private int year;
    private int numberOfDoors;
    private String fuelType;
    
    // Valid fuel types
    private static final String[] VALID_FUEL_TYPES = {"petrol", "diesel", "electric"};
    
    /**
     * Constructor to initialize a Car object
     * 
     * @param make The make (manufacturer) of the car
     * @param model The model of the car
     * @param year The year of manufacture
     * @throws IllegalArgumentException if year is invalid (less than 1900 or greater than current year + 1)
     */
    public Car(String make, String model, int year) {
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
     * Retrieves the make of the car
     * 
     * @return The make of the car
     */
    @Override
    public String getMake() {
        return make;
    }
    
    /**
     * Retrieves the model of the car
     * 
     * @return The model of the car
     */
    @Override
    public String getModel() {
        return model;
    }
    
    /**
     * Retrieves the year of manufacture of the car
     * 
     * @return The year of manufacture
     */
    @Override
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the number of doors for the car
     * 
     * @param doors The number of doors (typically 2, 4, or 5)
     * @throws IllegalArgumentException if doors is less than 2 or greater than 5
     */
    @Override
    public void setNumberOfDoors(int doors) {
        if (doors < 2 || doors > 5) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 5");
        }
        this.numberOfDoors = doors;
    }
    
    /**
     * Retrieves the number of doors of the car
     * 
     * @return The number of doors
     */
    @Override
    public int getNumberOfDoors() {
        return numberOfDoors;
    }
    
    /**
     * Sets the fuel type for the car
     * 
     * @param fuelType The fuel type (petrol, diesel, or electric)
     * @throws IllegalArgumentException if fuelType is not one of the valid options
     */
    @Override
    public void setFuelType(String fuelType) {
        if (fuelType == null || fuelType.trim().isEmpty()) {
            throw new IllegalArgumentException("Fuel type cannot be null or empty");
        }
        
        String normalizedFuelType = fuelType.trim().toLowerCase();
        boolean isValid = false;
        for (String validType : VALID_FUEL_TYPES) {
            if (validType.equals(normalizedFuelType)) {
                isValid = true;
                break;
            }
        }
        
        if (!isValid) {
            throw new IllegalArgumentException("Fuel type must be one of: petrol, diesel, or electric");
        }
        
        this.fuelType = normalizedFuelType;
    }
    
    /**
     * Retrieves the fuel type of the car
     * 
     * @return The fuel type (petrol, diesel, or electric)
     */
    @Override
    public String getFuelType() {
        return fuelType;
    }
    
    /**
     * Returns a string representation of the car
     * 
     * @return A formatted string with all car details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car Details:\n");
        sb.append("  Make: ").append(make).append("\n");
        sb.append("  Model: ").append(model).append("\n");
        sb.append("  Year: ").append(year).append("\n");
        sb.append("  Number of Doors: ").append(numberOfDoors).append("\n");
        sb.append("  Fuel Type: ").append(fuelType != null ? fuelType : "Not set").append("\n");
        return sb.toString();
    }
}

