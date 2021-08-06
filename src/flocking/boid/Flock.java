package flocking.boid;

import java.util.Iterator;
import java.util.NoSuchElementException;
import flocking.util.Angle;
import flocking.util.Perspective;

/**
 * Flock Class
 * Represents a Flock of Boids
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Flock<T> implements Iterable<T> {

    public static final int DEFAULT_POPULATION_SIZE = 50;
    private int populationSize;
    private Boid[] flock;

    public Flock() {
        this(DEFAULT_POPULATION_SIZE);
    }


    public Flock(int populationSize) {
        this.populationSize = populationSize;
        flock = new Boid[populationSize];
        for (int i = 0; i < populationSize; i++) {
            flock[i] = new Boid(new Perspective(new Angle(180f), 50f));
        }
    }


    public int size() {
        return populationSize;
    }


    /**
     * @return
     */
    public T[] toArray() {
        return (T[]) flock;
    }


    /**
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new FlockIterator<T>();
    }

    /**
     * 
     * Iterator for Flock Class
     * 
     * @author Red Williams <red.devcs@gmail.com>
     * @since Aug 4, 2021
     * @param <Flock> Flock Type
     */
    private class FlockIterator<I> implements Iterator<T> {

        private int index;

        public FlockIterator() {
            index = 0;
        }


        /**
         * Returns whether there is a next element in the iterator
         * 
         * @return {@code true} if there are more elements to iterate over,
         * {@code false} if not
         */
        @Override
        public boolean hasNext() {
            return index < populationSize;
        }


        /**
         * Returns the next Boid in the sequence
         * 
         * @return the next Boid in the sequence
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator has reached "
                    + "end of list");
            }
            return (T) flock[index++];
        }
    }

}
