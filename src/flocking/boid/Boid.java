package flocking.boid;

import flocking.Styles;
import flocking.Window;
import flocking.util.Angle;
import flocking.util.Perspective;
import flocking.util.Vector2D;

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

    private Vector2D position;
    private Angle direction;
    private Perspective perspective;

    /**
     * Boid Constructor
     * Creates a Boid with a random position within the screen
     */
    public Boid() {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), new Angle(Math
                .random() * 360), new Perspective(new Angle(PERSPECTION_ANGLE),
                    PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with the specified position
     * 
     * @param x Boid x position
     * @param y Boid y position
     * @param dir Boid direction
     */
    public Boid(Vector2D pos, Angle dir, Perspective view) {
        position = pos;
        direction = dir;
        perspective = view;
    }


    public Vector2D getPosition() {
        return position;
    }


    public void setPosition(Vector2D newPos) {
        position = newPos;
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
     * Fixes the Boid's position if it goes ofscreen
     */
    private void fixOffscreen() {

        // Fix possible X offset
        if (position.x < 0) {
            position.x = Window.WINDOW_WIDTH;
        }
        else if (position.x >= Window.WINDOW_WIDTH) {
            position.x = 0;
        }

        // Fix possible Y offset
        if (position.y < 0) {
            position.y = Window.WINDOW_HEIGHT;
        }
        else if (position.y >= Window.WINDOW_HEIGHT) {
            position.y = 0;
        }

    }


    /**
     * Updates the Boid's position depending on it's surroundings
     */
    public void update() {
        position.x += Math.cos(direction.toRadians()) * MOVEMENT_SPEED;
        position.y -= Math.sin(direction.toRadians()) * MOVEMENT_SPEED;
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
        return "[" + Styles.df.format(position.x) + ", " + Styles.df.format(
            position.y) + ", " + direction + "]";
    }
}
