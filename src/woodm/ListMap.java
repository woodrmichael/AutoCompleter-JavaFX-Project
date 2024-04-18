/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 13 - AutoCompleter with Trie
 * Name: Michael Wood
 * Created: 4/18/2024
 */
package woodm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
    public boolean containsKey(Object key) {
        boolean found = false;
        for(int i = 0; !found && i < this.entries.size(); i++) {
            if(this.entries.get(i).getKey().equals(key)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public V get(Object key) {
        V value = null;
        for(int i = 0; value == null && i < this.entries.size(); i++) {
            if(this.entries.get(i).getKey().equals(key)) {
                value = this.entries.get(i).getValue();
            }
        }
        return value;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = this.remove(key);
        this.entries.add(new AbstractMap.SimpleEntry<>(key, value));
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        boolean removed = false;
        V value = null;
        for(int i = 0; !removed && i < this.entries.size(); i++) {
            if(this.entries.get(i).getKey().equals(key)) {
                value = this.entries.remove(i).getValue();
                removed = true;
            }
        }
        return value;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(this.entries);
    }

    @Override
    public void clear() {
        this.entries.clear();
    }

    @Override
    public int size() {
        return this.entries.size();
    }

    @Override
    public boolean isEmpty() {
        return this.entries.isEmpty();
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
