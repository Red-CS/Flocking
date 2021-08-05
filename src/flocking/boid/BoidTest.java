package flocking.boid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import flocking.Window;
import flocking.util.Angle;
import flocking.util.Perspective;

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
    public void setUp() throws Exception {
        boid = new Boid(10.5, 10.5, new Angle(270.5), new Perspective(new Angle(
            180.0), 20.0));
    }


    @Test
    public void testConstructors() {
        Boid randomBoid;
        for (int i = 0; i < 10000; i++) {
            randomBoid = new Boid();
            assertTrue(boid.getX() >= 0);
            assertTrue(boid.getX() < Window.WINDOW_WIDTH);
            assertTrue(boid.getY() >= 0);
            assertTrue(boid.getY() < Window.WINDOW_HEIGHT);
        }
    }


    /**
     * Test method for {@link flocking.boid.Boid#getX()}.
     */
    @Test
    public void testGetX() {
        assertEquals(10.5, boid.getX());
    }


    /**
     * Test method for {@link flocking.boid.Boid#getY()}.
     */
    @Test
    public void testGetY() {
        assertEquals(10.5, boid.getY());
    }


    /**
     * Test method for {@link flocking.boid.Boid#setX(double)}.
     */
    @Test
    public void testSetX() {
        boid.setX(20.0);
        assertEquals(20.0, boid.getX());
    }


    /**
     * Test method for {@link flocking.boid.Boid#setY(double)}.
     */
    @Test
    public void testSetY() {
        boid.setY(20.0);
        assertEquals(20.0, boid.getY());
    }


    /**
     * Test method for {@link flocking.boid.Boid#getDirection()}.
     */
    @Test
    public void testGetDirection() {
        assertTrue(new Angle(270.5).equals(boid.getDirection()));
    }


    /**
     * Test method for {@link flocking.boid.Boid#setDirection(Angle)}.
     */
    @Test
    public void testSetDirection() {
        boid.setDirection(new Angle(90.0));
        assertTrue(new Angle(90.0).equals(boid.getDirection()));
    }


    /**
     * Test method for {@link flocking.boid.Boid#update()}.
     */
    @Test
    public void testUpdate() {
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
