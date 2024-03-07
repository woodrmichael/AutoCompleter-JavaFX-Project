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
        final long nsInDay = 86_400_000_000_000L;
        final long nsInHr = 3_600_000_000_000L;
        final long nsInMin = 60_000_000_000L;
        final long nsInS = 1_000_000_000;
        final long nsInMs = 1_000_000;
        final long nsInMicros = 1_000;
        long nanoseconds = nsInDay + nsInHr + nsInMin;
        assertEquals("1 day(s) 1 hour(s) 1 minute(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = nsInHr + nsInMin + nsInS;
        assertEquals("1 hour(s) 1 minute(s) 1 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = nsInMin + nsInS;
        assertEquals("1 minute(s) 1.0 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = nsInS;
        assertEquals("1.0 second(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = nsInMs;
        assertEquals("1.0 millisecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = nsInMicros;
        assertEquals("1.0 microsecond(s)", AutoCompleter.format(nanoseconds));
        nanoseconds = 1;
        assertEquals("1 nanosecond(s)", AutoCompleter.format(nanoseconds));
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
