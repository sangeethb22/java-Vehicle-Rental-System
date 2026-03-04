import java.io.Serializable;

public abstract class Vehicle {
    //Private attributes (Encapsulation)
    private String vehicleId;
    private String brand;
    private String model;
    private double basePerDay;
    private boolean isAvailable;

    //call the constructor
    public Vehicle(String vehicleId, String brand, String model, double basePerDay, boolean isAvailable){
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model =model;
        this.basePerDay = basePerDay;
        this.isAvailable = isAvailable;
    }
    //Getter s and settters
    public String getVehicleId() {return vehicleId; }
    public String getBrand() {return brand; }
    public String getModel() {return model; }
    public double getBasePerDay() {return basePerDay; }
    public boolean getIsAvailable() {return isAvailable; }
    public void setAvailable(boolean available) {isAvailable = available; }
    // Display basic details
    public void displayDetails() {
        System.out.println("ID:" + vehicleId + "| Brand:" + brand + " | Model:" + model + " | Rate: $" + basePerDay + " | Available:" + isAvailable);
    }

    //Abstract  method to be implemented by
    public abstract double calculateRentalCost(int days);

}


