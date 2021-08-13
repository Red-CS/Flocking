package steering.util;

/**
 * Perspective Class
 * <br>
 * Represents a Boid's Field of View (also known as it's Neighborhood)
 * defined by an Angle and a Radius
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class Perspective {

    Angle theta;
    float radius;

    /** Default Perspective */
    public static final Perspective DEFAULT_PERSPECTIVE = new Perspective(
        new Angle(180), 30);

    /**
     * Perspective Constructor
     * <br>
     * Creates a Perspective object that represents the field of vision a Boid
     * has (also referred to as its neighborhood).
     * The Perspective can only have an angle between 0° and 180° ([0, 180]).
     * An angle of 180° represents a Perspective of 180° on both sides,
     * thus a complete, 360° view of its surroundings.
     * 
     * @param theta Angle of the Perspective field, in degrees, starting from
     *     the Boid's direction, on both sides. A {@code theta} of 180 would
     *     mean a circular Perspective field
     * @param radius Radius of the Perspective field
     * @throws IllegalArgumentException when {@code theta} is outside range
     *     [0, 180]
     */
    public Perspective(Angle theta, float radius)
        throws IllegalArgumentException {

        // Handle an angle outside bounds
        if (theta.toDegrees() < 0 || theta.toDegrees() > 180.0) {
            throw new IllegalArgumentException("Recieved theta = " + theta
                + ", must be within [0, 180].");
        }

        this.theta = theta;
        this.radius = radius;
    }


    /**
     * Returns the one-sided angle of the field
     * 
     * @return the angle of the field
     */
    public Angle getTheta() {
        return theta;
    }


    /**
     * Sets the one-sided angle of the current Perspective object
     * 
     * @param theta Angle to set
     * @throws IllegalArgumentException when {@code theta} is
     *     outside range [0, 180]
     */
    public void setAngle(Angle theta) throws IllegalArgumentException {

        // Handle an angle outside of bounds
        if (theta.toDegrees() < 0 || theta.toDegrees() > 180.0) {
            throw new IllegalArgumentException("Recieved theta = " + theta
                + ", must be within [0, 180].");
        }

        this.theta = theta;
    }


    /**
     * Returns the radius of the field
     * 
     * @return the radius of the field
     */
    public double getRadius() {
        return radius;
    }


    /**
     * Returns whether or not a Perspective contains a point
     * 
     * @param point Vector2D point to check
     * @return {@code true} if the Perspective field contains the point,
     * {@code false} otherwise.
     */
    public boolean contains(Vector2D point) {

        // Gets the angle the Boid makes with the current Boid
        float boidAngle = Math.abs((float) (Math.atan2(point.y, point.x)));

        // Check if the point is in the Perspective angle
        if (boidAngle <= theta.toRadians() && boidAngle >= -theta.toRadians()) {
            // Check if it is in the Perspective radius
            return (point.calcMagnitude() <= radius);
        }

        return false;
    }

}
