class Bike extends Vehicle {
    private int engineCapacityCC;
    public Bike(String id, String brand, String model, double rate, int cc){
        super( id, brand, model , rate, true );
        this.engineCapacityCC = cc;
    }
    public int getEngineCapacity(){ return engineCapacityCC; }
    @Override
    public double calculateRentalCost(int days){
        return (getBasePerDay() * days) + (engineCapacityCC * 0.5 * days);

    }
}
