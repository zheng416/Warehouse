import java.util.ArrayList;

/**
 * <h1>Drone</h1> Represents a Drone
 */

public class Drone extends Vehicle {

    final private double GAS_RATE = 1.33;
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;
    private int range = 1;

    /**
     * Default Contructor
     */
    //============================================================================
    public Drone() {
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
     * @param maxWeight1    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Drone(String licensePlate1, double maxWeight1) {
        this();
        this.licensePlate = licensePlate1;
        this.maxWeight = maxWeight1;
    }

    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double p;
        for (int i = 0;i< packages.size();i++) {
            p += packages.get(i).getPrice();
        }
        return p - GAS_RATE * range;
    }

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     *
     * @return Truck Report
     */
    @Override
    public String report() {
        return "==========Drone Report==========\n" +
                "License Plate No.: " + licensePlate + "\n" +
                "Destination: " + zipDest + "\n" +
                "Weight Load: " + currentWeight + "/" + maxWeight + "\n" +
                "Net Profit: $" + getProfit() + "\n" +
                "==============================" + "\n" + super.report();
    }
    public void fill(ArrayList<Package> warehousePackages) {
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).distance(zipDest) <= range) {
                addPackage(warehousePackages.get(i));
            }
        }

    }


}
