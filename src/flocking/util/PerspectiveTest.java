package flocking.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for the Perspective Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class PerspectiveTest {

    private Perspective view;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        view = new Perspective(new Angle(45.0f), 20);
    }


    /**
     * Test method for {@link flocking.util.Perspective#getTheta()}.
     */
    @Test
    public void testGetTheta() {
        assertEquals(new Angle(45.0f), view.getTheta());
    }


    /**
     * Test method for {@link flocking.util.Perspective#getRadius()}.
     */
    @Test
    public void testGetRadius() {
        assertEquals(20.0, view.getRadius());
    }


    /**
     * Test method for {@link Perspective#contains(Vector2D)}.
     */
    @Test
    public void testContains() {

        float x;
        float y;

        // Test 1st quadrant with angle 60 deg
        x = (float) (3.0 * Math.cos(2.0 * Math.PI / 3.0));
        y = (float) (3.0 * Math.sin(2.0 * Math.PI / 3.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test 2nd quadrant with angle 135 deg
        x = (float) (3.0 * Math.cos(3.0 * Math.PI / 4.0));
        y = (float) (3.0 * Math.sin(3.0 * Math.PI / 4.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test 3rd quadrant with angle 225 deg
        x = (float) (3.0 * Math.cos(5.0 * Math.PI / 4.0));
        y = (float) (3.0 * Math.sin(5.0 * Math.PI / 4.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test 4th quadrant with angle 300 deg
        x = (float) (3.0 * Math.cos(5.0 * Math.PI / 3.0));
        y = (float) (3.0 * Math.sin(5.0 * Math.PI / 3.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test outside of radius
        x = (float) (25.0 * Math.cos(Math.PI / 6.0));
        y = (float) (25.0 * Math.sin(Math.PI / 6.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test with circular Perspective
        Perspective circular = new Perspective(new Angle(180f), 5.0f);

        // Test 1st quadrant with 45 deg
        x = (float) (Math.cos(Math.PI / 4.0));
        y = (float) (Math.sin(Math.PI / 4.0));
        assertTrue(circular.contains(new Vector2D(x, y)));

        // Test 2nd quadrant with 135 deg
        x = (float) (Math.cos(3.0 * Math.PI / 4.0));
        y = (float) (Math.sin(3.0 * Math.PI / 4.0));
        assertTrue(circular.contains(new Vector2D(x, y)));

        // Test 3rd quadrant with 225 deg
        x = (float) (Math.cos(5.0 * Math.PI / 4.0));
        y = (float) (Math.sin(5.0 * Math.PI / 4.0));
        assertTrue(circular.contains(new Vector2D(x, y)));

        // Test 4th quadrant with 315 deg
        x = (float) (Math.cos(7.0 * Math.PI / 4.0));
        y = (float) (Math.sin(7.0 * Math.PI / 4.0));
        assertTrue(circular.contains(new Vector2D(x, y)));

        // Test outside of radius
        x = (float) (25.0 * Math.cos(Math.PI / 6.0));
        y = (float) (25.0 * Math.sin(Math.PI / 6.0));
        assertFalse(view.contains(new Vector2D(x, y)));

        // Test inside of Perspective (standard & circular)
        x = (float) (Math.cos(Math.PI / 6.0));
        y = (float) (Math.sin(Math.PI / 6.0));
        assertTrue(view.contains(new Vector2D(x, y)));
        assertTrue(circular.contains(new Vector2D(x, y)));
        
        assertFalse(new Perspective)

    }

}
