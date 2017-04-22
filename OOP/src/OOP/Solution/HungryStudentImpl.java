package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.*;

/**
 * Created by ashiber on 21-Apr-17.
 */
public class HungryStudentImpl implements HungryStudent {
    private int id;
    private String name;
    /**
     * a list of all the student friends
     */
    private Set<HungryStudent> friends;
    /**
     * a collection of all the favourite restaurants.
     */
    private Collection<Restaurant> favRests;

    public HungryStudentImpl(int id, String name) {
        this.id = id;
        this.name = name;
        friends = new TreeSet<>();
        favRests = new PriorityQueue<>();
    }

    @Override
    public HungryStudent favorite(Restaurant r) throws UnratedFavoriteRestaurantException {
        favRests.add(r);
        if (r instanceof RestaurantImpl) {
            RestaurantImpl rest = (RestaurantImpl) r;
            if (!rest.wasRatedBy(this)) {
                throw new UnratedFavoriteRestaurantException();
            }
        }
        return this;
    }

    @Override
    public Collection<Restaurant> favorites() {
        Collection<Restaurant> copyFav=new PriorityQueue<>();
        copyFav.addAll(favRests);
        return copyFav;
    }

    @Override
    public HungryStudent addFriend(HungryStudent s) throws SameStudentException, ConnectionAlreadyExistsException {
        //a student cannot be a friend of himself
        if(this.equals(s))
            throw new SameStudentException();
        //validate that the friend is not already in the friends collection
        if(this.friends.contains(s))
            throw new ConnectionAlreadyExistsException();
        //everything is valid we can add the student to his friends
        this.friends.add(s);
        return this;
    }

    @Override
    public Set<HungryStudent> getFriends() {
        Set<HungryStudent> copyOfFriends=new TreeSet<>();
        copyOfFriends.addAll(friends);
        return copyOfFriends;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(int rLimit) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Restaurant> favoritesByDist(int dLimit) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(HungryStudent o) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
