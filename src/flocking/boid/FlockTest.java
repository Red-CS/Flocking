package flocking.boid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Flock Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class FlockTest {

    private Flock<Boid> flock;

    /**
     * Runs before every test
     */
    @BeforeEach
    public void setUp() {
        flock = new Flock<Boid>(3);
    }


    /**
     * Test method for {@link flocking.boid.Flock#toArray()}.
     */
    @Test
    public void testToArray() {
        Boid[] boids = flock.toArray();
        assertEquals(3, boids.length);
        assertNotNull(boids[0]);
    }


    /**
     * Test method for {@link flocking.boid.Flock#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(3, flock.size());
    }

}
