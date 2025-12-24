/**
 * CarVehicle Interface
 * 
 * This interface extends the vehicle contract with car-specific attributes.
 * It defines methods for managing the number of doors and fuel type
 * for car vehicles in the rental system.
 */
public interface CarVehicle {
    /**
     * Sets the number of doors for the car
     * 
     * @param doors The number of doors (typically 2, 4, or 5)
     * @throws IllegalArgumentException if doors is less than 2 or greater than 5
     */
    void setNumberOfDoors(int doors);
    
    /**
     * Retrieves the number of doors of the car
     * 
     * @return The number of doors
     */
    int getNumberOfDoors();
    
    /**
     * Sets the fuel type for the car
     * 
     * @param fuelType The fuel type (petrol, diesel, or electric)
     * @throws IllegalArgumentException if fuelType is not one of the valid options
     */
    void setFuelType(String fuelType);
    
    /**
     * Retrieves the fuel type of the car
     * 
     * @return The fuel type (petrol, diesel, or electric)
     */
    String getFuelType();
}

