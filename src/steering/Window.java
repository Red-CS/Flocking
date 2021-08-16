package steering;

import javax.swing.JFrame;

/**
 * Main Window for Flocking Simulation
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
@SuppressWarnings("serial")
public class Window extends JFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public Window() {

        setTitle("Flocking Simulation");
        setContentPane(new Display(WINDOW_WIDTH, WINDOW_HEIGHT));
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    /**
     * Main method/entry point
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Window();
    }

    /*
               __
          (___()'`;
          /,    /`
          \\"--\\     -fringe
    
     */

}
