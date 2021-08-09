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

    private static final float MOVEMENT_SPEED = 4.5F;
    private static final float PERSPECTION_RADIUS = 50;
    private static final float PERSPECTION_ANGLE = 180;

    private Perspective perspective;
    private Angle heading;

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    public Boid(Perspective view) {
        perspective = view;
        heading = new Angle((float) (Math.random() * 360));

        float x = (float) (Math.random() * Window.WINDOW_WIDTH);
        float y = (float) (Math.random() * Window.WINDOW_WIDTH);
        position = new Vector2D(x, y);
        velocity = Vector2D.random2D();
        acceleration = Vector2D.random2D();
    }


    /**
     * Boid Constructor
     * Creates a Boid with the specified position, velocity, and perspective
     * 
     * @param pos Boid Vector2D position
     * @param vel Boid Velocity
     * @param view Boid Perspective
     */
    public Boid(Vector2D pos, Perspective view) {
        position = pos;
        perspective = view;
        heading = new Angle((float) (Math.random() * 360));

        velocity = Vector2D.random2D();
        acceleration = Vector2D.random2D();
        System.out.println(velocity);
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


    public void setDirection(Angle newDir) {
        heading = newDir;
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
     * Updates the Boid's position depending on it's surroundings
     */
    public void update(Flock<Boid> flock) {

        align(flock);

        position.displayAdd(velocity);
        velocity.displayAdd(acceleration);
        velocity.limit(4);
        acceleration.scale(0);
        heading.setAngle((float) (Math.toDegrees(Math.atan2(velocity.y,
            velocity.x))));
        System.out.println(heading);
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

        // Alignment
        Vector2D steeringForce = new Vector2D(xAvg, yAvg);
        steeringForce.displaySubtract(velocity);
        steeringForce.normalize();
// velocity = steeringForce;
// steeringForce.scale(steeringForce.calcMagnitude() / 4);
// System.out.println(steeringForce);

        acceleration.displayAdd(steeringForce);
// heading.setAngle((float) Math.toDegrees(Math.atan(steeringForce.y
// / steeringForce.x)));
        System.out.println(heading.toDegrees());
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
            position.y) + ", " + "velocity.getDirection().toString()" + "]";
    }
}
