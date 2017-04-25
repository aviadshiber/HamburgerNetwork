package OOP.Solution;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by ashiber on 25-Apr-17.
 */
public class StringHelper {


    public static <T> String delimitCollection(Collection<T> collection, Function<? super T, String> toStringResult, String delimiter) {
        Comparator<T> comparator = Comparator.comparing(toStringResult);
        return collection.stream().sorted(comparator).map(toStringResult).reduce("", (first, next) -> first.equals("") ? first + next : first + ", " + next);
    }
}
