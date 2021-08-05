package flocking.boid;

import flocking.Styles;
import flocking.Window;
import flocking.util.Angle;
import flocking.util.Perspective;
import flocking.util.Vector2D;
import flocking.util.Velocity;

/**
 * Boid Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Boid {

    private static final float MOVEMENT_SPEED = 4.5F;
    private static final float PERSPECTION_RADIUS = 50;
    private static final float PERSPECTION_ANGLE = 180;

    private Vector2D position;
    private Velocity velocity;
    private Perspective perspective;

    /**
     * Boid Constructor
     * Creates a Boid with a random position within the screen
     */
    public Boid() {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), new Velocity(
                new Angle((float) (Math.random() * 360)), MOVEMENT_SPEED),
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
    public Boid(Vector2D pos, Velocity vel, Perspective view) {
        position = pos;
        velocity = vel;
        setPerspective(view);
    }


    public Vector2D getPosition() {
        return position;
    }


    public void setPosition(Vector2D position) {
        this.position = position;
    }


    public Velocity getVelocity() {
        return velocity;
    }


    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }


    /**
     * @return the perspective
     */
    public Perspective getPerspective() {
        return perspective;
    }


    /**
     * @param perspective the perspective to set
     */
    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
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
        position.x += Math.cos(velocity.getDirection().toRadians())
            * MOVEMENT_SPEED;
        position.y -= Math.sin(velocity.getDirection().toRadians())
            * MOVEMENT_SPEED;
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
     * Tests whether or not two Boid objects are equal
     * 
     * @param obj Object to compare with
     * @return {@code true} if they the same, {@code false} if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Boid objBoid = (Boid) obj;
        return objBoid.position.equals(position) && objBoid.velocity.equals(
            velocity) && objBoid.perspective.equals(perspective);
    }


    /**
     * Returns a String representation of a Boid
     * 
     * @return a String representation of a Boid
     */
    @Override
    public String toString() {
        return "[" + Styles.df.format(position.x) + ", " + Styles.df.format(
            position.y) + ", " + velocity.getDirection().toString() + "]";
    }
}
