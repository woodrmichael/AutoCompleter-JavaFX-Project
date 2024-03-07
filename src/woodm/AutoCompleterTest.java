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
}

/*
  Discussion: What method did you find most difficult to test? Why?

  TODO

*/
