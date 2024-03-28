/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 8 - JUnit testing
 * Name: Michael Wood
 * Created: 3/7/2024
 */
package woodm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to ensure all implementations of the AutoCompleter interface work properly.
 */
class AutoCompleterTest {
    private AutoCompleter autoCompleter;

    @BeforeEach
    void setUp() {
        autoCompleter = new UnorderedList(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        autoCompleter = null;
    }

    @Test
    void getBackingClassTest() {
        assertEquals("java.util.ArrayList", autoCompleter.getBackingClass());
        autoCompleter = new UnorderedList(new LinkedList<>());
        assertEquals("java.util.LinkedList", autoCompleter.getBackingClass());
    }

    @Test
    void addTest() {
        assertTrue(autoCompleter.add("Hello"));
        assertFalse(autoCompleter.add("Hello"));
        assertThrows(IllegalArgumentException.class, () -> autoCompleter.add(""));
        assertThrows(IllegalArgumentException.class, () -> autoCompleter.add(null));
    }

    @Test
    void sizeTest() {
        assertEquals(0, autoCompleter.size());
        autoCompleter.add("Hello");
        assertEquals(1, autoCompleter.size());
        autoCompleter.add("Hello");
        assertEquals(1, autoCompleter.size());
    }

    @Test
    void exactMatchTest() {
        autoCompleter.add("Hello");
        assertTrue(autoCompleter.exactMatch("Hello"));
        assertFalse(autoCompleter.exactMatch("hello"));
        assertFalse(autoCompleter.exactMatch(null));
        assertFalse(autoCompleter.exactMatch(""));
    }

    @Test
    void allMatchesTest() {
        autoCompleter.add("hello");
        autoCompleter.add("hello world");
        autoCompleter.add("Hello");
        autoCompleter.add("hi");
        autoCompleter.add("help!");
        String[] helMatches = {"hello", "hello world", "help!"};
        String[] hMatches = {"hello", "hello world", "hi", "help!"};
        String[] matches = {"hello", "hello world", "Hello", "hi", "help!"};
        assertArrayEquals(helMatches, autoCompleter.allMatches("hel"));
        assertArrayEquals(hMatches, autoCompleter.allMatches("h"));
        assertArrayEquals(new String[0], autoCompleter.allMatches(null));
        assertArrayEquals(new String[0], autoCompleter.allMatches("HI"));
        assertArrayEquals(matches, autoCompleter.allMatches(""));
    }

    @Test
    void formatTest() {
        final long test1 = 192_720_000_000_000L;
        final long test2 = 51_728_000_000_000L;
        final long test3 = 2_575_300_000_000L;
        final long test4 = 18_800_000_000L;
        final long test5 = 998_800_000;
        final long test6 = 318_800;
        final long test7 = 7;
        final long test8 = 998_799_980;
        final long test9 = 51_727_999_999_980L;
        long nanoseconds = test1;
        assertEquals("2 day(s) 5 hour(s) 32 minute(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test2;
        assertEquals("14 hour(s) 22 minute(s) 8 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test3;
        assertEquals("42 minute(s) 55.3 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test4;
        assertEquals("18.8 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test5;
        assertEquals("998.8 millisecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test6;
        assertEquals("318.8 microsecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test7;
        assertEquals("7 nanosecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test8;
        assertEquals("998.8 millisecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = test9;
        assertEquals("14 hour(s) 22 minute(s) 8 second(s)", AutoCompleter.format(nanoseconds));
    }
}


/*
  Discussion: What method did you find most difficult to test? Why?

  I thought the allMatches() method was the most difficult to test because
  I was originally comparing the results of the method to another String array that has the
  same contents as what was expected. This resulted in the test failing when I used
  assertEquals because they aren't the same object. I then decided to use Arrays.equals inside
  the assertEquals and this worked. Then, I found out that the assertArrayEquals is the
  simplified version of this and that is what I ended up using.

*/
