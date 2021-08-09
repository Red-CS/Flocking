package flocking.boid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import flocking.Window;
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
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {}


    @Test
    public void testConstructors() {
        Boid randomBoid;
        for (int i = 0; i < 10000; i++) {
            randomBoid = new Boid();
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
        boid.setPosition(new Vector2D(20.0f, 20.0f));
        assertEquals(20.0, boid.getPosition().x);
        assertEquals(20.0, boid.getPosition().y);
    }


    /**
     * Test method for {@link flocking.boid.Boid#update()}.
     */
    @Test
    public void testUpdate() {
        fail("Not yet implemented");
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
        assertEquals("[10.5, 10.5, 270.5°]", boid.toString());
    }

}
