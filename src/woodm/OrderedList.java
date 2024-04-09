/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 11 - Auto Complete
 * Name: Michael Wood
 * Created: 3/28/2024
 */
package woodm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

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
        this.items = list;
        Set<String> unique = new HashSet<>(list);
        this.items.clear();
        this.items.addAll(unique);
        Collections.sort(items);
    }
    @Override
    public boolean add(String word) throws IllegalArgumentException {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Please ensure the word you want to add" +
                    " isn't empty or null");
        }
        boolean changed = false;
        ListIterator<String> iterator = this.items.listIterator();
        if(!items.contains(word)) {
            if (!iterator.hasNext() && !iterator.hasPrevious()) {
                iterator.add(word);
                changed = true;
            } else if (iterator.hasNext() && iterator.next().compareTo(word) < 0) {
                iterator.previous();
                while (iterator.hasNext() && !changed) {
                    String currentWord = iterator.next();
                    if (currentWord.compareTo(word) > 0) {
                        iterator.previous();
                        iterator.add(word);
                        changed = true;
                    }
                }
                if(!changed) {
                    iterator.add(word);
                    changed = true;
                }
            } else {
                if(iterator.hasPrevious()) {
                    iterator.previous();
                }
                iterator.add(word);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty()
                && Collections.binarySearch(this.items, target) >= 0;
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public String getBackingClass() {
        return this.items.getClass().getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        String[] matches = new String[0];
        if(prefix != null) {
            if(prefix.isEmpty()) {
                matches = this.items.toArray(new String[size()]);
            } else {
                int index = Collections.binarySearch(this.items, prefix);
                if(index < 0) {
                    index = -index - 1;
                }
                List<String> matchesList = new ArrayList<>();
                while(index < items.size() && items.get(index).startsWith(prefix)) {
                    matchesList.add(items.get(index));
                    index++;
                }
                matches = matchesList.toArray(new String[0]);
            }
        }
        return matches;
    }
}
