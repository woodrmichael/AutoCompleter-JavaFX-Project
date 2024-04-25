/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 14 - Auto Completer Continued - HashTable
 * Name: Michael Wood
 * Created: 4/19/2024
 */
package woodm;

import java.util.HashSet;
import java.util.List;

/**
 * A HashTable is a HashSet adapter class. It uses a HashSet as its backing data structure.
 */
public class HashTable implements AutoCompleter {
    private final HashSet<String> items;

    /**
     * Creates a new HashTable with a HashSet as its backing data structure.
     * @param items the original list to pass in.
     *
     * @throws IllegalArgumentException thrown if the original list is null.
     */
    public HashTable(List<String> items) throws IllegalArgumentException {
        if(items == null) {
            throw new IllegalArgumentException();
        }
        this.items = new HashSet<>(items);
    }

    @Override
    public boolean add(String word) throws IllegalArgumentException {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Please ensure the word you want to add" +
                    " isn't empty or null");
        }
        return this.items.add(word);
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && this.items.contains(target);
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
                matches = this.items.stream()
                        .filter(word -> word.startsWith(prefix))
                        .toArray(String[]::new);
            }
        }
        return matches;
    }
}
