/**
 * <h1>Profitable</h1>
 * <p>
 * This interface represents something that can be used to make a profit. Along
 * with returning total profits it must also be able to provide a report.
 *
 * @author Wenxi Zhang & Jacky Zheng
 *
 * @version 2018-12-04
 */
public interface Profitable {

    double getProfit();

    String report();

}