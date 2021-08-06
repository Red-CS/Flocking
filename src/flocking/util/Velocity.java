package flocking.util;

import flocking.Styles;

/**
 * Velocity Class
 * Provides a Velocity implementation using Polar and Cartesian coordinates
 * 
 * @implNote Cartesian coordinates provide a cleaner way of performing vector
 * actions,
 * and Polar coordinates provide an easy way to visualize the Vector
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class Velocity extends Vector2D {

    private Angle direction;
    private float magnitude;

    /**
     * Velocity Constructor
     */
    public Velocity(Angle direction, float magnitude) {
        super((float) (magnitude * Math.cos(direction.toRadians())),
            (float) (magnitude * Math.sin(direction.toRadians())));
        this.direction = direction;
        this.magnitude = magnitude;
    }


    public Velocity(float x, float y) {
        super(x, y);
        direction = new Angle(x, y);
        magnitude = calcMagnitude();
    }


    public void setVelocity(Velocity newVelocity) {

    }


    /**
     * @return the direction
     */
    public Angle getDirection() {
        return direction;
    }


    /**
     * Sets the angle of the Velocity vector given the angle, also computing the
     * x and y components
     * 
     * @param direction the direction to set
     */
    public void setDirection(Angle direction) {
        this.direction = direction;
        setVector((float) (magnitude * Math.cos(direction.toRadians())),
            (float) (magnitude * Math.sin(direction.toRadians())));

    }


    /**
     * @return the magnitude
     */
    public float getMagnitude() {
        return magnitude;
    }


    /**
     * @param magnitude the magnitude to set
     */
    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
        setVector((float) (magnitude * Math.cos(direction.toRadians())),
            (float) (magnitude * Math.sin(direction.toRadians())));
    }


    /**
     * Adds a Velocity vector to the current Velocity
     * 
     * @param velocity Velocity to add
     */
    public void add(Velocity velocity) {
        x += velocity.x;
        y += velocity.y;
        direction = new Angle((float) (Math.toDegrees(Math.atan2(y, x))));
        magnitude = calcMagnitude();
    }


    /**
     * Adds a Vector to the current Velocity Vector, and reconfigures the Angle
     * and Magnitude variables
     * 
     * @param vector Vector2D to add
     */
    @Override
    public void add(Vector2D vector) {
        super.add(vector);

        reconfigVars();
    }


    /**
     * Subtracts a Vector from the current Velocity Vector, and reconfigures the
     * Angle
     * and Magnitude variables
     * 
     * @param vector Vector2D to subtract
     */
    @Override
    public void subtract(Vector2D vector) {
        super.subtract(vector);
        reconfigVars();

    }


    /**
     * Scales the Velocity vector be the specified scale factor
     * 
     * @param scaleFactor scale factor to scale by
     */
    @Override
    public void scale(float scaleFactor) {
        super.scale(scaleFactor);
        magnitude *= scaleFactor;
    }


    /**
     * Tests whether or not two Velocity objects are equal
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
        Velocity objVelocity = (Velocity) obj;
        return objVelocity.direction.equals(direction)
            && objVelocity.magnitude == magnitude;
    }


    @Override
    public String toString() {
        return "{" + direction.toString() + ", " + Styles.df.format(magnitude)
            + "} : " + super.toString();
    }


    /**
     * Reconfigures the variables after performing Vector operations
     */
    private void reconfigVars() {
        // Calculate angle
        float vectorAngle = (float) (Math.toDegrees(Math.atan2(y, x)));

        // Change to positive, if necessary
        if (vectorAngle < 0) {
            vectorAngle += 360;
        }

        // Reset direciton and magnitude
        direction = new Angle(vectorAngle);
        magnitude = calcMagnitude();
    }

}
