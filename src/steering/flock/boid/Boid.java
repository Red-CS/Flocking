package steering.flock.boid;

import steering.Styles;
import steering.Window;
import steering.flock.Flock;
import steering.util.Angle;
import steering.util.Perspective;
import steering.util.Vector2D;

/**
 * Boid Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Boid {

    private Perspective perspective;
    private Angle heading;

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    /**
     * Boid Constructor
     * <br>
     * Creates a Boid with the specified Perspective with a random
     * position on the screen
     * 
     * @param view Boid Perspective
     */
    public Boid(Perspective view) {

        this(new Vector2D((float) (Math.random() * Window.WINDOW_WIDTH),
            (float) (Math.random() * Window.WINDOW_HEIGHT)), view);
    }


    /**
     * Boid Constructor
     * Creates a Boid with the specified position and perspective
     * 
     * @param pos Boid Vector2D position
     * @param view Boid Perspective
     */
    public Boid(Vector2D pos, Perspective view) {
        position = pos;
        perspective = view;
        heading = new Angle((float) (Math.random() * 360));

        velocity = Vector2D.random2D();
        acceleration = Vector2D.random2D();
    }


    public Vector2D getPosition() {
        return position;
    }


    public void setPosition(Vector2D position) {
        this.position = position;
    }


    public Vector2D getVelocity() {
        return velocity;
    }


    public void setVelocity(Vector2D velocity) {
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


    public Angle getDirection() {
        return heading;
    }


    public void setDirection(Angle heading) {
        this.heading = heading;
    }


    /**
     * Updates the Boid's position depending on it's surroundings
     */
    public void flock(Flock<Boid> flock) {

        cohere(flock);
        align(flock);
        separate(flock);

        velocity.add(acceleration);
        velocity.limit(4);
        position.add(velocity);
        acceleration.multiply(0);
        heading.setAngle((float) (Math.toDegrees(Math.atan2(velocity.y,
            velocity.x))));
        fixOffscreen();
    }


    /**
     * Steer to avoid crowding local flockmates
     */
    private void separate(Flock<Boid> flock) {
        // Average components
        Vector2D steeringForce = new Vector2D(0, 0);
        int numBoids = 0;

        // Iterate through each member of the flock
        for (Boid boid : flock) {
            if (boid != this && boid.isVisibleTo(this)) {
                Vector2D posDiff = Vector2D.subtract(position, boid.position);
                float mag = posDiff.calcMagnitude();
                if (mag == 0) {
                    return;
                }
                posDiff.divide(mag * mag);
                steeringForce.add(posDiff);
                numBoids++;
            }
        }

        if (numBoids == 0) {
            return;
        }

        steeringForce.divide(numBoids);

        // Apply Steering Force
        steeringForce.setMagnitude(4f);
        steeringForce.subtract(velocity);
        steeringForce.limit(0.275f);

        // Add force to Acceleration
        acceleration.add(steeringForce);

    }


    /**
     * Steer towards the average heading of local flockmates
     */
    private void align(Flock<Boid> flock) {

        // Average components
        Vector2D steeringForce = new Vector2D(0, 0);
        int numBoids = 0;

        // Iterate through each member of the flock
        for (Boid boid : flock) {
            if (boid != this && boid.isVisibleTo(this)) {
                steeringForce.add(boid.getVelocity());
                numBoids++;
            }
        }

        if (numBoids == 0) {
            return;
        }

        steeringForce.divide(numBoids);

        // Apply Steering Force
        steeringForce.setMagnitude(4);
        steeringForce.subtract(velocity);
        steeringForce.limit(0.2f);

        // Add force to Acceleration
        acceleration.add(steeringForce);
    }


    /**
     * Steer to move towards the average position of local flockmates
     */
    private void cohere(Flock<Boid> flock) {
        // Average components
        Vector2D steeringForce = new Vector2D(0, 0);
        int numBoids = 0;

        // Iterate through each member of the flock
        for (Boid boid : flock) {
            if (boid != this && boid.isVisibleTo(this)) {
                steeringForce.add(boid.getPosition());
                numBoids++;
            }
        }

        if (numBoids == 0) {
            return;
        }

        steeringForce.divide(numBoids);

        // Apply Steering Force

        steeringForce.subtract(position);
        steeringForce.setMagnitude(4f);
        steeringForce.subtract(velocity);
        steeringForce.limit(.2f);

        // Add force to Acceleration
        acceleration.add(steeringForce);

    }


    /**
     * Fixes the Boid's position if it goes ofscreen
     */
    private void fixOffscreen() {

        // Fix possible X offset
        if (position.x < 0) {
            position.x = Window.WINDOW_WIDTH;
        }
        else if (position.x > Window.WINDOW_WIDTH) {
            position.x = 2;
        }

        // Fix possible Y offset
        if (position.y < 0) {
            position.y = Window.WINDOW_HEIGHT;
        }
        else if (position.y > Window.WINDOW_HEIGHT) {
            position.y = 2;
        }

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
        return objBoid.position.equals(position)
            && objBoid.velocity.equals(velocity)
            && objBoid.acceleration.equals(acceleration)
            && objBoid.heading.equals(heading)
            && objBoid.perspective.equals(perspective);
    }


    /**
     * Returns a String representation of a Boid
     * 
     * @return a String representation of a Boid
     */
    @Override
    public String toString() {
        return "[" + Styles.df.format(position.x) + ", " + Styles.df.format(
            position.y) + ", " + heading + "]";
    }
}
