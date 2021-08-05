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
public class AngleTest {

    private Angle angle;

    @BeforeEach
    public void setUp() {
        angle = new Angle(45.0F);
    }


    /**
     * Test method for {@link flocking.util.Angle#toDegrees()}.
     */
    @Test
    public void testToDegrees() {
        assertEquals(45.0, angle.toDegrees());
    }


    /**
     * Test method for {@link flocking.util.Angle#setAngle(float)}.
     */
    @Test
    public void testSetAngle() {
        angle.setAngle(135.0F);
        assertEquals(135.0, angle.toDegrees());
    }


    /**
     * Test method for {@link flocking.util.Angle#toRadians()}.
     */
    @Test
    public void testToRadians() {
        assertEquals((float) (Math.PI / 4), angle.toRadians(), 0.01);
    }


    /**
     * Test method for {@link flocking.util.Angle#equals(java.lang.Object)}.
     */
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEqualsObject() {
        assertTrue(angle.equals(angle));
        assertFalse(angle.equals(new String("Not a angle")));
        assertFalse(angle.equals(null));
        assertFalse(angle.equals(new Angle(123.4F)));
        assertTrue(angle.equals(new Angle(45.0F)));
    }


    /**
     * Test method for {@link flocking.util.Angle#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("45°", angle.toString());
    }

}
