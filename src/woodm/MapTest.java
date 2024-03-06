/*
 * IGNORE UNTIL LAB 13
 *
 * Course: CSC1120
 * Spring 2024
 * Lab 13 - Autocompleter with Trie
 * Name: Dr. Taylor
 * Created: 12/22/2023
 */
package woodm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import taylor.ListMap;

import java.util.Map;

/**
 * Test for a subset of the Map interface that must be implemented
 * as part of the week 13 lab assignment.
 */
class MapTest {

    private Map<Integer, Integer> map;

    @BeforeEach
    void setup() {
        map = new ListMap<>();
    }

    @org.junit.jupiter.api.Test
    void sizeAndClear() {
        Assertions.assertEquals(0, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
        map.put(0, 0);
        Assertions.assertEquals(1, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
        map.put(0, 0);
        map.put(-2, 0);
        Assertions.assertEquals(2, map.size());
        map.clear();
        Assertions.assertEquals(0, map.size());
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        Assertions.assertFalse(map.containsKey(0));
        Assertions.assertFalse(map.containsKey(1));
        map.put(0, 0);
        Assertions.assertTrue(map.containsKey(0));
        Assertions.assertFalse(map.containsKey(1));
        map.put(1, 0);
        Assertions.assertTrue(map.containsKey(0));
        Assertions.assertTrue(map.containsKey(1));
        Assertions.assertFalse(map.containsKey(-1));
    }

    @org.junit.jupiter.api.Test
    void get() {
        Assertions.assertNull(map.get(0));
        map.put(0, 2);
        Assertions.assertEquals(2, map.get(0));
        Assertions.assertNull(map.get(2));
        map.put(-1, -2);
        Assertions.assertEquals(-2, map.get(-1));
        Assertions.assertNull(map.get(-2));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        Assertions.assertTrue(map.isEmpty());
        map.put(0, 10);
        Assertions.assertFalse(map.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void put() {
        Assertions.assertNull(map.get(0));
        Assertions.assertNull(map.put(0, 2));
        Assertions.assertEquals(2, map.get(0));
        Assertions.assertNull(map.get(2));
        Assertions.assertEquals(2, map.put(0, 1));
        Assertions.assertEquals(1, map.get(0));
        map.put(-1, -2);
        Assertions.assertEquals(-2, map.get(-1));
        Assertions.assertNull(map.get(-2));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Assertions.assertNull(map.remove(0), "Should return null if item to remove is not present");
        map.put(0, 0);
        map.put(1, 1);
        Assertions.assertNull(map.remove(2), "Should return null if item to remove is not present");
        Assertions.assertEquals(0, map.remove(0));
        Assertions.assertEquals(1, map.size());
        Assertions.assertNull(map.remove(0));
        Assertions.assertEquals(1, map.remove(1));
        Assertions.assertEquals(0, map.size());
    }
}