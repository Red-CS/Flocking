package flocking.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        view = new Perspective(new Angle(45.0), 20);
    }


    /**
     * Test method for {@link flocking.util.Perspective#getTheta()}.
     */
    @Test
    public void testGetTheta() {
        assertEquals(45.0, view.getTheta());
    }


    /**
     * Test method for {@link flocking.util.Perspective#getRadius()}.
     */
    @Test
    public void testgetRadius() {
        assertEquals(20.0, view.getRadius());
    }

}
