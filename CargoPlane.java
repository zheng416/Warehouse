import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    final double GAS_RATE = 2.33;
//    private String licensePlate;
//    private double maxWeight;
//    private double currentWeight;
//    private int zipDest;
//    private ArrayList<Package> packages;
    private int range = 10;

    /**
     * Default Constructor
     */
    //============================================================================
    public CargoPlane() {
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
    public CargoPlane(String licensePlate1, double maxWeight1) {
        super(licensePlate1, maxWeight1);
    }

    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        for (int i = 0; i < warehousePackages.size(); i++) {
            if (warehousePackages.get(i).distance(getZipDest()) <= range) {
                addPackage(warehousePackages.get(i));
            }
        }

    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.ENGLISH);
        double p = 0;
        for (int i = 0;i< getPackages().size();i++) {
            p += getPackages().get(i).getPrice();
        }
        return Double.parseDouble(numberFormatter.format(p - GAS_RATE * range));
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        return "==========Cargo Plane Report==========\n" +
                "License Plate No.: " + getLicensePlate() + "\n" +
                "Destination: " + getZipDest() + "\n" +
                "Weight Load: " + getCurrentWeight() + "/" + getMaxWeight() + "\n" +
                "Net Profit: $" + getProfit() + "\n" +
                "==============================" + "\n" + super.report();
    }




}