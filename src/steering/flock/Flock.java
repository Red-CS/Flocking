package steering.flock;

import java.util.Iterator;
import java.util.NoSuchElementException;
import steering.flock.boid.Boid;

/**
 * Flock Class
 * Represents a Flock of Boids
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 8, 2021
 */
public class Flock<T extends Boid> implements Iterable<T> {

    private T[] flock;

    public Flock(T[] flockArray) {
        flock = flockArray;
    }


    public int size() {
        return flock.length;
    }


    /**
     * @return
     */
    public T[] toArray() {
        return flock;
    }


    /**
     * @return
     */
    @Override
    public Iterator<T> iterator() {
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
            return index < flock.length;
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
            return flock[index++];
        }
    }

}
