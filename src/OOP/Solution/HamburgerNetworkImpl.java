package OOP.Solution;

import OOP.Provided.HamburgerNetwork;
import OOP.Provided.HungryStudent;
import OOP.Provided.HungryStudent.ConnectionAlreadyExistsException;
import OOP.Provided.HungryStudent.SameStudentException;
import OOP.Provided.HungryStudent.StudentAlreadyInSystemException;
import OOP.Provided.HungryStudent.StudentNotInSystemException;
import OOP.Provided.Restaurant;
import OOP.Provided.Restaurant.RestaurantAlreadyInSystemException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static OOP.Provided.Restaurant.*;

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

    private void validateStudentPresence(int id) throws StudentNotInSystemException {
        if (!students.containsKey(id))
            throw new StudentNotInSystemException();
    }
    private void validateRestaurantPresence(int id) throws RestaurantNotInSystemException {
        if (!restaurants.containsKey(id))
            throw new RestaurantNotInSystemException();
    }

    @Override
    public Restaurant getRestaurant(int id) throws RestaurantNotInSystemException {
        validateRestaurantPresence(id);

        return restaurants.get(id);
    }
    @Override
    public HungryStudent getStudent(int id) throws StudentNotInSystemException {
        validateStudentPresence(id);

        return students.get(id);
    }

    @Override
    public HamburgerNetwork addConnection(HungryStudent s1, HungryStudent s2) throws StudentNotInSystemException, ConnectionAlreadyExistsException, SameStudentException {
        if (s1 == null || s2 == null || !students.containsKey(s1.hashCode()) || !students.containsKey(s2.hashCode()))
            throw new StudentNotInSystemException();
        s1.addFriend(s2);
        s2.addFriend(s1);
        return this;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws StudentNotInSystemException {
        return sortByIDAndRunOnFriends(s,friend->friend.favoritesByRating(0));
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws StudentNotInSystemException {
       return sortByIDAndRunOnFriends(s,friend-> friend.favoritesByDist(Integer.MAX_VALUE));
    }

    private Collection<Restaurant> sortByIDAndRunOnFriends(HungryStudent s, Function<HungryStudent,Collection<Restaurant>> friendCollection) throws StudentNotInSystemException {
        if (s == null)
            throw new StudentNotInSystemException();
        int studentID = s.hashCode();
        validateStudentPresence(studentID);
        List<Restaurant> result = new ArrayList<>();

        s.getFriends().stream().sorted(Comparable::compareTo)
                .forEachOrdered(friend ->
                        result.addAll(friendCollection.apply(friend).stream()
                                .filter( r-> !result.contains(r))
                                .collect(Collectors.toList())
                        ) //end of addAll method
                );
        return result;
    }

    @Override
    public boolean getRecommendation(HungryStudent s, Restaurant r, int t) throws StudentNotInSystemException, RestaurantNotInSystemException, ImpossibleConnectionException {

        Set<HungryStudent> studentsSet;
        if(s == null)throw new StudentNotInSystemException();
        if(r == null)throw new RestaurantNotInSystemException();
        validateStudentPresence(s.hashCode());
        validateRestaurantPresence(r.hashCode());
        if(t < 0)throw new ImpossibleConnectionException();
        if(t == 0) return s.favorites().contains(r);// if t == 0 maybe the student himself prefer the restaurant

        studentsSet = new TreeSet<>();
        studentsSet.add(s);
        return s.getFriends().stream().anyMatch((student)->getRecommendation(student,r,t-1,studentsSet));
    }

    private boolean getRecommendation(HungryStudent s, Restaurant r, int t,Set<HungryStudent> studentsSet) {
        if(t == 0) return s.favorites().contains(r);// if t == 0 maybe the student prefer the restaurant
        studentsSet.add(s);
        return s.getFriends().stream().filter((student)->!studentsSet.contains(student)).anyMatch((student)->getRecommendation(student,r,t-1,studentsSet));
    }

    @Override
    public String toString(){

      /*  Registered students: <studentId1, studentId2, studentId3...>.
     * Registered restaurants: <resId1, resId2, resId3...>.
     * Students:
     * <student1Id> -> [<friend1Id, friend2Id, friend3Id...>].
     * <student2Id> -> [<friend1Id, friend2Id, friend3Id...>].
     * ...
     * End students.
*/
        String s1= "Registered students: " + students.keySet().stream().sorted().map(s->s.toString()).reduce("",(f,n)-> f.equals("")?n:f+", "+n)+".";
        String s2= "Registered restaurants: "+ restaurants.keySet().stream().sorted().map(s->s.toString()).reduce("",(f,n)-> f.equals("")?n:f+", "+n)+".";
        String s3 = "Students:";

        StringBuilder s4 = new StringBuilder();

        students.values().stream().sorted(Comparable::compareTo).forEachOrdered(
                s-> s4.append(s.hashCode()+ " -> [" + s.getFriends().stream().map((friend)->friend.hashCode()).sorted().
                        map((friend)->friend.toString()).reduce("",(f,n)-> f.equals("")?n:f+", "+n)+"]."
                ).append("\n")    //end append
        );

        String s5 = "End students.";

        return s1 + "\n" + s2 + "\n" + s3 + "\n"+ s4 + s5;
    }
}


