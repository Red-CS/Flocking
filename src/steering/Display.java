package steering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;
import steering.flock.Flock;
import steering.flock.boid.Boid;
import steering.util.Angle;
import steering.util.Perspective;

/**
 * Display Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 16, 2021
 */
@SuppressWarnings("serial")
public class Display extends JPanel implements KeyListener {
    private Flock<Boid> flock;
    private Timer timer;

    public static final int FLOCK_SIZE = 50;
    private final int TICK_RATE = 10;

    private final boolean shouldDrawViews = false;

    /**
     * Display Constructor
     * 
     * @param width Width of the screen
     * @param height Height of the screen
     */
    public Display(int width, int height) {

        setBackground(Styles.bg);
        setSize(width, height);
        setFocusable(true);
        requestFocus();

        addKeyListener(this);

        // Instantiate Flock
        Boid[] boids = new Boid[FLOCK_SIZE];
        for (int i = 0; i < boids.length; i++) {
            boids[i] = new Boid(new Perspective(new Angle(180f), 50f));
        }

        flock = new Flock<Boid>(boids);

        // Start Animation Timer
        timer = new Timer(TICK_RATE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Updates each Boid accordingly
                for (Boid boid : flock) {
                    boid.flock(flock);
                }

                repaint();
            }
        });

        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        // Set up Image Buffer
        BufferedImage imageBuffer = new BufferedImage(getWidth(), getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics imageG2 = imageBuffer.createGraphics();

        // Set Color and Stroke
        imageG2.setColor(Color.WHITE);
        ((Graphics2D) imageG2).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) imageG2).setStroke(new BasicStroke((float) 1.5));

        // Draw Boids
        for (Boid boid : flock) {

            // Get Polygon info
            int x = (int) boid.getPosition().x;
            int y = Window.WINDOW_HEIGHT - (int) boid.getPosition().y;
            int[] xCoords = { x + Boid.HALF_HEIGHT, x - Boid.HALF_HEIGHT, x
                - Boid.HALF_HEIGHT };
            int[] yCoords = { y, y - Boid.HALF_WIDTH, y + Boid.HALF_WIDTH };

            // Draw Perspective
            if (shouldDrawViews) {
                boid.renderPerspective(imageG2, x, y);
            }

            // Draw Boid
            boid.renderBoid(imageG2, x, y, xCoords, yCoords);

        }

        // Draw Image Buffer
        g2d.drawImage(imageBuffer, 0, 0, this);

        // Dispose
        g2d.dispose();
    }


    /**
     * Builds a polygon from a set of points, rotated around a point, at the
     * specified rotation angle.
     * 
     * @param centerX the int center x coordinate around which to rotate
     * @param centerY the int center y coordinate around which to rotate
     * @param xp the int[] of x points which make up our polygon points. This
     *     array is parallel to the yp array where each index in this array
     *     corresponds to the same index in the yp array.
     * @param yp the int[] of y points which make up our polygon points. This
     *     array is parallel to the xp array where each index in this array
     *     corresponds to the same index in the xp array.
     * @param rotationAngle the double angle in which to rotate the provided
     *     coordinates (specified in degrees).
     * @return a Polygon of the provided coordinates rotated around the center
     * point
     * at the specified angle.
     * @throws IllegalArgumentException when the provided x points array is not
     *     the
     *     same length as the provided y points array
     */
    public static Polygon buildPolygon(int centerX, int centerY, int[] xp,
        int[] yp, double rotationAngle)
        throws IllegalArgumentException {
        // copy the arrays so that we dont manipulate the originals, that way we
        // can
        // reuse them if necessary
        int[] xpoints = Arrays.copyOf(xp, xp.length);
        int[] ypoints = Arrays.copyOf(yp, yp.length);
        if (xpoints.length != ypoints.length) {
            throw new IllegalArgumentException(
                "The provided x points are not the same length as the provided y points.");
        }

        // create a list of Point2D pairs
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        for (int i = 0; i < ypoints.length; i++) {
            list.add(new Point2D.Double(xpoints[i], ypoints[i]));
        }

        // create an array which will hold the rotated points
        Point2D[] rotatedPoints = new Point2D[list.size()];

        // rotate the points
        AffineTransform transform = AffineTransform.getRotateInstance(Math
            .toRadians(-rotationAngle), centerX, centerY);
        transform.transform(list.toArray(new Point2D[0]), 0, rotatedPoints, 0,
            rotatedPoints.length);

        // build the polygon from the rotated points and return it
        int[] ixp = new int[list.size()];
        int[] iyp = new int[list.size()];
        for (int i = 0; i < ixp.length; i++) {
            ixp[i] = (int) rotatedPoints[i].getX();
            iyp[i] = (int) rotatedPoints[i].getY();
        }

        return new Polygon(ixp, iyp, ixp.length);
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {}


    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        // Exit
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);

            case KeyEvent.VK_S:
                toggleSettings();
                break;

            case KeyEvent.VK_F:
                toggleFullscreen();
                break;
        }

    }


    private void toggleFullscreen() {}


    private void toggleSettings() {}

}
