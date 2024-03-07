/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 8 - JUnit testing
 * Name: Michael Wood
 * Created: 3/7/2024
 */
package woodm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An UnorderedList is a list that has no order and doesn't allow duplicates.
 */
public class UnorderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * Creates a new UnorderedList that removes all duplicates from the original list.
     * @param list the original list to pass in.
     *
     * @throws IllegalArgumentException thrown if the original list is null.
     */
    public UnorderedList(List<String> list) throws IllegalArgumentException {
        if(list == null) {
            throw new IllegalArgumentException("Please ensure your list is not null");
        }
        this.items = list;
        Set<String> unique = new HashSet<>(list);
        this.items.clear();
        this.items.addAll(unique);
    }

    @Override
    public boolean add(String word) throws IllegalArgumentException {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Please ensure the word you want to add" +
                    " isn't empty or null");
        }
        boolean changed = false;
        if(!this.items.contains(word)) {
            this.items.add(word);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && this.items.contains(target);
    }

    @Override
    public int size() {
        return items.size();
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
                for(int i = 0; i < size(); i++) {
                    if(this.items.get(i).startsWith(prefix)) {
                        matches = Arrays.copyOf(matches, matches.length + 1);
                        matches[matches.length - 1] = this.items.get(i);
                    }
                }
            }
        }
        return matches;
    }
}
