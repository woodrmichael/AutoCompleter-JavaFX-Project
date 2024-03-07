/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 8 - JUnit testing
 * Name: Michael Wood
 * Created: 3/5/2024
 */
package woodm;

/**
 * This is the AutoCompleter interface used to implement test classes.
 */
public interface AutoCompleter {
    /**
     * Adds a word to the AutoCompleter if the word is valid.
     * Duplicates aren't allowed to be added.
     * @param word a string that is to be added to the AutoCompleter.
     * @return true if word is added to the object,
     * false if the word is already in the AutoCompleter.
     *
     * @throws IllegalArgumentException thrown if word is null or an empty string.
     */
    boolean add(String word) throws IllegalArgumentException;

    /**
     * Searches for a target in the AutoCompleter and returns true if it was found, false if not.
     * @param target the target to search for.
     * @return true if target is found in the AutoCompleter,
     * false if target is null or an empty string, or it was not found.
     */
    boolean exactMatch(String target);

    /**
     * Returns an integer representing the number of items in the AutoCompleter.
     * @return the number of items in the AutoCompleter.
     */
    int size();

    /**
     * returns a String indicating the fully qualified name of the data structure used to store
     * the words for the AutoCompleter.
     * @return a String indicating the name of the data structure used to store the words.
     */
    String getBackingClass();

    /**
     * Returns an array of all the strings in the object that begin with the prefix.
     * If prefix is an empty string, an array of all the strings in the auto completer are returned.
     * If prefix is null, an empty array is returned.
     * @param prefix the prefix to compare the words in the AutoCompleter to.
     * @return an array of all the strings in the object that begin with the prefix.
     */
    String[] allMatches(String prefix);

    /**
     * Returns a human-friendly string representing the number of nanoseconds.
     * @param nanoseconds the amount of nanoseconds it takes to complete the operation.
     * @return a human-friendly string representing the number of nanoseconds.
     */
    static String format(long nanoseconds) {
        final long nsToDay = 86_400_000_000_000L;
        final long nsToHr = 3_600_000_000_000L;
        final long nsToMin = 60_000_000_000L;
        final long nsToS = 1_000_000_000;
        final long nsToMs = 1_000_000;
        final long nsToMicros = 1_000;
        String time;
        if(nanoseconds / nsToDay >= 1) { // 2 day(s) 5 hour(s) 32 minute(s)
            long days = nanoseconds / nsToDay;
            nanoseconds %= nsToDay;
            long hours = nanoseconds / nsToHr;
            nanoseconds %= nsToHr;
            long minutes = nanoseconds / nsToMin;
            time = days + " day(s) " + hours + " hour(s) " + minutes + " minute(s)";
        } else if(nanoseconds / nsToHr >= 1) { // 14 hour(s) 22 minute(s) 8 second(s)
            long hours = nanoseconds / nsToHr;
            nanoseconds %= nsToHr;
            long minutes = nanoseconds / nsToMin;
            nanoseconds %= nsToMin;
            long seconds = nanoseconds / nsToS;
            time = hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)";
        } else if(nanoseconds / nsToMin >= 1) { // 42 minute(s) 55.3 second(s)
            long minutes = nanoseconds / nsToMin;
            nanoseconds %= nsToMin;
            double seconds = (double) nanoseconds / nsToS;
            time = String.format("%d minute(s) %.1f second(s)", minutes, seconds);
        } else if(nanoseconds / nsToS >= 1) { // 18.8 second(s)
            double seconds = (double) nanoseconds / nsToS;
            time = String.format("%.1f second(s)", seconds);
        } else if(nanoseconds / nsToMs >= 1) { // 998.8 millisecond(s)
            double milliseconds = (double) nanoseconds / nsToMs;
            time = String.format("%.1f millisecond(s)", milliseconds);
        } else if(nanoseconds / nsToMicros >= 1) { // 318.8 microsecond(s)
            double microseconds = (double) nanoseconds / nsToMicros;
            time = String.format("%.1f microsecond(s)", microseconds);
        } else { // 7 nanosecond(s)
            time = nanoseconds + " nanosecond(s)";
        }
        return time;
    }
}
