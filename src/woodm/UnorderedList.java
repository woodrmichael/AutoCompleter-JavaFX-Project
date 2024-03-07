/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 8 - JUnit testing
 * Name: Michael Wood
 * Created: 3/7/2024
 */
package woodm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An UnorderedList is a list that has no order and doesn't allow duplicates.
 */
public class UnorderedList implements AutoCompleter {
    private final List<String> items;

    public UnorderedList(List<String> list) {
        this.items = list;
        Set<String> unique = new HashSet<>(list);
        this.items.clear();
        this.items.addAll(unique);
    }

    @Override
    public boolean add(String word) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean exactMatch(String target) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String getBackingClass() {
        return this.items.getClass().getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        return new String[0];
    }
}