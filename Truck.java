import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * <h1>Truck</h1> Represents a Truck
 */
public class Truck extends Vehicle {
//    private String licensePlate;
//    private double maxWeight;
//    private double currentWeight;
//    private int zipDest;
//    private ArrayList<Package> packages;
    private int range = 1;

    private final double GAS_RATE = 1.66;

    /**
     * Default Constructor
     */
    //============================================================================
    public Truck() {
        super();
    }

    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate1 license plate of vehicle
     * @param maxWeight1    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Truck(String licensePlate1, double maxWeight1) {
        super(licensePlate1, maxWeight1);
    }

    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Vehicle.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.66)
     * </p>
     */
    @Override
    public double getProfit() {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.ENGLISH);
        double p = 0;
        for (int i = 0;i< getPackages().size();i++) {
            p += getPackages().get(i).getPrice();
        }
        return Double.parseDouble(numberFormatter.format( p - GAS_RATE * range));
    }

    /**
     * Generates a String of the truck report. Truck report includes:
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
        return "==========Truck Report==========\n" +
                "License Plate No.: " + getLicensePlate() + "\n" +
                "Destination: " + getZipDest() + "\n" +
                "Weight Load: " + getCurrentWeight() + "/" + getMaxWeight() + "\n" +
                "Net Profit: $" + getProfit() + "\n" +
                "==============================" + "\n" + super.report();
    }
    public void fill(ArrayList<Package> warehousePackages) {
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).distance(getZipDest()) <= range) {
                addPackage(warehousePackages.get(i));
            }
        }
    }


}