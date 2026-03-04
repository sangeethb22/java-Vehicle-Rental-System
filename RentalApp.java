
import java.util.*;
import java.io.*;
public class RentalApp {
    public static ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private static double totalRentalIncome = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "vehicles.txt";

    public static void main(String[] args){
        loadFromFile();
        boolean running = true;

        while (running){
            System.out.println("\n--- Vehicle Rental Management System ---");
            System.out.println("1. Add a Vehicle\n2. View All Vehicles\n3. Rent a Vehicle\n4. Return a Vehicle\n5. Search Vehicle by ID\n6. View Total Rental Income\n7. Exit");
            System.out.print("Select an option: ");

            try{
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1 : addVehicle(); break;
                    case 2 : viewAllVehicles(); break;
                    case 3 : rentVehicle(); break;
                    case 4 : returnVehicle(); break;
                    case 5 : searchVehicle(); break;
                    case 6 : System.out.println("Total Income : $" +  totalRentalIncome); break;
                    case 7 : saveToFile(); running = false; break;
                    default : System.out.println("Invalid choice. Please enter a numeric value.");

                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");

            }
        }
    }
    private static void addVehicle(){
        try {
            System.out.println("Type (1: Car, 2: Bike, 3: van):");
            int type = Integer.parseInt(scanner.nextLine());
            System.out.println("ID :"); String id = scanner.nextLine();
            System.out.println("Brand :"); String brand = scanner.nextLine();
            System.out.println("Model :"); String model = scanner.nextLine();
            System.out.println("Base Rate :"); double rate = Double.parseDouble(scanner.nextLine());

            if (type == 1) {
                System.out.println("Seats:");
                int seats = Integer.parseInt(scanner.nextLine());
                vehicleList.add(new Car(id, brand, model, rate, seats));
            } else if (type == 2) {
                System.out.println("CC:");
                int cc = Integer.parseInt(scanner.nextLine());
                vehicleList.add(new Car(id, brand, model, rate, cc));
            } else if (type == 3) {
                System.out.println("Cargo Kg:");
                double kg = Integer.parseInt(scanner.nextLine());
                vehicleList.add(new Van(id, brand, model, rate, kg));
            }
            System.out.println("Vehicle Added Successfully!");


        } catch (Exception e) {
            System.out.println("Error adding Vehicle. Check your Number");
        }
    }
    private static void rentVehicle(){
        System.out.println("Enter Vehicle ID:");
        String id = scanner.nextLine();
        for (Vehicle v : vehicleList){
            if (v.getVehicleId().equals(id) && v.getIsAvailable()){
                System.out.println("How many Days:");
                int days = Integer.parseInt(scanner.nextLine());
                double cost = v.calculateRentalCost(days);
                v.setAvailable(false);
                totalRentalIncome += cost;
                System.out.println("Rented vehicle successfully... Your cost : $" +  cost);
                return;
            }
        }
        System.out.println("Not Available");
    }
    private static void returnVehicle(){
        System.out.println("Enter Vehicle ID:");
        String id = scanner.nextLine();
        for (Vehicle v : vehicleList){
            if (v.getVehicleId().equalsIgnoreCase(id)){
                v.setAvailable(true);
                System.out.println("Returned");
                return;
            }
        }
    }
    private static void viewAllVehicles(){
       if (vehicleList.isEmpty()){
           System.out.println("There is no Vehicles in the system");
       } else {
           for (Vehicle v : vehicleList){
               v.displayDetails();
           }
       }
    }
    private static void searchVehicle() {
        System.out.println("Enter ID to Search:");
        String id = scanner.nextLine();
        for (Vehicle v : vehicleList){
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                v.displayDetails();
                return;
            }
        }
        System.out.println("Vehicle not found");
    }
    private static void saveToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("vehicle.txt"))) {
            writer.println(totalRentalIncome);

            for (Vehicle v :  vehicleList ){
                String type = "" ;
                String extraInfo = "";
                 if (v instanceof Car){
                     type = "Car";
                     extraInfo = String.valueOf(((Car) v).getNumberOfSeats());
                 } else if ( v instanceof Bike ) {
                     type = "Bike";
                     extraInfo = String.valueOf(((Bike) v).getEngineCapacity());

                 } else if (v instanceof Van) {
                     type = "Van";
                     extraInfo = String.valueOf(((Van) v).getCargoCapacityKg());

                 }
                 writer.println(type +"," +v.getVehicleId() + "," + v.getBrand() +","+ v.getModel() + "," + v.getBasePerDay() + "," + v.getIsAvailable() + "," +  extraInfo);
            }
            System.out.println(" Data Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error saving to file :" + e.getMessage());

        }
    }

    private static void loadFromFile(){
        vehicleList.clear();
        File file = new File("vehicle.txt");
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)){
            if(fileScanner.hasNextLine())totalRentalIncome = Double.parseDouble(fileScanner.nextLine());
            while (fileScanner.hasNextLine()) {
                String[] p = fileScanner.nextLine().split(",");
                String type = p[0], id = p[1], brand = p[2], model= p[3];
                double rate = Double.parseDouble(p[4]);
                boolean avail = Boolean.parseBoolean(p[5]);
                Vehicle v = null;
                if (type.equals("Car")) v = new Car(id, brand, model, rate, Integer.parseInt(p[6]));
                else if (type.equals("Bike")) v = new Bike(id, brand, model, rate, Integer.parseInt(p[6]));
                else if (type.equals("Van")) v = new Van(id, brand, model, rate, Double.parseDouble(p[6]));
                if (v != null) {v.setAvailable(avail); vehicleList.add(v);}
            }


        }catch (Exception e) { System.out.println("Error loading file :"); }

    }

}

