/**
 * Represents an ExperimentNode.
 *
 * @author MuhammedOZKAN 151044084 @pithblood
 * @version 1.0.0
 * @since 2019
 */
public class ExperimentNode {

    private Experiment data;
    private ExperimentNode next;
    private ExperimentNode nextDay;

    /**
     * Creates an ExperimentNode with the specified parameter.
     *
     * @param data    The ExperimentNode data.
     * @param next    The ExperimentNode next.
     * @param nextDay The ExperimentNode nextDay.
     */
    public ExperimentNode(Experiment data, ExperimentNode next, ExperimentNode nextDay) {
        this.data = data;
        this.next = next;
        this.nextDay = nextDay;
    }

    // Setter getter methods

    /**
     * Returns an experiment data.
     *
     * @return Experiment
     */
    public Experiment getData() {
        return data;
    }

    /**
     * Sets an experiment data.
     *
     * @param data The ExperimentNode data.
     */
    public void setData(Experiment data) {
        this.data = data;
    }

    /**
     * Returns an next experiment reference.
     *
     * @return ExperimentNode
     */
    public ExperimentNode getNext() {
        return next;
    }

    /**
     * Sets an experiment next reference.
     *
     * @param next The ExperimentNode next.
     */
    public void setNext(ExperimentNode next) {
        this.next = next;
    }

    /**
     * Returns an nextDay experiment reference.
     *
     * @return ExperimentNode
     */
    public ExperimentNode getNextDay() {
        return nextDay;
    }

    /**
     * Sets an experiment nextDay reference.
     *
     * @param nextDay The ExperimentNode nextDay.
     */
    public void setNextDay(ExperimentNode nextDay) {
        this.nextDay = nextDay;
    }
}
