package OOP.Solution;

import OOP.Provided.HamburgerNetwork;
import OOP.Provided.HungryStudent;
import OOP.Provided.HungryStudent.StudentAlreadyInSystemException;
import OOP.Provided.Restaurant;
import OOP.Provided.Restaurant.RestaurantAlreadyInSystemException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by ashiber on 21-Apr-17.
 */
public class HamburgerNetworkImpl implements HamburgerNetwork {

    private HashMap<Integer, HungryStudent> students;
    private HashMap<Integer, Restaurant> restaurants;

    public HamburgerNetworkImpl() {

        students = new HashMap<>();
        restaurants = new HashMap<>();
    }

    @Override
    public HungryStudent joinNetwork(int id, String name) throws StudentAlreadyInSystemException {
        HungryStudentImpl newStudent;
        if (students.containsKey(id)) throw new StudentAlreadyInSystemException();
        newStudent = new HungryStudentImpl(id, name);
        students.put(id, newStudent);
        return newStudent;
    }

    @Override
    public Restaurant addRestaurant(int id, String name, int dist, Set<String> menu) throws RestaurantAlreadyInSystemException {
        RestaurantImpl newRestaurant;
        if (restaurants.containsKey(id)) throw new RestaurantAlreadyInSystemException();
        newRestaurant = new RestaurantImpl(id, name, dist, menu);
        restaurants.put(id, newRestaurant);
        return newRestaurant;
    }

    @Override
    public Collection<HungryStudent> registeredStudents() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Restaurant> registeredRestaurants() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HungryStudent getStudent(int id) throws HungryStudent.StudentNotInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Restaurant getRestaurant(int id) throws Restaurant.RestaurantNotInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HamburgerNetwork addConnection(HungryStudent s1, HungryStudent s2) throws HungryStudent.StudentNotInSystemException, HungryStudent.ConnectionAlreadyExistsException, HungryStudent.SameStudentException {
        return null;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        return null;
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        return null;
    }

    @Override
    public boolean getRecommendation(HungryStudent s, Restaurant r, int t) throws HungryStudent.StudentNotInSystemException, Restaurant.RestaurantNotInSystemException, ImpossibleConnectionException {
        return false;
    }
}
