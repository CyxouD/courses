package com.courses.spalah;

import com.courses.spalah.multimap.MultiMap;
import com.courses.spalah.multimap.MultiMapFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author Ievgen Tararaka
 */
@RunWith(JUnit4.class)
public class MultiMapTest {
    private static final String FRUIT_KEY = "fruits";
    private static final String VEGETABLE_KEY = "vegetables";
    private static final String OTHER_KEY = "other";

    private MultiMap<String, String> multiMap;

    @Before
    public void setUp() {
        multiMap = MultiMapFactory.createMultiMap();

        multiMap.put(FRUIT_KEY, "apple");
        multiMap.put(FRUIT_KEY, "banana");
        multiMap.put(FRUIT_KEY, "pear");
        multiMap.put(FRUIT_KEY, "banana");
        // [fruits - [apple, banana, pear, banana]]

        multiMap.put(VEGETABLE_KEY, "carrot");
        multiMap.put(VEGETABLE_KEY, "potato");
        multiMap.put(VEGETABLE_KEY, "salad");
        // [vegetables - [carrot, potato, salad]]

        multiMap.put(OTHER_KEY, "clock");
        multiMap.put(OTHER_KEY, "toy");
        // [other - [clock, toy]]
    }

    @Test
    public void testGet() {
        Collection<String> fruits = multiMap.get(FRUIT_KEY);
        assertEquals(4, fruits.size());

        Collection<String> random = multiMap.get("randomKey");
        assertTrue(random.isEmpty());
    }

    @Test
    public void testSizing() {
        assertEquals(9, multiMap.size());

        multiMap.removeAll(OTHER_KEY);
        assertEquals(7, multiMap.size());

        multiMap.put(VEGETABLE_KEY, "tomato");
        assertEquals(8, multiMap.size());

        multiMap.remove(VEGETABLE_KEY, "tomato");
        assertEquals(7, multiMap.size());

        multiMap.removeAll(VEGETABLE_KEY);
        multiMap.removeAll(FRUIT_KEY);
        assertEquals(0, multiMap.size());
    }

    @Test
    public void testContainsValue() {
        assertTrue(multiMap.containsValue("banana"));
        assertTrue(multiMap.containsValue("toy"));
        assertFalse(multiMap.containsValue("radish"));
    }

    @Test
    public void testRemoving() {
        Collection<String> removedOther = multiMap.removeAll(OTHER_KEY);
        assertTrue(removedOther.contains("toy"));
        assertTrue(removedOther.contains("clock"));

        assertFalse(multiMap.remove(OTHER_KEY, "trash"));
        Collection<String> removedFruits = multiMap.removeAll(FRUIT_KEY);
        Collection<String> removedVegetables = multiMap.removeAll(VEGETABLE_KEY);

        assertTrue(multiMap.isEmpty());
        assertEquals(4, removedFruits.size());
        assertEquals(3, removedVegetables.size());

        Collection<String> removedRandoms = multiMap.removeAll("randomKey");
        assertTrue(removedRandoms.isEmpty());

        multiMap.put("random", "random1");
        assertTrue(multiMap.remove("random", "random1"));
        assertTrue(multiMap.isEmpty());
    }

    @Test
    public void testAllValues() {
        Collection<String> all = multiMap.allValues();
        assertEquals(9, all.size());
        assertTrue(all.contains("banana"));
        assertFalse(all.contains("some_key"));

        boolean result = multiMap.remove(FRUIT_KEY, "banana");
        all = multiMap.allValues();
        assertTrue(result);
        assertEquals(8, all.size());
        assertTrue(all.contains("banana"));

        multiMap.removeAll(FRUIT_KEY);
        multiMap.removeAll(VEGETABLE_KEY);
        multiMap.removeAll(OTHER_KEY);
        all = multiMap.allValues();
        assertEquals(0, all.size());
    }
}
