import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Database Manager</h1>
 *
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        //TODO
        ArrayList<Vehicle> automobiles = new ArrayList<Vehicle>();
        Scanner scan;
        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
            scan = new Scanner(file);
            String temp;
            while (scan.hasNextLine()) {
                temp = scan.nextLine();
                String[] vehicleInfo = temp.split(",");
                if (vehicleInfo[0].equals("Truck")) {
                    Vehicle truck = new Truck(vehicleInfo[1], Double.parseDouble(vehicleInfo[2]));
                    automobiles.add(truck);
                } else if (vehicleInfo[0].equals("Drone")) {
                    Vehicle drone = new Drone(vehicleInfo[1], Double.parseDouble(vehicleInfo[2]));
                    automobiles.add(drone);
                } else if (vehicleInfo[0].equals("Cargo Plane")) {
                    Vehicle cargoPlane = new CargoPlane(vehicleInfo[1], Double.parseDouble(vehicleInfo[2]));
                    automobiles.add(cargoPlane);
                }
            }
        } catch (Exception e) {
            return automobiles;
        }
        return automobiles;
    }





    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        //TODO
        ArrayList<Package> shipment = new ArrayList<Package>();
        Scanner scan;
        try {
//            FileReader fr = new FileReader(file);
////            BufferedReader br = new BufferedReader(fr);
            scan = new Scanner(file);
            String temp;
            while (scan.hasNextLine()) {
                temp = scan.nextLine();
                String[] packageInfo = temp.split(",");
                Package pack = new Package(packageInfo[0], packageInfo[1], Double.parseDouble(packageInfo[2]), Double.parseDouble(packageInfo[3]) , new ShippingAddress(packageInfo[4], packageInfo[5], packageInfo[6], packageInfo[7], Integer.parseInt(packageInfo[8])));
                shipment.add(pack);
            }
        } catch (Exception e) {
            return shipment;
        }
        return shipment;
    }






    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        //TODO
        String profit;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            profit = br.readLine();
        } catch (Exception e) {
            return 0;
        }
        return Double.parseDouble(profit);
    }





    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        //TODO
        String number;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            number = br.readLine();
        } catch (Exception e) {
            return 0;
        }
        return Integer.parseInt(number);
    }




    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        //TODO
        String number;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            number = br.readLine();
        } catch (Exception e) {
            return false;
        }
        if (Integer.parseInt(number) == 1) {
            return true;
        } else {
            return false;
        }
    }





    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        //TODO

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Vehicle v : vehicles) {
                if (v instanceof Truck) {
                    bw.write("Truck,");
                }
                if (v instanceof CargoPlane) {
                    bw.write("Cargo Plane,");
                }
                if (v instanceof Drone) {
                    bw.write("Drone,");
                }
                bw.write(v.getLicensePlate() + ",");
                bw.write(Double.toString(v.getMaxWeight()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        //TODO
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < packages.size() ; i++) {
                String writing = packages.get(i).getID() + "," + packages.get(i).getProduct() + "," +
                        packages.get(i).getWeight() + "," + packages.get(i).getPrice() + "," +
                        packages.get(i).getDestination().getName() + "," + packages.get(i).getDestination().getAddress()
                        + "," + packages.get(i).getDestination().getCity() + "," +
                        packages.get(i).getDestination().getState() + "," + packages.get(i).getDestination().getZipCode();
                bw.write(writing);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.print("");
        }
    }




    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        //TODO

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

                String writing = Double.toString(profit);
                bw.write(writing);
                bw.newLine();

        } catch (Exception e) {
            System.out.print("");
        }
    }





    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        //TODO
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            String writing = Integer.toString(nPackages);
            bw.write(writing);
            bw.newLine();

        } catch (Exception e) {
            System.out.print("");
        }
    }






    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        //TODO

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            int num;
            if (primeDay) {
                num = 1;
            } else {
                num = 0;
            }
            String writing = Integer.toString(num);
            bw.write(writing);
            bw.newLine();

        } catch (Exception e) {
            System.out.print("");
        }
    }
}