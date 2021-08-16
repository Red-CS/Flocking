package steering.flock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steering.flock.boid.Boid;
import steering.util.Angle;
import steering.util.Perspective;

/**
 * Tests the FlockIterator for the Flock Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class FlockIteratorTest {

    private Flock<Boid> flock;
    private Iterator<Boid> iter;
    private Boid[] boids;

    /**
     * Runs before each test
     */
    @BeforeEach
    public void setUp() {
        boids = new Boid[2];
        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(new Perspective(new Angle(180f), 50f));
        }

        flock = new Flock<Boid>(boids);
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
        for (int i = 0; i < flock.size(); i++) {
            assertTrue(boids[i].equals(iter.next()));
        }

    }

}
