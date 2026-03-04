class Van extends Vehicle {
    private double cargoCapacityKg;
    public Van(String id, String brand, String model, double rate, double kg) {
        super(id, brand, model , rate , true );
        this.cargoCapacityKg = kg;
    }
    public double getCargoCapacityKg() { return cargoCapacityKg; }
    @Override
    public double calculateRentalCost(int days){
        return (getBasePerDay() * days) + (cargoCapacityKg * 0.2 * days);

    }
}
