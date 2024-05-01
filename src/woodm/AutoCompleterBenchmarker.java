/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 14 - Benchmarking
 * Name: Michael Wood
 * Created: 4/30/2024
 */
package woodm;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * A class used to benchmark different kinds methods on an AutoCompleter.
 */
public class AutoCompleterBenchmarker {

    /**
     * Runs the benchmarks a specified amount of time for a specific AutoCompleter.
     * @param implementation The type of AutoCompleter.
     * @param backingListType The type of list to back the AutoCompleter
     * @param operation The method to be benchmarked on the AutoCompleter/
     * @param size the size of the backing list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @param numberOfTests the amount of tests.
     * @return an array of longs containing the time, in nanoseconds,
     * required for each benchmark to complete.
     *
     * @throws IllegalArgumentException Thrown if implementation, backingListType,
     * operation, size, multiplier, or numberOfTests aren't valid.
     */
    public static long[] runBenchmarks(String implementation, String backingListType,
                                       String operation, int size, int multiplier,
                                       int numberOfTests) throws IllegalArgumentException {
        checkInputNumbers(size, multiplier, numberOfTests);
        long[] elapsedTimes = new long[numberOfTests];
        for(int i = 0; i < numberOfTests; i++) {
            List<String> backingList = fillList(backingListType, size);
            AutoCompleter autoCompleter = generateAutoCompleter(implementation, backingList);
            elapsedTimes[i] = runOperation(autoCompleter, operation);
            size *= multiplier;
        }
        return elapsedTimes;
    }

    /**
     * Returns a string that describes the required command line arguments to the user.
     * @return a string with text describing the required command line arguments.
     */
    public static String getHelp() {
        return "\nThis program is able to generate benchmark results that generate graphs that " +
                "show the amount of time it takes for the desired AutoCompleter implementations " +
                "to complete the desired operation as a function of the number of elements" +
                " stored in the backing list.\n" +
                "\n--implementation=[implementation] --backingListType=[backingListType] " +
                "--operation=[operation] --startSize=[startSize] "
                + "--multiplier=[multiplier] --numberOfSamples=[numberOfSamples]\n" +
                "implementation: The type of AutoCompleter to benchmark.\n" +
                "Valid Implementations: 'UnorderedList', 'OrderedList', " +
                "'BinarySearchTree', 'Trie', or 'Hashtable'.\n" +
                "operation: The method to be benchmarked on the AutoCompleter.\n" +
                "Valid Operations: 'add', 'exactMatch', or 'allMatches'.\n" +
                "startSize: the size of the backing list.\n" +
                "multiplier: how much the size of the backing list should multiply by " +
                "between each test.\nnumberOfSamples: the amount of tests.\n" +
                "Valid startSize, multiplier, numberOfSamples: x >= 1";
    }

    /**
     * Fills the AutoCompleter's backing list with random strings.
     * @param size the size of the list.
     * @return a list containing random strings.
     */
    private static List<String> fillList(String backingListType, int size) {
        final int wordLength = 10;
        List<String> backingList;
        if(backingListType.equals("ArrayList")) {
            backingList = new ArrayList<>(size);
        } else if(backingListType.equals("LinkedList")) {
            backingList = new LinkedList<>();
        } else {
            throw new IllegalArgumentException("Invalid backingListType, please try again");
        }
        for(int i = 0; i < size; i++) {
            backingList.add(AutoCompleter.getString(wordLength));
        }
        return backingList;
    }

    /**
     * Generates a specific type of AutoCompleter with an initial amount of words.
     * @param implementation the type of AutoCompleter to be created
     * @param backingList the list containing the original words.
     * @return an AutoCompleter with an initial amount of words.
     *
     * @throws IllegalArgumentException thrown if size is less than 0 or if backingListType isn't
     * a valid type. Valid Implementations are 'UnorderedList', 'OrderedList',
     * 'BinarySearchTree', 'Trie', or 'HashTable'
     */
    private static AutoCompleter generateAutoCompleter(String implementation,
                                                       List<String> backingList) {
        return switch (implementation) {
            case "UnorderedList" -> new UnorderedList(backingList);
            case "OrderedList" -> new OrderedList(backingList);
            case "BinarySearchTree" -> new BinarySearchTree(backingList);
            case "Trie" -> new Trie(backingList);
            case "HashTable" -> new HashTable(backingList);
            default -> throw new IllegalArgumentException(
                    "Invalid implementation, please try again.\n");
        };
    }

    /**
     * Runs the specified operation on the AutoCompleter.
     * @param autoCompleter the AutoCompleter to call the operation on.
     * @param operation the method to be called on the AutoCompleter.
     * @return the amount of time in ns to run the operation.
     *
     * @throws IllegalArgumentException thrown if operation isn't a valid operation.
     * Valid options are 'add', 'exactMatch', or 'allMatches'.
     */
    private static long runOperation(AutoCompleter autoCompleter, String operation) {
        final int wordLength = 10;
        String word = AutoCompleter.getString(wordLength);
        long startTime;
        long endTime;
        switch (operation) {
            case "add":
                startTime = System.nanoTime();
                autoCompleter.add(word);
                endTime = System.nanoTime();
                break;
            case "exactMatch":
                startTime = System.nanoTime();
                autoCompleter.exactMatch(word);
                endTime = System.nanoTime();
                break;
            case "allMatches":
                startTime = System.nanoTime();
                autoCompleter.allMatches(word);
                endTime = System.nanoTime();
                break;
            default:
                throw new IllegalArgumentException("Ensure the operation is valid. " +
                        "Valid options are 'add', 'exactMatch', or 'allMatches'.\n");
        }
        return endTime - startTime;
    }

    /**
     * Checks if the input numbers taken from the command line arguments are valid
     * @param size the size of the list.
     * @param multiplier how much the size of the list should multiply by between each test.
     * @param numberOfTests the amount of tests.
     *
     * @throws IllegalArgumentException Thrown if either size, multiplier,
     * or numberOfTests isn't positive.
     */
    private static void checkInputNumbers(int size, int multiplier, int numberOfTests)
            throws IllegalArgumentException {
        StringBuilder message = new StringBuilder();
        if(size < 1) {
            message.append("Please ensure size is >= 1\n");
        }
        if(multiplier < 1) {
            message.append("Please ensure multiplier is >= 1\n");
        }
        if(numberOfTests < 1) {
            message.append("Please ensure numberOfTests is >= 1\n");
        }
        if(!message.isEmpty()) {
            throw new IllegalArgumentException(message.toString());
        }
    }
}
