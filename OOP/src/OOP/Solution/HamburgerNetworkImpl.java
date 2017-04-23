package OOP.Solution;

import OOP.Provided.HamburgerNetwork;
import OOP.Provided.HungryStudent;
import OOP.Provided.HungryStudent.ConnectionAlreadyExistsException;
import OOP.Provided.HungryStudent.SameStudentException;
import OOP.Provided.HungryStudent.StudentAlreadyInSystemException;
import OOP.Provided.HungryStudent.StudentNotInSystemException;
import OOP.Provided.Restaurant;
import OOP.Provided.Restaurant.RestaurantAlreadyInSystemException;

import java.util.ArrayList;
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
        return new ArrayList<>(students.values());
    }

    @Override
    public Collection<Restaurant> registeredRestaurants() {
        return new ArrayList<>(restaurants.values());
    }

    @Override
    public HungryStudent getStudent(int id) throws StudentNotInSystemException {
        validateStudentPresence(id);

        return students.get(id);
    }

    private void validateStudentPresence(int id) throws StudentNotInSystemException {
        if (!students.containsKey(id))
            throw new StudentNotInSystemException();
    }

    @Override
    public Restaurant getRestaurant(int id) throws Restaurant.RestaurantNotInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HamburgerNetwork addConnection(HungryStudent s1, HungryStudent s2) throws StudentNotInSystemException, ConnectionAlreadyExistsException, SameStudentException {
        if (s1 == null || s2 == null || !students.containsKey(s1) || !students.containsKey(s2))
            throw new StudentNotInSystemException();
        s1.addFriend(s2);
        s2.addFriend(s1);
        return this;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws StudentNotInSystemException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws StudentNotInSystemException {
        if (s == null)
            throw new StudentNotInSystemException();
        int studentID = s.hashCode();
        validateStudentPresence(studentID);
        ArrayList<Restaurant> result = new ArrayList<>();
        //TODO : is it okay to assume 0 is the minimum?
        s.getFriends().stream().sorted(Comparable::compareTo).forEachOrdered(friend -> result.addAll(friend.favoritesByDist(0)));
        return result;
    }

    @Override
    public boolean getRecommendation(HungryStudent s, Restaurant r, int t) throws StudentNotInSystemException, Restaurant.RestaurantNotInSystemException, ImpossibleConnectionException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
