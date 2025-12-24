/**
 * TruckVehicle Interface
 * 
 * This interface extends the vehicle contract with truck-specific attributes.
 * It defines methods for managing the cargo capacity and transmission type
 * for truck vehicles in the rental system.
 */
public interface TruckVehicle {
    /**
     * Sets the cargo capacity for the truck
     * 
     * @param capacity The cargo capacity in tons (must be positive)
     * @throws IllegalArgumentException if capacity is less than or equal to 0
     */
    void setCargoCapacity(double capacity);
    
    /**
     * Retrieves the cargo capacity of the truck
     * 
     * @return The cargo capacity in tons
     */
    double getCargoCapacity();
    
    /**
     * Sets the transmission type for the truck
     * 
     * @param transmission The transmission type (manual or automatic)
     * @throws IllegalArgumentException if transmission is not one of the valid options
     */
    void setTransmissionType(String transmission);
    
    /**
     * Retrieves the transmission type of the truck
     * 
     * @return The transmission type (manual or automatic)
     */
    String getTransmissionType();
}

