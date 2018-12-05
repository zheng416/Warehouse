/**
 * <h1>Shipping Address</h1> Represents a shipping address
 *
 * @author Wenxi Zhang & Jacky Zheng
 *
 * @version 2018-12-04
 */
public class ShippingAddress {
    private String name; //e.g. (Lawson Computer Science Building)
    private String address; //e.g. Street Address (305 N University St)
    private String city; // e.g. (West Lafayette)
    private String state; // e.g. IN
    private int zipCode;

    public ShippingAddress(String name1, String address1, String city1, String state1, int zipCode1) {
        this.name = name1;
        this.address = address1;
        this.city = city1;
        this.state = state1;
        this.zipCode = zipCode1;
    }

    public ShippingAddress() {
        this.name = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zipCode = 0;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode1) {
        this.zipCode = zipCode1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state1) {
        this.state = state1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city1) {
        this.city = city1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address1) {
        this.address = address1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }
}