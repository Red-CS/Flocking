package steering.flock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steering.flock.boid.Boid;

/**
 * Tests the FlockIterator for the Flock Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class FlockIteratorTest {

    private Flock<Boid> flock;
    private Iterator<Boid> iter;

    /**
     * Runs before each test
     */
    @BeforeEach
    public void setUp() {
        flock = new Flock<Boid>(2);
        iter = flock.iterator();
    }


    /**
     * Test method for {@link flocking.boid.Flock.FlockIterator#hasNext()}.
     */
    @Test
    public void testHasNext() {
        assertTrue(iter.hasNext());
        iter.next();
        iter.next();
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }


    /**
     * Test method for {@link flocking.boid.Flock.FlockIterator#next()}.
     */
    @Test
    public void testNext() {
        Boid[] testedBoids = flock.toArray();
        for (int i = 0; i < flock.size(); i++) {
            assertTrue(testedBoids[i].equals(iter.next()));
        }
    }

}
