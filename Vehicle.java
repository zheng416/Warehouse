import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;
    private int range;


    /**
     * Default Constructor
     */
    //============================================================================
    public Vehicle() {
        this.licensePlate = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<>();
    }

    //============================================================================


    /**
     * Constructor
     *
     * @param licensePlate1 license plate of vehicle
     * @param maxWeight1    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate1, double maxWeight1) {
        this();
        this.licensePlate = licensePlate1;
        this.maxWeight = maxWeight1;
    }

    //============================================================================


    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return this.licensePlate;
    }


    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate1 license plate to be updated to
     */
    public void setLicensePlate(String licensePlate1) {
        this.licensePlate = licensePlate1;
    }


    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return this.maxWeight;
    }


    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight1 max weight to be updated to
     */
    public void setMaxWeight(double maxWeight1) {
        this.maxWeight = maxWeight1;
    }


    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return this.currentWeight;
    }


    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return this.zipDest;
    }


    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest1 ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest1) {
        this.zipDest = zipDest1;
    }


    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return this.packages;
    }


    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        if (!isFull() & pkg.getWeight() <= maxWeight - currentWeight) {
            this.packages.add(pkg);
            this.currentWeight += pkg.getWeight();
            return true;
        } else {
            return false;
        }
    }


    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        this.currentWeight = 0;
        this.packages = new ArrayList<Package>();
    }


    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        if (this.currentWeight >= this.maxWeight) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */
    public void fill(ArrayList<Package> warehousePackages) {
        /*
        for (int i = 0; i < warehousePackages.size(); i++) {
            for (int j = i + 1; j < warehousePackages.size(); j++) {
                if (warehousePackages.get(i).distance(zipDest) > warehousePackages.get(j).distance(zipDest)) {
                    Package temp = warehousePackages.get(i);
                    warehousePackages.get(i) = warehousePackages.get(j);
                    warehousePackages.get(j) = temp;
                }
            }
        }
        Collections.sort(warehousePackages, new SortByDistance());
        for (int i = 0; i < warehousePackages.size(); i++) {
            addPackage(warehousePackages.get(i));
        }
        */
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).distance(zipDest) <= range) {
                addPackage(warehousePackages.get(i));
            }
        }

    }


    @Override
    public double getProfit() {
        return 0;
    }

    @Override
    public String report() {
        String s = "=====Shipping Labels=====";
        for (int i = 0; i < packages.size(); i++) {
            s += "\n" + packages.get(i).shippingLabel();
        }
        return s;
    }

    class SortByDistance implements Comparator {
        public int compare(Object o1, Object o2) {
            Package s1 = (Package) o1;
            Package s2 = (Package) o2;
            if (s1.distance(zipDest) < s2.distance(zipDest))
                return 1;
            return 0;
        }
    }
}