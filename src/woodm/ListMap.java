/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 13 - AutoCompleter with Trie
 * Name: Michael Wood
 * Created: 4/18/2024
 */
package woodm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A ListMap that implements the Map interface is a Map
 * that uses a list as its backing data structure.
 * @param <K> the type of keys maintained by this map.
 * @param <V> the type of mapped values.
 */
public class ListMap<K, V> implements Map<K, V> {
    private final List<Map.Entry<K, V>> entries;

    /**
     * Creates a new ListMap with an ArrayList as its backing data structure.
     */
    public ListMap() {
        this.entries = new ArrayList<>();
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(Object key, Object value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }

    @Override
    public Set<K> keySet() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map m) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
