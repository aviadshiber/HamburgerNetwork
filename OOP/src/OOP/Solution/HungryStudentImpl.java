package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

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
        //returning a copy of faveRest to make the object immutable
        return new PriorityQueue<>(favRests);
    }

    @Override
    public HungryStudent addFriend(HungryStudent s) throws SameStudentException, ConnectionAlreadyExistsException {
        //a student cannot be a friend of himself
        if (this.equals(s))
            throw new SameStudentException();
        //validate that the friend is not already in the friends collection
        if (this.friends.contains(s))
            throw new ConnectionAlreadyExistsException();
        //everything is valid we can add the student to his friends
        this.friends.add(s);
        return this;
    }

    @Override
    public Set<HungryStudent> getFriends() {
        //returning a copy of faveRest to make the object immutable
        return new TreeSet<>(friends);
    }

    @Override
    public Collection<Restaurant> favoritesByRating(int rLimit) {
        Collection<RestaurantImpl> copyFavRest = new ArrayList<>();
        //copying only the RestaurantImpl instances.
        favRests.forEach(r -> {
                    if (r instanceof RestaurantImpl)
                        copyFavRest.add((RestaurantImpl) r);
                }
        );
        if (copyFavRest.size() != (long) favRests.size())
            throw new RuntimeException("Not all instances are RestaurantImpl");

        //creating sorting criteria
        Comparator<RestaurantImpl> byRating = Comparator.comparingDouble(RestaurantImpl::averageRating).reversed();
        Comparator<RestaurantImpl> byDistance = Comparator.comparingInt(RestaurantImpl::distance);
        Comparator<RestaurantImpl> byName = Comparator.comparing(RestaurantImpl::getName);
        //returning all restaurants which have at least rLimit rank and sorting them by the 3 criteria
        return copyFavRest.stream()
                .filter((r) -> r.averageRating() >= rLimit).
                        sorted(byRating.thenComparing(byDistance).thenComparing(byName)).
                        collect(Collectors.toList());
    }

    @Override
    public Collection<Restaurant> favoritesByDist(int dLimit) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    protected boolean eq(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof HungryStudentImpl)) return false;
        HungryStudentImpl other = (HungryStudentImpl) o;
        return id == other.id;
    }

    @Override
    public boolean equals(Object o) {
        return this.eq(o) && ((HungryStudentImpl) o).eq(this);
    }

    @Override
    public int hashCode() {
        return id;
    }

    int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(HungryStudent o) {
        if (this.equals(o))
            return 0;
        if (o instanceof HungryStudentImpl) {
            HungryStudentImpl other = (HungryStudentImpl) o;
            if (this.getId() < other.getId())
                return -1;
            return 1;
        }
        //TODO: what should be returned here?
        throw new RuntimeException("o is not HungryStudentImpl instance");
    }

}
