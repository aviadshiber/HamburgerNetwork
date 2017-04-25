package OOP.Tests;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by ashiber on 22-Apr-17.
 */
public class TestToString {

    @Test
    public void test() {
        ArrayList<String> l = new ArrayList<>();
        l.add("BBB");
        l.add("Burger salon");
        System.out.println(l.toString().substring(1, l.toString().length() - 1));
    }
}
