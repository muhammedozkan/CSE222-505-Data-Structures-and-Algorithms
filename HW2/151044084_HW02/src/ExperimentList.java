/**
 * Represents an ExperimentList.
 *
 * @author MuhammedOZKAN 151044084 @pithblood
 * @version 1.0.0
 * @since 2019
 */

import java.util.Iterator;

public class ExperimentList implements Iterable<Experiment> {

    private ExperimentNode head, tail;

    /**
     * Creates an ExperimentList with no parameter.
     */
    public ExperimentList() {
        head = tail = null;
    }

    /**
     * Creates an ExperimentList with the specified parameter.
     *
     * @param head The ExperimentList head reference.
     */
    public ExperimentList(ExperimentNode head) {
        this.head = tail = head;
        //find the list tail
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

    /**
     * Insert experiment to the end of the day on ExperimentList.
     *
     * @param data The Experiment data.
     */
    public void addExp(Experiment data) {
        //creates new node
        ExperimentNode node = new ExperimentNode(data, null, null);

        if (head == null) {
            tail = head = node;
        } else {
            if (data.getDay() < head.getData().getDay()) {
                //nextDay reference assignment
                //next reference assignment
                node.setNextDay(head);
                node.setNext(head);
                head = node;
                //tail control
            } else if (data.getDay() >= tail.getData().getDay()) {
                //nextDay reference assignment condition
                if (data.getDay() > tail.getData().getDay()) {
                    getExperimentNode(tail.getData().getDay(), 0).setNextDay(node);
                }
                //next reference assignment
                tail.setNext(node);
                tail = node;
            } else {
                ExperimentNode temp = head;
                ExperimentNode previous = temp;
                //To get to the given day
                while (data.getDay() >= temp.getData().getDay()) {
                    previous = temp;
                    temp = temp.getNext();
                }
                //next reference assignment
                node.setNext(temp);
                previous.setNext(node);
                //nextDay reference assignment condition
                if (previous.getData().getDay() < data.getDay()) {
                    //Gets index 0 and then is updated nextDay
                    node.setNextDay(getExperimentNode(previous.getData().getDay(), 0).getNextDay());
                    getExperimentNode(previous.getData().getDay(), 0).setNextDay(node);
                }
            }
        }
    }


    /**
     * Get the experiment with the given day and position
     *
     * @param day   day value
     * @param index index value
     * @return Experiment
     */
    //index and day are started from 0
    public Experiment getExp(int day, int index) {
        return getExperimentNode(day, index).getData();
    }


    /**
     * Set the experiment with the given day and position
     *
     * @param day   day value
     * @param index index value
     * @param data  The Experiment data.
     */
    //index and day are started from 0. the data on this node has been changed. consistency check not performed.
    public void setExp(int day, int index, Experiment data) {
        if (data.getDay() != day)
            throw new IllegalArgumentException();
        getExperimentNode(day, index).setData(data);
    }


    /**
     * Remove the experiment specified as index from given day.
     *
     * @param day   day value
     * @param index index value
     */
    public void removeExp(int day, int index) {
        if (index < 0 || day < 0)
            throw new IndexOutOfBoundsException();
        else {
            ExperimentNode temp = head;
            ExperimentNode previous = temp;
            ExperimentNode previousDay = temp;
            int counter = 0;
            //To get to the given day
            while (temp.getData().getDay() != day) {
                previousDay = temp;
                temp = temp.getNextDay();
            }
            //on the given day it is counted to go to the position.
            while (counter != index) {
                if (temp.getData().getDay() == day)
                    counter++;
                //if the requested index does not exist on the list, it throws an error.
                if (temp.getNext().getData().getDay() != day) {
                    throw new IndexOutOfBoundsException();
                }
                previous = temp;
                temp = temp.getNext();
            }
            if (temp == head) {
                head = temp.getNext();
                head.setNextDay(temp.getNextDay());
            } else {
                if (index == 0) {
                    //for find new tail
                    temp = getExperimentNode(previousDay.getData().getDay(), 0);
                    while (temp.getData().getDay() != day) {
                        previous = temp;
                        temp = temp.getNext();
                    }
                    if (temp == tail) {
                        // next reference null assignment
                        previous.setNext(null);
                        tail = previous;
                        previousDay.setNextDay(null);
                    } else {
                        previous.setNext(temp.getNext());
                        if (temp.getNext().getData().getDay() == temp.getData().getDay())
                            temp.getNext().setNextDay(temp.getNextDay());
                        previousDay.setNextDay(temp.getNext());
                    }
                } else {
                    previous.setNext(temp.getNext());
                }
            }
        }
    }


    /**
     * List all completed experiments in a given day and print on the screen.
     *
     * @param day day value
     */
    public void listExp(int day) {
        if (day < 0)
            throw new IndexOutOfBoundsException();
        else {
            ExperimentNode temp = head;
            boolean flag = true;
            //the function prints a warning message if the desired day is not available.
            while (temp.getData().getDay() != day && flag) {
                if (temp.getNextDay() != null)
                    temp = temp.getNextDay();
                else
                    flag = false;
            }
            if (flag) {
                //isCompleted checking
                //Null checking was made to prevent failure at the end of the list
                do {
                    if (temp.getData().isCompleted())
                        System.out.println(temp.getData());
                    if (temp.getNext() != null)
                        temp = temp.getNext();
                    else {
                        flag = false;
                    }
                } while (flag && temp.getData().getDay() == day);
            } else
                System.out.println("Experiment not found for the requested day");
        }
    }

    /**
     * Remove all experiments in a given day.
     *
     * @param day day value
     */
    public void removeDay(int day) {
        if (day < 0)
            throw new IndexOutOfBoundsException();
        else {
            ExperimentNode temp = head;
            ExperimentNode previous = temp;
            boolean flag = true;
            //run until the given day.
            while (temp.getData().getDay() != day && flag) {
                if (temp.getNext() != null) {
                    previous = temp;
                    temp = temp.getNext();
                } else
                    flag = false;
            }
            if (flag) {
                //Null checking was made to prevent failure at the end of the list
                while (temp.getData().getDay() == day && temp.getNext() != null) {
                    temp = temp.getNext();
                }
                if (previous == head && temp == head) {
                    throw new IndexOutOfBoundsException();
                } else if (previous == head) {
                    head = temp;
                } else if (temp == tail) {
                    previous.setNext(null);
                    previous.setNextDay(null);
                    tail = previous;
                    getExperimentNode(previous.getData().getDay(), 0).setNextDay(null);
                } else {
                    previous.setNext(temp);
                    getExperimentNode(previous.getData().getDay(), 0).setNextDay(temp);
                }
            } else
                System.out.println("Experiment not found for the requested day");
        }
    }

    /**
     * Sorts the experiments performed on a given day according to their accuracy,
     * the changes were made in the list.
     *
     * @param day day value
     */
    public void orderDay(int day) {
        if (day < 0)
            throw new IndexOutOfBoundsException();
        else {
            ExperimentNode temp = head;
            int count = 0;
            boolean flag = true;
            //the function run until the given day if the desired day is not available, returns false
            while (temp.getData().getDay() != day && flag == true) {
                if (temp.getNext() != null)
                    temp = temp.getNext();
                else
                    flag = false;
            }
            if (flag != false) {
                ExperimentNode dayfirst = temp;
                //Count how many items on the requested day.
                while (temp != null && temp.getData().getDay() == day) {
                    temp = temp.getNext();
                    count++;
                }

                if (count > 1) {//bubbleSort
                    for (int i = 0; i < count - 1; i++) {
                        temp = dayfirst;
                        for (int j = 0; j < count - i - 1; j++) {
                            if (temp.getData().getDay() == temp.getNext().getData().getDay())
                                if (temp.getData().getAccuracy() > temp.getNext().getData().getAccuracy()) {
                                    //swap data
                                    Experiment tmp = temp.getData();
                                    temp.setData(temp.getNext().getData());
                                    temp.getNext().setData(tmp);
                                }
                            temp = temp.getNext();
                        }
                    }
                }
            }

        }
    }


    /**
     * Returns the head node of the ordered ExperimentList.
     * <p>
     * Sorts all the experiments in the list by accuracy,
     * the original list was not changed because the log list could be damaged.
     *
     * @return ExperimentNode
     */
    public ExperimentList orderExperiments() {
        //creates new head,tail because this function return new list
        ExperimentNode headS, tailS;
        tailS = headS = null;

        //it be traversing on original list because each element must be adding in new list
        for (Experiment e : this) {
            //creates new node
            ExperimentNode nodeS = new ExperimentNode(e, null, null);
            //the same addExp() function implement, this is done by sorting according to the accuracy value.
            if (headS == null)
                tailS = headS = nodeS;
            else {
                if (e.getAccuracy() < headS.getData().getAccuracy()) {
                    nodeS.setNext(headS);
                    headS = nodeS;
                } else if (e.getAccuracy() >= tailS.getData().getAccuracy()) {
                    tailS.setNext(nodeS);
                    tailS = nodeS;
                } else {
                    ExperimentNode temp = headS;
                    ExperimentNode previous = temp;

                    while (e.getAccuracy() >= temp.getData().getAccuracy()) {
                        previous = temp;
                        temp = temp.getNext();
                    }
                    nodeS.setNext(temp);
                    previous.setNext(nodeS);
                }
            }
        }
        return new ExperimentList(headS);
    }

    //helper function

    /**
     * Returns the ExperimentNode reference of the
     * specified node(day,index) in the ExperimentList.
     *
     * @param day   day value
     * @param index index value
     * @return ExperimentNode
     */
    private ExperimentNode getExperimentNode(int day, int index) {
        if (index < 0 || day < 0)//if the requested index or day less than 0, it throws an error.
            throw new IndexOutOfBoundsException();
        else {
            ExperimentNode temp = head;
            int counter = 0;
            //run until the given day.
            while (temp.getData().getDay() != day) {
                temp = temp.getNextDay();
            }
            //on the given day it is counted to go to the position.
            while (counter != index) {
                if (temp.getData().getDay() == day)
                    counter++;
                //if the requested index is not available, it throws an error
                if (temp.getNext().getData().getDay() != day) {
                    throw new IndexOutOfBoundsException();
                }
                temp = temp.getNext();
            }
            return temp;
        }
    }


    /**
     * Returns head node of the ExperimentList.
     *
     * @return ExperimentNode
     */
    public ExperimentNode getHead() {
        return head;
    }


    /**
     * Returns Iterator instance.
     *
     * @return Iterator
     */
    public Iterator<Experiment> iterator() {
        return new ExperimentListIterator(this);
    }

    public void listAll() {
        System.out.println("List experiment view:");
        ExperimentNode last = head;
        while (last != null) {
            System.out.println(last.getData().toString());
            last = last.getNext();
        }
        System.out.println();
        System.out.println("List day view:");
        last = head;
        while (last != null) {
            System.out.println(last.getData().toString());
            last = last.getNextDay();
        }
    }
}
