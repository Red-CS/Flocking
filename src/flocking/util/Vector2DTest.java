package flocking.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for the Vector2D Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 5, 2021
 */
public class Vector2DTest {

    private Vector2D vector;

    /**
     * Runs before each test
     */
    @BeforeEach
    public void setUp() {
        vector = new Vector2D(10.52F, 10.52F);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#setVector(float, float)}.
     */
    @Test
    public void testSetVectorFromFloat() {
        Vector2D vectorF = new Vector2D(20F, 20F);
        vector.setVector(vectorF.x, vectorF.y);
        assertEquals(20F, vector.x);
        assertEquals(20F, vector.y);
    }


    /**
     * Test method for
     * {@link flocking.util.Vector2D#setVector(flocking.util.Vector2D)}.
     */
    @Test
    public void testSetVectorVector2D() {
        Vector2D vectorV = new Vector2D(20F, 20F);
        vector.setVector(vectorV);
        assertEquals(20F, vector.x);
        assertEquals(20F, vector.y);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#add(Vector2D)}.
     */
    @Test
    public void testAdd() {
        vector.add(new Vector2D(-10.52f, 1.2f));
        assertEquals(0, vector.x);
        assertEquals(11.72, vector.y, 0.01);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#scale(float)}.
     */
    @Test
    public void testScale() {
        vector.scale((float) (1.0 / 3.0));
        assertEquals(3.5, vector.x, 0.01);
        assertEquals(3.5, vector.y, 0.01);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#calcMagnitude()}.
     */
    @Test
    public void testCalcMagnitude() {
        assertEquals(14.87, vector.calcMagnitude(), 0.01);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#equals()}.
     */
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        assertTrue(vector.equals(vector));
        assertFalse(vector.equals(new String("Not a vector")));
        assertFalse(vector.equals(null));
        assertFalse(vector.equals(new Vector2D(10.55F, 10.55F)));
        assertTrue(vector.equals(new Vector2D(10.52F, 10.52F)));
    }


    /**
     * Test method for {@link flocking.util.Vector2D#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("[10.5, 10.5]", vector.toString());
    }

}
