/**
 * Represents an Experiment.
 *
 * @author MuhammedOZKAN 151044084 @pithblood
 * @version 1.0.0
 * @since 2019
 */
public class Experiment {
    private String setup;
    private int day;
    private String time;
    private boolean completed;
    private float accuracy;

    /**
     * Creates an Experiment with the specified parameter.
     *
     * @param setup     The Experiments setup information.
     * @param day       The Experiments day value.
     * @param time      The Experiments time.
     * @param completed The Experiments completed value.
     * @param accuracy  The Experiments accuracy value.
     */
    public Experiment(String setup, int day, String time, boolean completed, float accuracy) {
        setSetup(setup);
        setDay(day);
        setTime(time);
        setCompleted(completed);
        setAccuracy(accuracy);
    }

    /**
     * Returns an experiment with all information.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }

    // Setter getter methods

    /**
     * Returns an experiment setup information.
     *
     * @return String
     */
    public String getSetup() {
        return setup;
    }

    /**
     * Sets an experiment setup information.
     *
     * @param setup The Experiments setup information.
     */
    public void setSetup(String setup) {
        this.setup = setup;
    }

    /**
     * Returns an experiment day value.
     *
     * @return int
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets an experiment day value.
     * Day assume start from 0
     *
     * @param day The Experiments day value.
     */
    public void setDay(int day) {
        if (day < 0)
            throw new IndexOutOfBoundsException();
        this.day = day;
    }

    /**
     * Returns an experiment time.
     *
     * @return String
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets an experiment time.
     *
     * @param time The Experiments time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns the status value of an experiment.
     *
     * @return boolean if Experiment is completed , return true
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion value of the experiment
     *
     * @param completed Experiment complete information
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns an accuracy value.
     *
     * @return float
     */
    public float getAccuracy() {
        return accuracy;
    }

    /**
     * Sets an accuracy value.
     *
     * @param accuracy EThe Experiments accuracy value.
     */
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
}