import java.io.File;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
    	//TODO
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	DatabaseManager.loadVehicles(VEHICLE_FILE);
    	DatabaseManager.loadPackages(PACKAGE_FILE);
    	DatabaseManager.loadProfit(PROFIT_FILE);
    	DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
    	DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
    	
    	
    	//2) Show menu and handle user inputs
        Scanner scan = new Scanner(System.in);


        System.out.println("==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Activate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================");
        int input;
        input = scan.nextInt();
        if (input < 1 || input > 6) {
            System.out.println("Error: Option not available.");
        }
        String id;
        String product;
        double weight;
        double price;
        String name; //e.g. (Lawson Computer Science Building)
        String address; //e.g. Street Address (305 N University St)
        String city; // e.g. (West Lafayette)
        String state; // e.g. IN
        int zipCode;

        switch (input) {
            case 1:
                System.out.println("Enter Package ID:");
                id = scan.next();
                scan.nextLine();
                System.out.println("Enter Product Name:");
                product = scan.nextLine();
                System.out.println("Enter Weight:");
                weight = scan.nextDouble();
                System.out.println("Enter Price:");
                price = scan.nextDouble();
                scan.nextLine();
                System.out.println("Enter Buyer Name:");
                name = scan.nextLine();
                System.out.println("Enter Address:");
                address = scan.nextLine();
                System.out.println("Enter City:");
                city = scan.nextLine();
                System.out.println("Enter State:");
                state = scan.nextLine();
                System.out.println("Enter ZIP Code:");
                zipCode = scan.nextInt();

                ShippingAddress sA = new ShippingAddress(name, address, city, state, zipCode);
                Package newPackage = new Package(id, product, weight, price, sA);

                System.out.println("\n\n" + newPackage.shippingLabel());
                break;
            case 2:
                String licPlate;
                double maxWeight;
                System.out.println("Vehicle Options:\n" +
                        "1) Truck\n" +
                        "2) Drone\n" +
                        "3) Cargo Plane");
                int option;
                option = scan.nextInt();
                if (option < 1 || option > 3) {
                    System.out.println("Error: Option not available");
                } else {
                    if (option == 1) {
                        scan.nextLine();
                        System.out.println("Enter License Plate No.:");
                        licPlate = scan.nextLine();
                        System.out.println("Enter Maximum Carry Weight:");
                        maxWeight = scan.nextDouble();
                        Truck truck1 = new Truck(licPlate, maxWeight);
                    } else if (option == 2) {
                        scan.nextLine();
                        System.out.println("Enter License Plate No.:");
                        licPlate = scan.nextLine();
                        System.out.println("Enter Maximum Carry Weight:");
                        maxWeight = scan.nextDouble();
                        Drone drone1 = new Drone(licPlate, maxWeight);
                    } else if (option == 3) {
                        scan.nextLine();
                        System.out.println("Enter License Plate No.:");
                        licPlate = scan.nextLine();
                        System.out.println("Enter Maximum Carry Weight:");
                        maxWeight = scan.nextDouble();
                        CargoPlane cargoPlane1 = new CargoPlane(licPlate, maxWeight);
                    }
                }
                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}