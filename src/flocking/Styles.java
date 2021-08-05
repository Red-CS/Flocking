package flocking;

import java.awt.Color;
import java.text.DecimalFormat;

/**
 * Colors and Styles for the Flocking Simulation
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public @interface Styles {

    Color bg = new Color(0x6a717d);
    DecimalFormat df = new DecimalFormat("#.#");
}
