package steering.flock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steering.flock.boid.Boid;
import steering.util.Angle;
import steering.util.Perspective;

/**
 * Tests the Flock Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class FlockTest {

    private Flock<Boid> flock;
    private Boid[] boids;

    /**
     * Runs before every test
     */
    @BeforeEach
    public void setUp() {
        boids = new Boid[3];
        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(new Perspective(new Angle(180f), 50f));
        }

        flock = new Flock<Boid>(boids);
    }


    /**
     * Test method for {@link flocking.boid.Flock#toArray()}.
     */
    @Test
    public void testToArray() {
        Boid[] boids = flock.toArray();
        assertEquals(3, boids.length);
        for (int i = 0; i < boids.length; i++) {
            assertNotNull(boids[i]);
            assertTrue(boids[i].equals(this.boids[i]));
        }

    }


    /**
     * Test method for {@link flocking.boid.Flock#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(3, flock.size());
    }

}
