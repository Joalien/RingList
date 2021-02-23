import fr.kubys.RingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RingListTest {

    RingList<Integer> ringList;
    
    @BeforeEach
    void init() {
        this.ringList = new RingList<>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Nested
    class UnitTest {
        @Test
        void deal() {
            ringList.deal();
            assertEquals(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), ringList.mapToRingList());
        }

        @Test
        void cutWithPositiveNumber() {
            ringList.cut(3);
            assertEquals(List.of(3, 4, 5, 6, 7, 8, 9, 0, 1, 2), ringList.mapToRingList());
        }

        @Test
        void cutWithNegativeNumber() {
            ringList.cut(-3);
            assertEquals(List.of(7, 8, 9, 0, 1, 2, 3, 4, 5, 6), ringList.mapToRingList());
        }

        @Test
        void increment() {
            ringList.increment(3);
            assertEquals(List.of(0, 7, 4, 1, 8, 5, 2, 9, 6, 3), ringList.mapToRingList());
        }
    }

    @Nested
    class ExampleTests {
        @Test
        void example1() {
            ringList.increment(7);
            ringList.deal();
            ringList.deal();
            assertEquals(List.of(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), ringList.mapToRingList());
        }

        @Test
        void example2() {
            ringList.cut(6);
            ringList.increment(7);
            ringList.deal();
            assertEquals(List.of(3, 0, 7, 4, 1, 8, 5, 2, 9, 6), ringList.mapToRingList());
        }

        @Test
        void example3() {
            ringList.increment(7);
            ringList.increment(9);
            ringList.cut(-2);
            assertEquals(List.of(6, 3, 0, 7, 4, 1, 8, 5, 2, 9), ringList.mapToRingList());

        }

        @Test
        void example4() {
            ringList.deal();
            ringList.cut(-2);
            ringList.increment(7);
            ringList.cut(8);
            ringList.cut(-4);
            ringList.increment(7);
            ringList.cut(3);
            ringList.increment(9);
            ringList.increment(3);
            ringList.cut(-1);
            assertEquals(List.of(9, 2, 5, 8, 1, 4, 7, 0, 3, 6), ringList.mapToRingList());
        }
    }

    @Nested
    class iterator {

        public static final String EXPECTED_ORDER_WITH_COMMA = "0, 3, 6, 9, 2, 5, 8, 1, 4, 7";
        public static final String EXPECTED_ORDER = "0369258147";

        @BeforeEach
        void increment7() {
            ringList.increment(7);
        }

        @Test
        void testToString() {
            assertTrue(ringList.toString().contains(EXPECTED_ORDER_WITH_COMMA));
        }

        @Test
        void testRingList() {
            assertTrue(ringList.mapToRingList().toString().contains(EXPECTED_ORDER_WITH_COMMA));
        }

        @Test
        void testForI() {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < ringList.size(); i++) {
                s.append(ringList.get(i));
            }

            assertTrue(s.toString().contains(EXPECTED_ORDER));
        }

        @Test
        void testForEach() {
            StringBuilder s = new StringBuilder();
            for (Integer element : ringList) {
                s.append(element);
            }

            assertTrue(s.toString().contains(EXPECTED_ORDER));
        }

        @Test
        void testListDotForEach() {
            StringBuilder s = new StringBuilder();
            ringList.forEach(s::append);

            assertTrue(s.toString().contains(EXPECTED_ORDER));
        }


        @Test
        void testStream() {
            StringBuilder s = new StringBuilder();
            ringList.stream().forEach(s::append);

            assertTrue(s.toString().contains(EXPECTED_ORDER));
        }


        @Test
        void testMapToOtherList() {
            assertTrue(new ArrayList<>(ringList).toString().contains(EXPECTED_ORDER_WITH_COMMA));
        }
    }
}
