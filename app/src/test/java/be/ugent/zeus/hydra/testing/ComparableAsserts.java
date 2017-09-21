/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): Niko Strijbol
 */
package be.ugent.zeus.hydra.testing;


import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Provides utilities to test and check correct implementations for {@link Comparable} and {@link Comparator}.
 * <p>
 * This class was copied from the <a href="https://github.com/SoftSmithy/softsmithy-lib/blob/develop/softsmithy-lib-core/src/test/java/org/softsmithy/lib/Tests.java">here</a>,
 * and was cleaned up and fitted with documentation.
 *
 * @author Niko Strijbol
 * @author puce
 */
public class ComparableAsserts {

    /**
     * Tests that a comparator is correct and is consistent with equals.
     *
     * @param comparator The comparator to test.
     * @param o          An object.
     * @param smaller    An object that is less than o.
     * @param equal      An object that is equal to o.
     * @param greater    An object that is greater than o.
     * @param <T>        The type of the object.
     */
    public static <T> void testComparatorConsistentWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        testComparator(comparator, o, smaller, equal, greater);
        testConsistencyWithEquals(comparator, o, smaller, equal, greater);
    }

    /**
     * Test that a comparator makes sense.
     *
     * @param comparator The comparator to test.
     * @param o          An object.
     * @param smaller    An object that is less than o.
     * @param equal      An object that is equal to o.
     * @param greater    An object that is greater than o.
     * @param <T>        The type of the object.
     */
    public static <T> void testComparator(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertAsymmetry(comparator, o, smaller, greater);
        assertSymmetry(comparator, o, equal);
        assertSignum(comparator, o, smaller, equal, greater);
        assertTransitivity(comparator, o, smaller, greater);

        assertEquals(0, comparator.compare(o, o));
    }

    private static <T> void assertAsymmetry(Comparator<? super T> comparator, T o, T smaller, T greater) {
        assertTrue(comparator.compare(o, smaller) > 0);
        assertTrue(comparator.compare(smaller, o) < 0);


        assertTrue(comparator.compare(o, greater) < 0);
        assertTrue(comparator.compare(greater, o) > 0);
    }

    private static <T> void assertSymmetry(Comparator<? super T> comparator, T o, T equal) {
        assertEquals(0, comparator.compare(o, equal));
        assertEquals(0, comparator.compare(equal, o));
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertSignum(comparator, o, smaller);
        assertSignum(comparator, o, equal);
        assertSignum(comparator, o, greater);
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o1, T o2) {
        assertEquals(Math.signum(comparator.compare(o1, o2)), -1 * Math.signum(comparator.compare(o2, o1)), 0);
    }

    private static <T> void assertTransitivity(Comparator<? super T> comparator, T o, T smaller, T greater) {
        // when
        assertTrue(comparator.compare(smaller, o) < 0);
        assertTrue(comparator.compare(o, greater) < 0);

        // then
        assertTrue(comparator.compare(smaller, greater) < 0);
    }

    private static <T> void testConsistencyWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertEquals(o.equals(smaller), comparator.compare(o, smaller) == 0);
        assertEquals(o.equals(equal), comparator.compare(o, equal) == 0);
        assertEquals(o.equals(greater), comparator.compare(o, greater) == 0);
    }

    public static <T extends Comparable<? super T>> void testComparatorConsistentWithEquals(T o, T smaller, T equal, T greater) {
        testComparatorConsistentWithEquals(new ComparableComparator<>(), o, smaller, equal, greater);
    }

    public static <T extends Comparable<? super T>> void testComparator(T o, T smaller, T equal, T greater) {
        testComparator(new ComparableComparator<>(), o, smaller, equal, greater);
    }

    private static class ComparableComparator<T extends Comparable<? super T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
}