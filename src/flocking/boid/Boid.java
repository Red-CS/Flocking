package flocking.boid;

import flocking.Styles;
import flocking.Window;
import flocking.util.Angle;
import flocking.util.Perspective;

/**
 * Boid Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Boid {

    private final int MOVEMENT_SPEED = 3;
    private static final double PERSPECTION_RADIUS = 50;
    private static final double PERSPECTION_ANGLE = 180;

    private double xPos;
    private double yPos;
    private Angle direction;
    private Perspective perspective;

    /**
     * Boid Constructor
     * Creates a Boid with a random position within the screen
     */
    public Boid() {
        this(Math.random() * Window.WINDOW_WIDTH, Math.random()
            * Window.WINDOW_HEIGHT, new Angle(Math.random() * 360),
            new Perspective(new Angle(PERSPECTION_ANGLE), PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with the specified position
     * 
     * @param x Boid x position
     * @param y Boid y position
     * @param dir Boid direction
     */
    public Boid(double x, double y, Angle dir, Perspective view) {
        xPos = x;
        yPos = y;
        direction = dir;
        perspective = view;
    }


    /**
     * Returns the X position of the Boid
     * 
     * @return the X position of the Boid
     */
    public double getX() {
        return xPos;
    }


    /**
     * Returns the Y position of the Boid
     * 
     * @return the Y position of the Boid
     */
    public double getY() {
        return yPos;
    }


    /**
     * Sets the X position of the Boid
     * 
     * @param x new X position of the Boid
     */
    public void setX(double x) {
        xPos = x;
    }


    /**
     * Returns the Direction of the Boid
     * 
     * @return the Direction of the Boid
     */
    public Angle getDirection() {
        return direction;
    }


    /**
     * Sets the new Direction of the Boid
     * 
     * @param newDirection new Direction of the Boid
     */
    public void setDirection(Angle newDirection) {
        direction = newDirection;
    }


    /**
     * Sets the Y position of the Boid
     * 
     * @param y new Y position of the Boid
     */
    public void setY(double y) {
        yPos = y;
    }


    /**
     * Fixes the Boid's position if it goes ofscreen
     */
    private void fixOffscreen() {

        // Fix possible X offset
        if (xPos < 0) {
            xPos = Window.WINDOW_WIDTH;
        }
        else if (xPos >= Window.WINDOW_WIDTH) {
            xPos = 0;
        }

        // Fix possible Y offset
        if (yPos < 0) {
            yPos = Window.WINDOW_HEIGHT;
        }
        else if (yPos >= Window.WINDOW_HEIGHT) {
            yPos = 0;
        }

    }


    /**
     * Updates the Boid's position depending on it's surroundings
     */
    public void update() {
        xPos += Math.cos(direction.toRadians()) * MOVEMENT_SPEED;
        yPos -= Math.sin(direction.toRadians()) * MOVEMENT_SPEED;
        fixOffscreen();
    }


    /**
     * Steer to avoid crowding local flockmates
     */
    private void separate() {

    }


    /**
     * Steer towards the average heading of local flockmates
     */
    private void align(Flock<Boid> flock) {

        // Iterate through each member of the flock
        // -- If that Boid is in the perspective of this Boid,
        // -- include its velocity vector in calculating the average
        // Calculate the steering velocity vector

    }


    /**
     * Steer to move towards the average position of local flockmates
     */
    private void cohere() {

    }


    /**
     * Returns a String representation of a Boid
     * 
     * @return a String representation of a Boid
     */
    @Override
    public String toString() {
        return "[" + Styles.df.format(xPos) + ", " + Styles.df.format(yPos)
            + ", " + direction + "]";
    }
}
