package steering.flock.boid;

import java.awt.Color;
import java.awt.Graphics;
import steering.Display;
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

    protected final Color boidColor = new Color(0x44F2F2F2, true);
    protected final Color boidOutline = new Color(0xFFFFFF);

    public static final int HALF_WIDTH = 8;
    public static final int HALF_HEIGHT = 12;

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
    protected void separate(Flock<Boid> flock) {
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
    protected void align(Flock<Boid> flock) {

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
    protected void cohere(Flock<Boid> flock) {
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
     * 
     * @implNote We know it's fully offscreen in the x direction when the x is
     * added to half the Boid's height - the largest component of the Boid. The
     * same idea goes for y.
     * This way, Boids won't appear/disappear instantly, but will wait until the
     * entire Boid is not visible before moving its position
     */
    protected void fixOffscreen() {

        // Fix possible X offset
        if (position.x + HALF_HEIGHT < 0) {
            position.x = Window.WINDOW_WIDTH;
        }
        else if (position.x - HALF_HEIGHT > Window.WINDOW_WIDTH) {
            position.x = -HALF_HEIGHT;
        }

        // Fix possible Y offset
        if (position.y + HALF_HEIGHT < 0) {
            position.y = Window.WINDOW_HEIGHT;
        }
        else if (position.y - HALF_HEIGHT > Window.WINDOW_HEIGHT) {
            position.y = -HALF_HEIGHT;
        }

    }


    /**
     * Returns whether or not a Boid is visible to the current Boid {@code boid}
     * 
     * @param boid {@code this} Boid
     * @return {@code true} if the Boid calling this method is in the
     * perspective of {@code boid}, {@code false} otherwise.
     */
    protected boolean isVisibleTo(Boid boid) {
        Vector2D compPos = boid.getPosition();
        return boid.getPerspective().contains(new Vector2D(position.x
            - compPos.x, position.y - compPos.y));
    }


    /**
     * Draws the Boid on the screen
     * 
     * @param imageG2 Graphics context
     * @param x Boid center X
     * @param y Boid center Y
     * @param xCoords Polygon X coords
     * @param yCoords Polygon Y coords
     */
    public void renderBoid(Graphics imageG2, int x, int y, int[] xCoords,
        int[] yCoords) {

        imageG2.setColor(boidColor);
        imageG2.fillPolygon(Display.buildPolygon(x, y, xCoords, yCoords, heading
            .toDegrees()));
        imageG2.setColor(boidOutline);
        imageG2.drawPolygon(Display.buildPolygon(x, y, xCoords, yCoords, heading
            .toDegrees()));
    }


    /**
     * Draws the Boid's Perspective on the screen
     * 
     * @param imageG2 Graphics context
     * @param x Center X
     * @param y Center Y
     */
    public void renderPerspective(Graphics imageG2, int x, int y) {

        //@formatter:off

        int halfR = (int) perspective.getRadius() / 2;

        // Draw left arc
        imageG2.drawArc(x - halfR, y - halfR,
            (int) perspective.getRadius(), (int) perspective.getRadius(),
            (int) heading.toDegrees(), (int) perspective.getTheta().toDegrees());

        // Draw right arc
        imageG2.drawArc(x - halfR, y - halfR,
            (int) perspective.getRadius(), (int) perspective.getRadius(),
            (int) heading.toDegrees(), (int) -perspective.getTheta().toDegrees());

        imageG2.setColor(boidColor);
      
        // Draw left line
        imageG2.drawLine(x, y,
            x + (int) (Math.cos(heading.toRadians() + perspective.getTheta().toRadians()) * halfR),
            y - (int) (Math.sin(heading.toRadians() + perspective.getTheta().toRadians()) * halfR));

        // Draw right line
        imageG2.drawLine(x, y,
            x + (int) (Math.cos(heading.toRadians() - perspective.getTheta().toRadians()) * halfR),
            y - (int) (Math.sin(heading.toRadians() - perspective.getTheta().toRadians()) * halfR));
        
        //@formatter:on

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
