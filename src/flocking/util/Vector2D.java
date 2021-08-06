package flocking.util;

import flocking.Styles;

/**
 * Vector2D Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 5, 2021
 */
public class Vector2D {

    public float x;
    public float y;

    /**
     * Vector2D Constructor
     * 
     * @param x X component
     * @param y Y component
     */
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Sets the Vector to a new value
     * 
     * @param x new X component
     * @param y new Y component
     */
    public void setVector(float x, float y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Sets the Vector to a predefined Vector
     * 
     * @param vector predefined vector to set
     */
    public void setVector(Vector2D vector) {
        x = vector.x;
        y = vector.y;
    }


    /**
     * Adds two Vectors together
     * 
     * @param compliment Vector to add
     */
    public void add(Vector2D compliment) {
        x += compliment.x;
        y += compliment.y;
    }


    /**
     * Subtracts one Vector from another
     * 
     * @param compliment Vector to subtract
     */
    public void subtract(Vector2D compliment) {
        x -= compliment.x;
        y -= compliment.y;
    }


    /**
     * Scales the vector by the passed scale factor
     * 
     * @param scaleFactor scale factor to scale the Vector by
     */
    public void scale(float scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
    }


    /**
     * Finds the magnitude of a Vector
     * 
     * @return
     */
    public float calcMagnitude() {
        return calcMagnitude(x, y);
    }


    /**
     * Finds the magnitude of a Vector given its components
     * 
     * @param x
     * @param y
     * @return
     */
    public static float calcMagnitude(float x, float y) {
        return (float) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }


    /**
     * Tests whether or not two Vector2D objects are equal
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
        Vector2D objVector = (Vector2D) obj;
        return objVector.x == x && objVector.y == y;
    }


    /**
     * Returns a String representation of the Vector
     * 
     * @return a String representation of the Vector
     */
    @Override
    public String toString() {
        return "[" + Styles.df.format(x) + ", " + Styles.df.format(y) + "]";
    }

}
