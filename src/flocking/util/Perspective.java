package flocking.util;

/**
 * Perspective Class
 * Represents a Boid's Field of View
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class Perspective {

    Angle theta;
    float radius;

    /**
     * Perspective Constructor
     * 
     * @param theta angle of the Perspective field, in degrees, starting from
     *     the Boid's direction, on both sides. A {@code theta} of 180 would
     *     mean a circular Perspective field
     * @param radius Radius of the Perspective field
     * @throws IllegalArgumentException when {@code theta} is outside range [0,
     *     180]
     */
    public Perspective(Angle theta, float radius)
        throws IllegalArgumentException {

        if (theta.toDegrees() < 0 || theta.toDegrees() > 180.0) {
            throw new IllegalArgumentException("Recieved theta = " + theta
                + ", must be within [0, 180].");
        }
        this.theta = theta;
        this.radius = radius;
    }


    /**
     * Gets the complimentary angle of the field
     * 
     * @return the angle of the field
     */
    public Angle getTheta() {
        return theta;
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
        float boidAngle = Math.abs((float) (Math.atan2(point.y, point.x)));
        if (boidAngle <= theta.toRadians() && boidAngle >= -theta.toRadians()) {
            return (point.calcMagnitude() <= radius);
        }
        return false;
    }

}
