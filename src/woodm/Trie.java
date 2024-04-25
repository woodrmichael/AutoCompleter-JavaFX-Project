/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 13 - AutoCompleter with Trie
 * Name: Michael Wood
 * Created: 4/18/2024
 */
package woodm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A Trie is a tree with an arbitrary amount of children which
 * stores each string as a chain of characters.
 */
public class Trie implements AutoCompleter {
    private boolean endsWord;
    private final Map<Character, Trie> edges;
    private int size;

    /**
     * Creates a new Trie with no words in it.
     */
    public Trie() {
        this.endsWord = false;
        this.edges = new ListMap<>();
        this.size = 0;
    }

    /**
     * Creates a new Trie with a starting list of words.
     * @param list the original list to pass in.
     *
     * @throws IllegalArgumentException thrown if the original list is null.
     */
    public Trie(List<String> list) throws IllegalArgumentException {
        this();
        if(list == null) {
            throw new IllegalArgumentException("Please ensure your list is not null");
        }
        for (String word : list) {
            this.add(word);
        }
    }

    @Override
    public boolean add(String word) throws IllegalArgumentException {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Please ensure the word you want to add" +
                    " isn't empty or null");
        }
        return add(word, this);
    }

    private boolean add(String word, Trie node) {
        boolean changed = false;
        if(word.isEmpty()) {
            if(!node.endsWord) {
                node.endsWord = true;
                changed = true;
                this.size++;
            }
        } else {
            char letter = word.charAt(0);
            if(!node.edges.containsKey(letter)) {
                node.edges.put(letter, new Trie());
            }
            Trie nextNode = node.edges.get(letter);
            changed = add(word.substring(1), nextNode);
        }
        return changed;
    }

    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && exactMatch(target, this);
    }

    private boolean exactMatch(String target, Trie node) {
        boolean found = false;
        if(target.isEmpty()) {
            if(node.endsWord) {
                found = true;
            }
        } else {
            char letter = target.charAt(0);
            if(node.edges.containsKey(letter)) {
                Trie nextNode = node.edges.get(letter);
                found = exactMatch(target.substring(1), nextNode);
            }
        }
        return found;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String getBackingClass() {
        return ListMap.class.getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        List<String> matches = new ArrayList<>();
        if(prefix != null) {
            Trie node = this;
            if(!prefix.isEmpty()) {
                node = findPrefixNode(this, prefix);
            }
            allMatches(prefix, node, matches);
        }
        return matches.toArray(new String[0]);
    }

    private void allMatches(String prefix, Trie node, List<String> matches) {
        if(node != null) {
            if(node.endsWord) {
                matches.add(prefix);
            }
            for(Map.Entry<Character, Trie> entry : node.edges.entrySet()) {
                char letter = entry.getKey();
                Trie nextNode = entry.getValue();
                allMatches(prefix + letter, nextNode, matches);
            }
        }
    }

    private Trie findPrefixNode(Trie node, String prefix) {
        Trie nextNode = null;
        if(!prefix.isEmpty()) {
            char letter = prefix.charAt(0);
            if(prefix.substring(1).isEmpty()) {
                nextNode = node.edges.get(letter);
            } else if(node.edges.containsKey(letter)) {
                nextNode = findPrefixNode(node.edges.get(letter), prefix.substring(1));
            }
        }
        return nextNode;
    }
}
