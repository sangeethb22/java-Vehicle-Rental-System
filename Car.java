 class Car extends Vehicle {
    private int numberOfSeats;
    public Car(String id, String brand, String model, double rate, int seats){
        super(id, brand, model , rate, true );
        this.numberOfSeats = seats;
    }
    public int getNumberOfSeats() { return numberOfSeats; }

    @Override
     public double calculateRentalCost(int days){
        return (getBasePerDay() * days) + (numberOfSeats * 200 * days);

    }
}
