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
    public void testAddFromVector() {
        vector.add(new Vector2D(-10.52f, 1.2f));
        assertEquals(0, vector.x);
        assertEquals(11.72, vector.y, 0.01);
    }


    @Test
    public void testAddFromFloat() {
        vector.add(3.48f, 2.48f);
        assertEquals(14, vector.x);
        assertEquals(13, vector.y);
    }


    @Test
    public void testAddStatic() {
        Vector2D sum = Vector2D.add(new Vector2D(3.5f, 2.5f), new Vector2D(2.5f,
            3.5f));
        assertTrue(sum.equals(new Vector2D(6, 6)));
    }


    @Test
    public void testSubtractFromVector() {
        vector.subtract(new Vector2D(10.52f, 0.52f));
        assertEquals(0, vector.x);
        assertEquals(10, vector.y);
    }


    @Test
    public void testSubtractFromFloat() {
        vector.subtract(3.12f, 2.12f);
        assertEquals(7.4f, vector.x, 0.01);
        assertEquals(8.4f, vector.y, 0.01);
    }


    @Test
    public void testSubtractStatic() {
        Vector2D diff = Vector2D.subtract(new Vector2D(3.5f, 2.5f),
            new Vector2D(2.5f, 3.5f));
        assertTrue(diff.equals(new Vector2D(1, -1)));
    }


    /**
     * Test method for {@link flocking.util.Vector2D#multiply(float)}.
     */
    @Test
    public void testMultiply() {
        vector.multiply((1.0f / 3.0f));
        assertEquals(3.5, vector.x, 0.01);
        assertEquals(3.5, vector.y, 0.01);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#divide(float)}.
     */
    @Test
    public void testDivide() {
        vector.divide(2.4f);
        assertEquals(4.38f, vector.x, 0.01);
        assertEquals(4.38f, vector.y, 0.01);
    }


    @Test
    public void testDot() {
        assertEquals(85.21, vector.dot(new Vector2D(3.5f, 4.6f)), 0.01);
    }


    @Test
    public void testDotStatic() {
        assertEquals(16.75, Vector2D.dot(new Vector2D(1.5f, 2.5f), new Vector2D(
            3.5f, 4.6f)), 0.01);
    }


    @Test
    public void testNormalize() {
        vector.normalize();
        assertEquals(0.7, vector.x, 0.01);
        assertEquals(0.7, vector.y, 0.01);
    }


    /**
     * Test method for {@link flocking.util.Vector2D#calcMagnitude()}.
     */
    @Test
    public void testCalcMagnitude() {
        assertEquals(14.87, vector.calcMagnitude(), 0.01);
    }


    @Test
    public void testSetMagnitude() {
        vector.setMagnitude(2.5f);
        assertEquals(2.5, vector.calcMagnitude(), 0.1);
    }


// @Test
    public void testLimit() {
        vector.limit(13.5f);
        assertEquals(10.52f, vector.x, 0.01);
        assertEquals(10.52f, vector.y, 0.01);
        vector.limit(1);
        assertEquals(0.7, vector.x, 0.01);
        assertEquals(0.7, vector.y, 0.01);
    }


    @Test
    public void testRandom2D() {
        Vector2D r = Vector2D.random2D();
        assertEquals(1, Math.sqrt(r.x * r.x + r.y * r.y), 0.001);
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
