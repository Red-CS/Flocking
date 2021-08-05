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
     * @throws java.lang.Exception
     */
    @BeforeEach
    protected void setUp() throws Exception {
        flock = new Flock<Boid>(3);
    }


    /**
     * Test method for {@link flocking.boid.Flock#toArray()}.
     */
    @Test
    protected void testToArray() {
        Boid[] boids = flock.toArray();
        assertEquals(3, boids.length);
        assertNotNull(boids[0]);
    }


    /**
     * Test method for {@link flocking.boid.Flock#size()}.
     */
    @Test
    protected void testSize() {
        assertEquals(3, flock.size());
    }

}
