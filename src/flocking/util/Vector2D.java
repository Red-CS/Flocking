package flocking.util;

import flocking.Styles;

/**
 * Vector2D Class
 * <br>
 * A Vector2D represents a 2-Dimensional Vector, which can be
 * used to represent positions, physics vectors, and things of the like
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 5, 2021
 */
public class Vector2D {

    // X and Y components
    public float x;
    public float y;

    /**
     * Vector2D Constructor
     * <br>
     * Creates a new Vector2D object with the passed components
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
     * @param v Vector to add
     */
    public void add(Vector2D v) {
        x += v.x;
        y += v.y;
    }


    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }


    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }


    /**
     * Subtracts one Vector from another
     * 
     * @param v Vector to subtract
     */
    public void subtract(Vector2D v) {
        x -= v.x;
        y -= v.y;
    }


    public void subtract(float x, float y) {
        this.x -= x;
        this.y -= y;
    }


    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }


    /**
     * Scales the vector by the passed scale factor
     * 
     * @param scaleFactor scale factor to scale the Vector by
     */
    public void multiply(float scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
    }


    public void divide(float scaleFactor) {
        x /= scaleFactor;
        y /= scaleFactor;
    }


    public float dot(Vector2D v) {
        return x * v.x + y * v.y;
    }


    public static float dot(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }


    /**
     * Normalizes the vector, giving it a magnitude of length 1
     */
    public void normalize() {
        float m = calcMagnitude();
        x /= m;
        y /= m;
    }


    public void limit(float magnitude) {
        float currMag = calcMagnitude();
        if (magnitude * magnitude < currMag * currMag) {
            normalize();
            multiply(magnitude);
        }
    }


    public void setMagnitude(float magnitude) {
        float currentMag = calcMagnitude();
        if (currentMag == 0) {
            return;
        }
        multiply(magnitude / currentMag);
    }


    /**
     * Calculates the magnitude of the Vector
     * 
     * @return the magnitude of the Vector
     */
    public float calcMagnitude() {
        return calcMagnitude(x, y);
    }


    /**
     * Calculates the magnitude of the Vector given its components
     * 
     * @param x X component
     * @param y Y component
     * @return the magnitude of the Vector
     */
    public static float calcMagnitude(float x, float y) {
        return (float) (Math.sqrt(x * x + y * y));
    }


    /**
     * Returns a random unit vector
     * 
     * @return a random unit vector
     */
    public static Vector2D random2D() {
        float angle = (float) (Math.random() * 2 * Math.PI);
        return new Vector2D((float) Math.cos(angle), (float) Math.sin(angle));
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
