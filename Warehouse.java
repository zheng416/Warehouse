import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 *
 *
 * @author Wenxi Zhang & Jacky Zheng
 *
 * @version 2018-12-04
 */

public class Warehouse {
	final static String FOLDER_PATH = "files/";
    final static File VEHICLE_FILE = new File(FOLDER_PATH + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(FOLDER_PATH + "PackageList.csv");
    final static File PROFIT_FILE = new File(FOLDER_PATH + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(FOLDER_PATH + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(FOLDER_PATH + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {


        ArrayList<Package> packages;
        ArrayList<Vehicle> vehicles;
        double profit;
        int nPackages;
        boolean prime;
        int primeDay;


    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
    	packages = DatabaseManager.loadPackages(PACKAGE_FILE);
    	profit = DatabaseManager.loadProfit(PROFIT_FILE);
    	nPackages = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
    	prime = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
    	profit = 0;

    	int packagesWarehouse = 0;
    	if (prime) {
    	    primeDay = 1;
        } else {
    	    primeDay = 0;
        }

        boolean space = false;
    	boolean space2 = false;

    	
    	
    	//2) Show menu and handle user inputs



        Scanner scan = new Scanner(System.in);
        String mainMenu0 = "==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Activate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================";
        String mainMenu1 = "==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Deactivate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================";


        String menu = mainMenu0;


        boolean flag = true;
        while (flag) {
            System.out.println(menu);
            if (space) {
                System.out.println();
                space = false;
            }
            if (space2) {
                System.out.println();
                space2 = false;
            }
            int input;
            input = scan.nextInt();
            if (input < 1 || input > 6) {
                System.out.println("Error: Option not available.\n");
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
                    System.out.println("\nEnter Package ID:");
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
                    address = scan.nextLine() + " ";
                    System.out.println("Enter City:");
                    city = scan.nextLine();
                    System.out.println("Enter State:");
                    state = scan.nextLine();
                    System.out.println("Enter ZIP Code:");
                    zipCode = scan.nextInt();

                    //If prime, apply 15% dis, otherwise regular price.
                    String priceDiscounted = Double.toString(price);
                    if (primeDay == 1) {
                        price = price * 0.85;
                        priceDiscounted = String.format("%.2f", price);
                    }


                    ShippingAddress sA = new ShippingAddress(name, address, city, state, zipCode);
                    Package newPackage = new Package(id, product, weight, Double.parseDouble(priceDiscounted), sA);
                    packages.add(newPackage);
                    packagesWarehouse++;

                    System.out.println("\n" + newPackage.shippingLabel());
                    space = true;
//                    space2 = true;
                    break;
                case 2:
                    String licPlate;
                    double maxWeight;
                    boolean flag2 = true;
                    while (flag2) {
                        System.out.println("\nVehicle Options:\n" +
                                "1) Truck\n" +
                                "2) Drone\n" +
                                "3) Cargo Plane");
                        int option;
                        option = scan.nextInt();
                        if (option < 1 || option > 3) {
                            System.out.println("Error: Option not available.");
                        } else {
                            if (option == 1) {
                                scan.nextLine();
                                System.out.println("Enter License Plate No.:");
                                licPlate = scan.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                maxWeight = scan.nextDouble();
                                Truck truck = new Truck(licPlate, maxWeight);
                                vehicles.add(truck);
                                flag2 = false;
                            } else if (option == 2) {
                                scan.nextLine();
                                System.out.println("Enter License Plate No.:");
                                licPlate = scan.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                maxWeight = scan.nextDouble();
                                Drone drone = new Drone(licPlate, maxWeight);
                                vehicles.add(drone);
                                flag2 = false;
                            } else if (option == 3) {
                                scan.nextLine();
                                System.out.println("Enter License Plate No.:");
                                licPlate = scan.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                maxWeight = scan.nextDouble();
                                CargoPlane cargoPlane = new CargoPlane(licPlate, maxWeight);
                                vehicles.add(cargoPlane);
                                flag2 = false;
                            }
                        }
                    }
                    break;
                case 3:
                    if (!prime) {
                        menu = mainMenu1;
                        primeDay = 1;
                        prime = true;
                        for (int i = 0; i < packages.size() ; i++) {
                            String iPrice;
                            Double iPriced = packages.get(i).getPrice() * 0.85;
                            iPrice = String.format("%.2f", iPriced);
                            packages.get(i).setPrice(Double.parseDouble(iPrice));
                        }
                    } else {
                        menu = mainMenu0;
                        primeDay = 0;
                        prime = false;

                    }
                    System.out.println();
                    break;
                case 4:
                    //No Vehicles Available
                    if (vehicles.size() == 0) {
                        if (packages.size() == 0) {
                            System.out.println("Error: No packages available.\n");
                        } else {
                            System.out.println("Error: No vehicles available.\n");
                        }
                    } else { //Vehicles Available
                        boolean flag3 = true;
                        int input3;
                        while (flag3) {
                            System.out.println("\nOptions:\n" +
                                    "1) Send Truck\n" +
                                    "2) Send Drone\n" +
                                    "3) Send Cargo Plane\n" +
                                    "4) Send First Available");
                            input3 = scan.nextInt();
                            //Option DNE
                            if (input3 < 1 || input3 > 4) {
                                System.out.println("Error: Option not available.");
                            } else {
                                //Selects Truck
                                if (input3 == 1) {
                                    int count = 0;
                                    for (int i = 0; i < vehicles.size() ; i++) {
                                        if (vehicles.get(i) instanceof Truck) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        System.out.println("Error: No vehicles of selected type is available.\n");
                                    } else {
                                        System.out.println("\nZip Code Options:\n" +
                                                "1) Send to first ZIP Code\n" +
                                                "2) Send to mode of ZIP Codes");
                                        int input4;
                                        input4 = scan.nextInt();
                                        //Selects First ZipCode
                                        if (input4 == 1) {
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof Truck) {
                                                    vehicles.get(i).setZipDest(packages.get(0).getDestination()
                                                            .getZipCode());
                                                    vehicles.get(i).fill(packages);
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    System.out.println(vehicles.get(i).report());
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                            //Selects Mode ZipCode
                                        } else if (input4 == 2) {
                                            int zip = 0;
                                            int max = 0;
                                            for (int i = 0; i < packages.size() ; i++) {
                                                int counts = 0;
                                                for (int j = 0; j < packages.size() ; j++) { //double loops compares
                                                    // one to second value
                                                    if (packages.get(i).getDestination().getZipCode() ==
                                                            packages.get(j).getDestination().getZipCode()) {
                                                        counts++; //matching
                                                    }
                                                }
                                                if (counts > max ) { //if matching is more, replace current max with
                                                    // updated max
                                                    max = counts;    //also same amount won't replace since handout
                                                    // says first occurrence
                                                    zip = packages.get(i).getDestination().getZipCode(); //and update
                                                    // the zip code
                                                }
                                            }
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof Truck) {
                                                    vehicles.get(i).setZipDest(zip);
                                                    vehicles.get(i).fill(packages);
                                                    System.out.println(vehicles.get(i).report());
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                        } //End Zip
                                    } //End Option 1
                                    //Selects Drone
                                } else if (input3 == 2) {
                                    int count = 0;
                                    for (int i = 0; i < vehicles.size() ; i++) {
                                        if (vehicles.get(i) instanceof Drone) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        System.out.println("Error: No vehicles of selected type is available.\n");
                                    } else {
                                        System.out.println("\nZip Code Options:\n" +
                                                "1) Send to first ZIP Code\n" +
                                                "2) Send to mode of ZIP Codes");
                                        int input4;
                                        input4 = scan.nextInt();
                                        //Selects First ZipCode
                                        if (input4 == 1) {
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof Drone) {
                                                    vehicles.get(i).setZipDest(packages.get(0).getDestination()
                                                            .getZipCode());
                                                    vehicles.get(i).fill(packages);
                                                    System.out.println(vehicles.get(i).report());
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                            //Selects Mode ZipCode
                                        } else if (input4 == 2) {
                                            int zip = 0;
                                            int max = 0;
                                            for (int i = 0; i < packages.size() ; i++) {
                                                int counts = 0;
                                                for (int j = 0; j < packages.size() ; j++) { //double loops compares
                                                    // one to second value
                                                    if (packages.get(i).getDestination().getZipCode() ==
                                                            packages.get(j).getDestination().getZipCode()) {
                                                        counts++; //matching
                                                    }
                                                }
                                                if (counts > max ) { //if matching is more, replace current max with
                                                    // updated max
                                                    max = counts;    //also same amount won't replace since handout
                                                    // says first occurrence
                                                    zip = packages.get(i).getDestination().getZipCode(); //and update
                                                    // the zip code
                                                }
                                            }
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof Drone) {
                                                    vehicles.get(i).setZipDest(zip);
                                                    vehicles.get(i).fill(packages);
                                                    System.out.println(vehicles.get(i).report());
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                        } //End Zip
                                    } //End Option 2
                                    //Selects Cargo Plane
                                } else if (input3 == 3) {
                                    int count = 0;
                                    for (int i = 0; i < vehicles.size() ; i++) {
                                        if (vehicles.get(i) instanceof CargoPlane) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        System.out.println("Error: No vehicles of selected type is available.\n");
                                    } else {
                                        System.out.println("\nZip Code Options:\n" +
                                                "1) Send to first ZIP Code\n" +
                                                "2) Send to mode of ZIP Codes");
                                        int input4;
                                        input4 = scan.nextInt();
                                        //Selects First ZipCode
                                        if (input4 == 1) {
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof CargoPlane) {
                                                    vehicles.get(i).setZipDest(packages.get(0).getDestination()
                                                            .getZipCode());
                                                    vehicles.get(i).fill(packages);
                                                    System.out.println(vehicles.get(i).report());
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                            //Selects Mode ZipCode
                                        } else if (input4 == 2) {
                                            int zip = 0;
                                            int max = 0;
                                            for (int i = 0; i < packages.size() ; i++) {
                                                int counts = 0;
                                                for (int j = 0; j < packages.size() ; j++) { //double loops compares
                                                    // one to second value
                                                    if (packages.get(i).getDestination().getZipCode() ==
                                                            packages.get(j).getDestination().getZipCode()) {
                                                        counts++; //matching
                                                    }
                                                }
                                                if (counts > max ) { //if matching is more, replace current max with
                                                    // updated max
                                                    max = counts;    //also same amount won't replace since handout
                                                    // says first occurrence
                                                    zip = packages.get(i).getDestination().getZipCode(); //and update
                                                    // the zip code
                                                }
                                            }
                                            for (int i = 0; i < vehicles.size(); i++) {
                                                if (vehicles.get(i) instanceof CargoPlane) {
                                                    vehicles.get(i).setZipDest(zip);
                                                    vehicles.get(i).fill(packages);
                                                    System.out.println(vehicles.get(i).report());
                                                    nPackages += vehicles.get(i).getPackages().size();
                                                    profit += vehicles.get(i).getProfit();
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        packages.remove(vehicles.get(i).getPackages().get(j));
                                                    }
                                                    packagesWarehouse -= vehicles.get(i).getPackages().size();
                                                    vehicles.remove(vehicles.get(i));
                                                    flag3 = false;
                                                    i = vehicles.size(); //breaks out of for loop
                                                }
                                            }
                                        } //End Zip
                                    } //End Option 3
                                    //Selects First Available
                                } else if (input3 == 4) {
                                    if (vehicles.size() == 0) {
                                        System.out.println("Error: No vehicles of selected type is available.\n");
                                    } else {
                                        System.out.println("\nZip Code Options:\n" +
                                                "1) Send to first ZIP Code\n" +
                                                "2) Send to mode of ZIP Codes");
                                        int input4;
                                        input4 = scan.nextInt();
                                        //Selects First ZipCode
                                        if (input4 == 1) {
                                            vehicles.get(0).setZipDest(packages.get(0).getDestination().getZipCode());
                                            vehicles.get(0).fill(packages);
                                            System.out.println(vehicles.get(0).report());
                                            nPackages += vehicles.get(0).getPackages().size();
                                            profit += vehicles.get(0).getProfit();
                                            for (int j = 0; j < vehicles.get(0).getPackages().size(); j++) {
                                                packages.remove(vehicles.get(0).getPackages().get(j));
                                            }
                                            packagesWarehouse -= vehicles.get(0).getPackages().size();
                                            vehicles.remove(vehicles.get(0));
                                            flag3 = false;
                                            //Selects Mode ZipCode
                                        } else if (input4 == 2) {
                                            int zip = 0;
                                            int max = 0;
                                            for (int i = 0; i < packages.size() ; i++) {
                                                int counts = 0;
                                                for (int j = 0; j < packages.size() ; j++) { //double loops compares
                                                    // one to second value
                                                    if (packages.get(i).getDestination().getZipCode() ==
                                                            packages.get(j).getDestination().getZipCode()) {
                                                        counts++; //matching
                                                    }
                                                }
                                                if (counts > max ) { //if matching is more, replace current max with
                                                    // updated max
                                                    max = counts;    //also same amount won't replace since handout
                                                    // says first occurrence
                                                    zip = packages.get(i).getDestination().getZipCode(); //and update
                                                    // the zip code
                                                }
                                            }
                                            vehicles.get(0).setZipDest(zip);
                                            vehicles.get(0).fill(packages);
                                            System.out.println(vehicles.get(0).report());
                                            nPackages += vehicles.get(0).getPackages().size();
                                            profit += vehicles.get(0).getProfit();
                                            for (int j = 0; j < vehicles.get(0).getPackages().size(); j++) {
                                                packages.remove(vehicles.get(0).getPackages().get(j));
                                            }
                                            packagesWarehouse -= vehicles.get(0).getPackages().size();
                                            vehicles.remove(vehicles.get(0));
                                            flag3 = false;
                                        } //End Zip
                                    } // Ends Option 4
                                }
                            }
                        }
                    }

                    break;
                case 5:
//                    System.out.printf("==========Statistics===========\n" +
//                            "Profits: $%16c%.2f\n" +
//                            "Packages Shipped: %13d\n" +
//                            "Packages in Warehouse: %8d\n" +
//                            "==============================\n", '$', profit, nPackages, packagesWarehouse);
                    System.out.println();
                    printStatisticsReport(profit, nPackages, packagesWarehouse);
                    System.out.println();
                    space = true;

                    break;
                case 6:
                    DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
                    DatabaseManager.savePackages(PACKAGE_FILE, packages);
                    DatabaseManager.saveProfit(PROFIT_FILE, profit);
                    DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, nPackages);
                    DatabaseManager.savePrimeDay(PRIME_DAY_FILE, prime);
                    flag = false;
                    break;
            }
        }
    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using
        // DatabaseManager
    	
    
    }

    public static void printStatisticsReport(double profit, int packagesShipped, int size) {
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
        if (profit < 0) {
            System.out.printf("==========Statistics==========\n" +
                    "Profits: %24s%c\n" +
                    "Packages Shipped: %16d\n" +
                    "Packages in Warehouse: %11d\n" +
                    "==============================\n", "(" +
                    (numberFormatter.format(Math.abs(profit))),
                    ')', packagesShipped, size);
        } else {
            System.out.printf("==========Statistics==========\n" +
                    "Profits: %25s\n" +
                    "Packages Shipped: %16d\n" +
                    "Packages in Warehouse: %11d\n" +
                    "==============================\n",
                    (numberFormatter.format(profit)),
                    packagesShipped, size);
        }
    }


}