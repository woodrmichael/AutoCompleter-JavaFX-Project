/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 13 - AutoCompleter with Trie
 * Name: Michael Wood
 * Created: 4/18/2024
 */
package woodm;

import java.util.List;
import java.util.Map;

/**
 * A Trie is a tree with an arbitrary amount of children which
 * stores each string as a chain of characters.
 */
public class Trie implements AutoCompleter {
    private boolean endsWord;
    private Map<Character, Trie> edges;

    /**
     * Creates a new Trie with a starting list of words.
     * @param list the original list to pass in.
     *
     * @throws IllegalArgumentException thrown if the original list is null.
     */
    public Trie(List<String> list) throws IllegalArgumentException {
        if(list == null) {
            throw new IllegalArgumentException("Please ensure your list is not null");
        }
        //add list to Map
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
        return ListMap.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        return new String[0];
    }
}
