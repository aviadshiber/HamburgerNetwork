package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.*;


public class RestaurantImpl implements Restaurant {

    private int id;
    private String name;
    private int distance;
    private Set<String> menu;
    /**
     * a list of all the students that rates the restaurant
     */
    HashMap<HungryStudent, Integer> students ;

    public RestaurantImpl(int id, String name, int distance, Set<String> menu) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.menu = menu;
        this.students = new HashMap<HungryStudent, Integer>();
    }


    @Override
    public int distance() {
        return this.distance;
    }

    @Override
    public Restaurant rate(HungryStudent s, int r) throws RateRangeException {
        HungryStudent temp;
        if(r < 0 || r > 5) throw new RateRangeException();
        this.students.put(s,r);
        return this;
    }

    @Override
    public int numberOfRates() {
        return this.students.size();
    }

    @Override
    public double averageRating(){
        double ret=0;
        if(this.students.size() != 0) {
            for(Integer value : this.students.values()) {
                ret += value;
            }
        }
        return ret/((double)this.students.size());
    }

    @Override
    public int compareTo(Restaurant o) {
        if (this.equals(o))
            return 0;
        if (o instanceof RestaurantImpl) {
            RestaurantImpl other = (RestaurantImpl) o;
            if (this.getId() < other.getId())
                return -1;
            return 1;
        }
        //TODO: what should be returned here?
        throw new RuntimeException("o is not RestaurantImpl instance");
    }

    /**
     * the id of the Restaurant.
     *
     * @return the hash code of a student
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * the method checks if the student rated this restaurant
     * @param student the student
     * @return true if it was rated, false otherwise.
     */
    boolean wasRatedBy(HungryStudent student) {
        return this.students.containsKey(student);
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


    /**
     * this method check equality of the object.
     * this method should be override in subclasses
     *
     * @param o the other RestaurantImpl instance.
     * @return true if they are equal, false otherwise. @see https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)
     */
    protected boolean eq(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof RestaurantImpl)) return false;
        RestaurantImpl other = (RestaurantImpl) o;
        return id == other.id;
    }

    /**
     * the method check if two RestaurantImpl are equal by comparing their id.
     * the equals method bound to the contract in java.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return this.eq(o) && ((RestaurantImpl) o).eq(this);
    }

}
