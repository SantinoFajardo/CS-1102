/**
 * Vehicle Interface
 * 
 * This interface defines the contract for all vehicle types in the car rental agency system.
 * It specifies the common methods that all vehicles must implement to retrieve
 * basic vehicle information: make, model, and year of manufacture.
 */
public interface Vehicle {
    /**
     * Retrieves the make (manufacturer) of the vehicle
     * 
     * @return The make of the vehicle
     */
    String getMake();
    
    /**
     * Retrieves the model of the vehicle
     * 
     * @return The model of the vehicle
     */
    String getModel();
    
    /**
     * Retrieves the year of manufacture of the vehicle
     * 
     * @return The year of manufacture
     */
    int getYear();
}

