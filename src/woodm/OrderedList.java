/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 11 - Auto Complete
 * Name: Michael Wood
 * Created: 3/28/2024
 */
package woodm;

import java.util.List;

/**
 * An OrderedList is a list that has an order and doesn't allow duplicates.
 */
public class OrderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * Creates a new OrderedList that removes all duplicates from the original list
     * and puts all items in order alphabetically.
     * @param list the original list to pass in.
     *
     * @throws IllegalArgumentException thrown if the original list is null.
     */
    public OrderedList(List<String> list) throws IllegalArgumentException {
        if(list == null) {
            throw new IllegalArgumentException("Please ensure your list is not null");
        }
        // NEED TO UPDATE - MUST ENSURE NO DUPLICATES AND ITEMS IS IN ORDER.
        items = list;
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
        return null;
    }

    @Override
    public String[] allMatches(String prefix) {
        return new String[0];
    }
}
