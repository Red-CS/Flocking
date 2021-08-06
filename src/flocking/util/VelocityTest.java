package flocking.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 5, 2021
 */
public class VelocityTest {

    private Velocity velocity;

    /**
     * Runs before each test
     */
    @BeforeEach
    public void setUp() {
        velocity = new Velocity(new Angle(45.0F), 5F);
    }


    /**
     * Test method for {@link flocking.util.Velocity#getDirection()}.
     */
    @Test
    public void testGetDirection() {
        assertTrue(velocity.getDirection().equals(new Angle(45.0F)));
    }


    /**
     * Test method for
     * {@link flocking.util.Velocity#setDirection(flocking.util.Angle)}.
     */
    @Test
    public void testSetDirection() {
        velocity.setDirection(new Angle(163F));
        assertTrue(velocity.getDirection().equals(new Angle(163F)));
        assertEquals(-4.8, velocity.x, 0.1);
        assertEquals(1.5, velocity.y, 0.1);
    }


    /**
     * Test method for {@link flocking.util.Velocity#getMagnitude()}.
     */
    @Test
    public void testGetMagnitude() {
        assertEquals(5.0F, velocity.getMagnitude());
    }


    /**
     * Test method for {@link flocking.util.Velocity#setMagnitude(float)}.
     */
    @Test
    public void testSetMagnitude() {
        velocity.setMagnitude(38.2F);
        assertEquals(38.2F, velocity.getMagnitude());
        assertEquals(27.0F, velocity.x, 0.1);
        assertEquals(27.0F, velocity.y, 0.1);
    }


    /**
     * Test method for {@link flocking.util.Velocity#add(Velocity)}.
     */
    @Test
    public void testAddVelocity() {

        // Test adding a Vector in the same direction
        velocity.add(new Velocity(new Angle(135F), 5));
        assertTrue(velocity.getDirection().equals(new Angle(90F)));
        assertEquals(7, velocity.getMagnitude(), 0.1);
        assertEquals(0, velocity.x);
        assertEquals(7, velocity.y, 0.1);

        // Test adding a Vector in the opposite direction
        setUp();
        velocity.add(new Velocity(new Angle(200F), 3));
        assertTrue(velocity.getDirection().equals(new Angle(74.1f)));
        assertEquals(0.716, velocity.x, 0.1);
        assertEquals(2.5, velocity.y, 0.1);

    }


    /**
     * Test method for {@link flocking.util.Velocity#add(Vector2D)}.
     */
    @Test
    public void testAddVector() {

        // Test positive resultant vector
        velocity.add(new Vector2D((float) (5 * Math.cos(Math.toRadians(135))),
            (float) (5 * Math.sin(Math.toRadians(45)))));
        assertTrue(velocity.getDirection().equals(new Angle(90F)));
        assertEquals(7, velocity.getMagnitude(), 0.1);
        assertEquals(0, velocity.x);
        assertEquals(7, velocity.y, 0.1);

        // Test negative resultant vector
        setUp();
        velocity.add(new Vector2D((float) (10.0 * Math.cos(Math.PI * 3. / 4.)),
            (float) (10.0 * Math.cos(Math.PI * 5. / 4.))));
        assertTrue(velocity.getDirection().equals(new Angle(225F)));
        assertEquals(5, velocity.getMagnitude(), 0.1);
        assertEquals(-3.5, velocity.x, 0.1);
        assertEquals(-3.5, velocity.y, 0.1);
    }


    /**
     * Test method for {@link flocking.util.Velocity#scale(float)}.
     */
    @Test
    public void testScale() {
        velocity.scale(2);
        assertEquals(10, velocity.getMagnitude());
        assertEquals(7, velocity.x, 0.1);
        assertEquals(7, velocity.y, 0.1);
    }


    /**
     * Test method for {@link flocking.util.Velocity#equals(java.lang.Object)}.
     */
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        assertTrue(velocity.equals(velocity));
        assertFalse(velocity.equals(new String("Not velocity")));
        assertFalse(velocity.equals(null));
        assertFalse(velocity.equals(new Velocity(new Angle(8.55F), 3.0F)));
        assertTrue(velocity.equals(new Velocity(new Angle(45F), 5F)));
    }


    /**
     * Test method for {@link flocking.util.Velocity#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("{45°, 5} : [3.5, 3.5]", velocity.toString());
    }

}
