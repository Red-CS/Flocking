package flocking.boid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import flocking.Window;
import flocking.util.Perspective;
import flocking.util.Vector2D;

/**
 * Test Class for the Boid Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class BoidTest {

    private Boid boid;

    /**
     * Runs before each test
     */
    @BeforeEach
    public void setUp() {
        boid = new Boid(new Vector2D(10.5f, 10.5f),
            Perspective.DEFAULT_PERSPECTIVE);
    }


    @Test
    public void testConstructors() {
        Boid randomBoid;
        for (int i = 0; i < 10000; i++) {
            randomBoid = new Boid(Perspective.DEFAULT_PERSPECTIVE);
            Vector2D pos = randomBoid.getPosition();
            assertTrue(pos.x >= 0);
            assertTrue(pos.x < Window.WINDOW_WIDTH);
            assertTrue(pos.y >= 0);
            assertTrue(pos.y < Window.WINDOW_HEIGHT);
        }
    }


    /**
     * Test method for {@link flocking.boid.Boid#getPosition()}.
     */
    @Test
    public void testGetPosition() {
        assertEquals(10.5, boid.getPosition().x);
        assertEquals(10.5, boid.getPosition().y);
    }


    /**
     * Test method for {@link flocking.boid.Boid#setPosition(Vector2D)}.
     */
    @Test
    public void testSetPosition() {
        boid.setPosition(new Vector2D(10.0f, 20.0f));
        assertEquals(10.0, boid.getPosition().x);
        assertEquals(20.0, boid.getPosition().y);
    }


    /**
     * Test method for {@link flocking.boid.Boid#equals()}.
     */
    @Test
    public void testEquals() {
        fail("Not yet implemented");
    }


    /**
     * Test method for {@link flocking.boid.Boid#toString()}.
     */
    @Test
    public void testToString() {
        assertTrue(boid.toString().startsWith("[10.5, 10.5, "));
        assertTrue(boid.toString().endsWith("°]"));
    }

}
