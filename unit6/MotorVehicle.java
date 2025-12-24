/**
 * MotorVehicle Interface
 * 
 * This interface extends the vehicle contract with motorcycle-specific attributes.
 * It defines methods for managing the number of wheels and motorcycle type
 * for motorcycle vehicles in the rental system.
 */
public interface MotorVehicle {
    /**
     * Sets the number of wheels for the motorcycle
     * 
     * @param wheels The number of wheels (typically 2 or 3)
     * @throws IllegalArgumentException if wheels is less than 2 or greater than 3
     */
    void setNumberOfWheels(int wheels);
    
    /**
     * Retrieves the number of wheels of the motorcycle
     * 
     * @return The number of wheels
     */
    int getNumberOfWheels();
    
    /**
     * Sets the type of motorcycle
     * 
     * @param type The motorcycle type (sport, cruiser, or off-road)
     * @throws IllegalArgumentException if type is not one of the valid options
     */
    void setMotorcycleType(String type);
    
    /**
     * Retrieves the type of motorcycle
     * 
     * @return The motorcycle type (sport, cruiser, or off-road)
     */
    String getMotorcycleType();
}

