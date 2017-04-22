package OOP.Solution;

import OOP.Provided.HamburgerNetwork;
import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ashiber on 21-Apr-17.
 */
public class HamburgerNetworkImpl implements HamburgerNetwork {
    @Override
    public HungryStudent joinNetwork(int id, String name) throws HungryStudent.StudentAlreadyInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Restaurant addRestaurant(int id, String name, int dist, Set<String> menu) throws Restaurant.RestaurantAlreadyInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
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
