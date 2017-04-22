package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.Set;

/**
 * Created by ashiber on 21-Apr-17.
 */
public class RestaurantImpl implements Restaurant {

    private int id;
    private String name;
    private int distance;
    private Set<String> menu;


    public RestaurantImpl(int id, String name, int distance, Set<String> menu) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.menu = menu;
    }



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
     * @param student the student
     * @return true if it was rated, false otherwise.
     */
    boolean wasRatedBy(HungryStudent student) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the id.
     */
    int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return the menu
     */
    public Set<String> getMenu() {
        return menu;
    }
}
