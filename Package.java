/**
 * <h1>Package</h1> Represents a package
 */
public class Package {
    private String id;
    private String product;
    private double weight;
    private double price;
    private ShippingAddress destination;

    /**
     * Default Constructor
     */
    //============================================================================
    //TODO
    public Package() {
        this.id = "";
        this.product = "";
        this.weight = 0;
        this.price = 0;
        this.destination = new ShippingAddress();
    }

    //============================================================================

    /**
     * Constructor
     *
     * @param id1          id number of product
     * @param product1     name of product in package
     * @param weight1      weight of package
     * @param price1       price of product
     * @param destination1 the destination of the package
     */
    //============================================================================
    //TODO
    public Package(String id1, String product1, double weight1, double price1, ShippingAddress destination1) {
        this.id = id1;
        this.product = product1;
        this.weight = weight1;
        this.price = price1;
        this.destination = destination1;
    }
    //============================================================================

    /**
     * @return id of package
     */
    public String getID() {
        //TODO
        return this.id;
    }


    /**
     * @return Name of product in package
     */
    public String getProduct() {
        //TODO
        return this.product;
    }


    /**
     * @param product the product name to set
     */
    public void setProduct(String product) {
        //TODO
        this.product = product;
    }


    /**
     * @return price of product in package
     */
    public double getPrice() {
        //TODO
        return this.price;
    }


    /**
     * @param price1 the price to set
     */
    public void setPrice(double price1) {
        //TODO
        if (price1 < 0) {
            this.price = 0;
        } else {
            this.price = price1;
        }
    }


    /**
     * @return Package weight
     */
    public double getWeight() {
        return this.weight;
    }


    /**
     * @param weight1 the weight to set
     */
    public void setWeight(double weight1) {
        if (weight1 < 0) {
            this.weight = 0;
        } else {
            this.weight = weight1;
        }
    }


    /**
     * @return The shipping address of package
     */
    public ShippingAddress getDestination() {
        return this.destination;
    }


    /**
     * @param destination1 the shipping address to set
     */
    public void setDestination(ShippingAddress destination1) {
        this.destination = destination1;
    }


    /**
     * @return The package's shipping label.
     */
    public String shippingLabel() {
        return "====================\n" +
                "TO: " + destination.getName() + "\n" +
                destination.getAddress() + "\n" +
                destination.getCity() + ", " + destination.getState() + destination.getZipCode() + "\n" +
                "Weight:         " + weight + "\n" +
                "Price:        $" + price + "\n" +
                "Product:" + product + "\n" +
                "====================";
    }
    public int distance(int d) {
        return Math.abs(d- this.destination.getZipCode());
    }

}