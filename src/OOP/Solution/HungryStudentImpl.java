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
        if (favRests.contains(r))
            return this;
        if (r instanceof RestaurantImpl) {
            RestaurantImpl rest = (RestaurantImpl) r;
            if (!rest.wasRatedBy(this)) {
                throw new UnratedFavoriteRestaurantException();
            }
        }
        favRests.add(r);
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
        Collection<RestaurantImpl> copyFavRest = RestaurantImpl.makeCopy(favRests);
        //creating sorting criteria
        Comparator<RestaurantImpl> byRating = Comparator.comparingDouble(RestaurantImpl::averageRating).reversed();
        Comparator<RestaurantImpl> byDistance = Comparator.comparingInt(RestaurantImpl::distance);
        Comparator<RestaurantImpl> byID = Comparator.comparing(RestaurantImpl::getId);
        //returning all restaurants which have at least rLimit rank and sorting them by the 3 criteria
        return copyFavRest.stream()
                .filter((r) -> r.averageRating() >= rLimit).
                        sorted(byRating.thenComparing(byDistance).thenComparing(byID)).
                        collect(Collectors.toList());
    }



    @Override
    public Collection<Restaurant> favoritesByDist(int dLimit) {
        Collection<RestaurantImpl> copyFavRest = RestaurantImpl.makeCopy(favRests);
        //sorting by 3 criteria:
        Comparator<RestaurantImpl> byDistance = Comparator.comparingInt(RestaurantImpl::distance);
        Comparator<RestaurantImpl> byRating = Comparator.comparingDouble(RestaurantImpl::averageRating).reversed();
        Comparator<RestaurantImpl> byID = Comparator.comparingInt(RestaurantImpl::getId);
        //returning all restaurants which have no more than dLimit distance and sorting them by the 3 criteria
        return copyFavRest.stream().filter(r -> r.distance() <= dLimit).sorted(byDistance.thenComparing(byRating).thenComparing(byID)).collect(Collectors.toList());

    }

    /**
     * this method check equality of the object.
     * this method should be override in subclasses
     *
     * @param o the other HungryStudentImpl instance.
     * @return true if they are equal, false otherwise. @see https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)
     */
    protected boolean eq(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof HungryStudentImpl)) return false;
        HungryStudentImpl other = (HungryStudentImpl) o;
        return id == other.id;
    }

    /**
     * the method check if two HungryStudents are equal by comparing their id.
     * the equals method bound to the contract in java.
     *
     * @param o other student
     * @return true if they are equal by id.
     */
    @Override
    public boolean equals(Object o) {
        return this.eq(o) && ((HungryStudentImpl) o).eq(this);
    }

    /**
     * the id of the student.
     *
     * @return the hash code of a student
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Id getter
     *
     * @return the id of the student
     */
    int getId() {
        return id;
    }

    /**
     * Name getter
     *
     * @return the name of the student
     */
    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        String favFormat = favRests.toString();
        favFormat = favFormat.substring(1, favFormat.length() - 1);
        return String.format("Hungry student: %s./nId: %d./nFavorites: %s.",
                getName(),
                getId(),
                favFormat
        );
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
        //Ophir said that it never gets here...
        throw new RuntimeException("o is not HungryStudentImpl instance");
    }

}
