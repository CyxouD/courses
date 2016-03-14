import java.util.Iterator;

import com.courses.spalah.list.Car;
import com.courses.spalah.list.ListFactory;
import com.courses.spalah.list.ListType;
import com.courses.spalah.list.MyList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * @author Ievgen Tararaka
 */
@RunWith(JUnit4.class)
public class SinglyLinkedListTest {
    private MyList<String> stringList;
    private MyList<Car> carList;

    @Before
    public void setUp() {
        stringList = ListFactory.createList(ListType.SINGLY_LINKED_LIST);
        carList = ListFactory.createList(ListType.SINGLY_LINKED_LIST);
    }

    @Test
    public void testStringFlow() {
        stringList.add("A"); // A
        stringList.add("B"); // A B
        stringList.add("C"); // A B C

        assertEquals(3, stringList.size());

        stringList.set(1, "A"); // A A C

        assertEquals("A", stringList.get(1));

        stringList.add(1, "B"); // A B A C

        assertEquals(4, stringList.size());

        stringList.remove(0); // B A C

        assertEquals(3, stringList.size());

        stringList.remove(1); // B C

        stringList.remove(0); // C

        assertEquals(1, stringList.size());

        stringList.remove(0); //

        assertTrue(stringList.isEmpty());

        stringList.add("C"); // C
        stringList.add("B"); // C B
        stringList.add("A"); // C B A
        stringList.add("A"); // C B A A

        int count = 0;
        for (String s : stringList) { // iterator check
            count++;
        }

        assertEquals(4, count);

        assertTrue(stringList.contains("C"));
        assertFalse(stringList.contains("Z"));
    }

    @Test
    public void testCarFlow() {
        Car civic = new Car(20_000, "Honda Civic");
        Car m3 = new Car(50_000, "MMW M3");
        Car auris = new Car(18_000, "Toyota auris");
        Car sx4 = new Car(14_000, "Suzuki SX4");

        carList.add(civic); // civic
        carList.add(sx4); // civic sx4

        assertTrue(carList.contains(new Car(20_000, "Honda Civic")));
        assertFalse(carList.contains(new Car(20_000, "Honda CRV")));

        assertEquals(2, carList.size());

        carList.remove(0); // sx4

        assertEquals(1, carList.size());

        assertEquals(carList.get(0), sx4);

        carList.add(m3); // sx4 m3
        carList.add(0, auris); // auris sx4 m3

        for (Car car : carList) {
            assertFalse(car.equals(new Car(1, "Free Car")));
        }

        carList.remove(2); // auris sx4
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove(); // auris // [empty]
        }

        assertTrue(carList.isEmpty());
    }
}
