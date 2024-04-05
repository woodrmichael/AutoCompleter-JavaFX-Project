/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 11 - Auto Complete
 * Name: Michael Wood
 * Created: 4/4/2024
 */
package woodm;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A BinarySearchTree is a Binary Search Tree
 * that has a TreeSet backing class which doesn't allow duplicates.
 */
public class BinarySearchTree implements AutoCompleter {
    private final TreeSet<String> items;

    /**
     * Creates a new BinarySearchTree and adds all nodes from the treeSet
     * into the backing tree.
     * @param treeSet the original tree set to pass in.
     *
     * @throws IllegalArgumentException thrown if the original tree set is null.
     */
    public BinarySearchTree(TreeSet<String> treeSet) throws IllegalArgumentException {
        if(treeSet == null) {
            throw new IllegalArgumentException("Please ensure your list is not null");
        }
        this.items = treeSet;
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
                SortedSet<String> matchSet = this.items.subSet(prefix, prefix + Character.MAX_VALUE);
                matches = matchSet.toArray(new String[0]);
            }
        }
        return matches;
    }
}
