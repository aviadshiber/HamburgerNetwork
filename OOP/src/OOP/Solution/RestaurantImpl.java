package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

/**
 * Created by ashiber on 21-Apr-17.
 */
public class RestaurantImpl implements Restaurant {
    @Override
    public int distance() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Restaurant rate(HungryStudent s, int r) throws RateRangeException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int numberOfRates() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double averageRating() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(Restaurant o) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * the method checks if the student rated this restaurant
     * @param student
     * @return true if it was rated, false otherwise.
     */
    boolean wasRatedBy(HungryStudent student) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
