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
     * Creates a Boid with random attributes within the screen
     */
    public Boid() {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), new Velocity(
                new Angle((float) (Math.random() * 360)), MOVEMENT_SPEED),
            new Perspective(new Angle(PERSPECTION_ANGLE), PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random perspective and velocity, but defined
     * position, within the screen
     */
    public Boid(Vector2D pos) {
        this(pos, new Velocity(new Angle((float) (Math.random() * 360)),
            MOVEMENT_SPEED), new Perspective(new Angle(PERSPECTION_ANGLE),
                PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random position and perspective, but defined
     * velocity, within the screen
     */
    public Boid(Velocity vel) {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), vel,
            new Perspective(new Angle(PERSPECTION_ANGLE), PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random position and velocity, but defined
     * perspective, within the screen
     */
    public Boid(Perspective view) {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), new Velocity(
                new Angle((float) (Math.random() * 360)), MOVEMENT_SPEED),
            view);
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random position, but defined velocity and
     * perspective, within the screen
     */
    public Boid(Velocity vel, Perspective view) {
        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), vel, view);
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random velocity, but defined position and
     * perspective, within the screen
     */
    public Boid(Vector2D pos, Perspective view) {
        this(pos, new Velocity(new Angle((float) (Math.random() * 360)),
            MOVEMENT_SPEED), view);
    }


    /**
     * Boid Constructor
     * Creates a Boid with a random Perspective, but defined Position and
     * Velocity, within the screen
     */
    public Boid(Vector2D pos, Velocity vel) {
        this(pos, vel, new Perspective(new Angle(PERSPECTION_ANGLE),
            PERSPECTION_RADIUS));
    }


    /**
     * Boid Constructor
     * Creates a Boid with the specified position, velocity, and perspective
     * 
     * @param pos Boid Vector2D position
     * @param vel Boid Velocity
     * @param view Boid Perspective
     */
    public Boid(Vector2D pos, Velocity vel, Perspective view) {
        position = pos;
        velocity = vel;
        perspective = view;
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
    public void update(Flock<Boid> flock) {

        align(flock);

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

        // Average components
        float xAvg = 0f;
        float yAvg = 0f;
        int numBoids = 0;

        // Iterate through each member of the flock
        for (Boid boid : flock) {
            if (boid != this && boid.isVisibleTo(this)) {
                xAvg += boid.getPosition().x;
                yAvg += boid.getPosition().y;
                numBoids++;
            }
        }

        if (numBoids == 0) {
            return;
        }

        xAvg /= numBoids;
        yAvg /= numBoids;

// velocity.setDirection(new Angle(xAvg, yAvg));

        // Alignment
        Velocity steeringForce = new Velocity(xAvg, yAvg);
        steeringForce.subtract(velocity);
        velocity.setDirection(steeringForce.getDirection());
// velocity = steeringForce;
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
     * Returns whether or not a Boid is visible to the current Boid {@code boid}
     * 
     * @param boid {@code this} Boid
     * @return {@code true} if the Boid calling this method is in the
     * perspective of {@code boid}, {@code false} otherwise.
     */
    private boolean isVisibleTo(Boid boid) {
        Vector2D compPos = boid.getPosition();
        return boid.getPerspective().contains(new Vector2D(position.x
            - compPos.x, position.y - compPos.y));
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
