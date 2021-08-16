package steering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import steering.flock.boid.Boid;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 16, 2021
 */
@SuppressWarnings("serial")
public class Settings extends JFrame {

    public static final int CP_WIDTH = 600;
    public static final int CP_HEIGHT = 400;

    /**
     * Settings Constructor
     */
    public Settings() {
        setTitle("Settings");
        setSize(CP_WIDTH, CP_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        this.add(new BoidPerspectiveView());
        this.add(new BoidSettingsView());
        setVisible(true);
    }

    /**
     * The left side of the settings.
     * Shows and sets the Boids Perspective
     * 
     * @author Red Williams <red.devcs@gmail.com>
     * @since Aug 16, 2021
     */
    private class BoidPerspectiveView extends JPanel {

        private BoidPerspectiveView() {
            setBackground(new Color(0xFFFFFF));
        }


        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g2d);

            // Set up Image Buffer
            BufferedImage imageBuffer = new BufferedImage(getWidth(),
                getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics imageG2 = imageBuffer.createGraphics();

            // Set Color and Stroke
            imageG2.setColor(new Color(0x2F2F2F));
            ((Graphics2D) imageG2).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) imageG2).setStroke(new BasicStroke((float) 4.5));

            // Draw Boid Diagram
            int x = CP_WIDTH / 4;
            int y = (int) (CP_HEIGHT / 3.0);
            int[] xCoords = { x + Boid.HALF_HEIGHT, x - 8 * Boid.HALF_HEIGHT, x
                - 8 * Boid.HALF_HEIGHT };
            int[] yCoords = { y, y - 4 * Boid.HALF_WIDTH, y + 4
                * Boid.HALF_WIDTH };

            imageG2.drawPolygon(Display.buildPolygon(x, y, xCoords, yCoords,
                90));

            imageG2.drawArc(x - 100, y - 50, 200, 200, 90, 60);

            // Draw Image Buffer
            g2d.drawImage(imageBuffer, 0, 0, this);

        }
    }


    private class BoidSettingsView extends JPanel {
        private BoidSettingsView() {
            setBackground(Color.BLUE);
        }
    }

}
