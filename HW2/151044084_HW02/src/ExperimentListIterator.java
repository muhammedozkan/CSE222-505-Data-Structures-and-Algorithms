/**
 * Represents an ExperimentListIterator.
 *
 * @author MuhammedOZKAN 151044084 @pithblood
 * @version 1.0.0
 * @since 2019
 */

import java.util.Iterator;

public class ExperimentListIterator implements Iterator<Experiment> {

    private ExperimentNode current;

    /**
     * Creates pointer to head of the list for iteration.
     *
     * @param list The ExperimentList.
     */
    public ExperimentListIterator(ExperimentList list) {
        current = list.getHead();
    }

    /**
     * Returns false if next element does not exist.
     *
     * @return boolean
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Return current data and update pointer.
     *
     * @return Experiment
     */
    public Experiment next() {
        Experiment data = current.getData();
        current = current.getNext();
        return data;
    }

    /**
     * Implement if needed
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

}