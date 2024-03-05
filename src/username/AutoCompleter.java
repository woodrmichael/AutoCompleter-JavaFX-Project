package username;

public interface AutoCompleter {
    boolean add(String word);

    boolean exactMatch(String target);

    int size();

    String getBackingClass();

    String[] allMatches(String prefix);

    static String format(long nanoseconds) {
        return null; // TODO
    }
}
