/**
 * Represents an Test-Driver Function.
 *
 * @author MuhammedOZKAN 151044084 @pithblood
 * @version 1.0.0
 * @since 2019
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

// Driver class
public class Main {
    public static void main(String[] args) {
        // Create ExperimentList
        ExperimentList myList = new ExperimentList();

        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        // Add Experiment
        System.out.println();
        System.out.println("---------------addExp() TEST---------------");
        //the same time value is assigned to each experiment because there is no time-related operation for testing
        myList.addExp(new Experiment("Exp70       ", 7, time, false, 4.87F));
        myList.addExp(new Experiment("Exp7        ", 7, time, true, 4.87F));
        myList.addExp(new Experiment("Exp10       ", 1, time, false, 9.69F));
        myList.addExp(new Experiment("Exp1010     ", 3, time, false, 0.59F));
        myList.addExp(new Experiment("Exp2        ", 2, time, true, 0.81F));
        myList.addExp(new Experiment("Exp22       ", 2, time, true, 7.37F));
        myList.addExp(new Experiment("Exp77       ", 7, time, false, 1.81F));
        myList.addExp(new Experiment("Exp11       ", 8, time, true, 3.22F));
        myList.addExp(new Experiment("Exp222      ", 2, time, false, 2.83F));
        myList.addExp(new Experiment("Exp100      ", 1, time, true, 9.83F));
        myList.addExp(new Experiment("Exp100100   ", 6, time, true, 8.13F));
        myList.addExp(new Experiment("Exp9        ", 9, time, true, 6.22F));
        myList.addExp(new Experiment("Exp2222     ", 2, time, false, 0.37F));
        myList.addExp(new Experiment("Exp777      ", 7, time, true, 7.81F));
        myList.addExp(new Experiment("Exp100100100", 1, time, true, 5.83F));
        myList.addExp(new Experiment("Exp4        ", 4, time, false, 0.59F));
        myList.addExp(new Experiment("Exp99       ", 9, time, true, 3.22F));
        myList.addExp(new Experiment("Exp8        ", 8, time, true, 2.87F));
        myList.addExp(new Experiment("Exp1111     ", 1, time, true, 8.22F));
        myList.addExp(new Experiment("Exp22222    ", 2, time, false, 7.81F));
        myList.addExp(new Experiment("Exp7777     ", 7, time, true, 4.87F));
        myList.addExp(new Experiment("Exp5        ", 5, time, false, 5.87F));
        myList.addExp(new Experiment("Exp500      ", 5, time, true, 4.87F));
        myList.addExp(new Experiment("Exp1        ", 1, time, false, 7.87F));
        myList.addExp(new Experiment("Exp1.1      ", 6, time, true, 4.87F));
        myList.addExp(new Experiment("Exp3        ", 3, time, true, 6.87F));
        myList.addExp(new Experiment("Exp500500   ", 5, time, false, 5.87F));

        System.out.println("---------------Iterator Use TEST---------------");
        // Iterate through the list using For Each Loop
        for (Experiment exp : myList)
            System.out.println(exp);


        System.out.println();
        System.out.println("---------------getExp(7,2),getExp(1,0) TEST---------------");
        System.out.println(myList.getExp(7, 2));
        System.out.println(myList.getExp(1, 0));


        System.out.println();
        System.out.println("---------------setExp(7,2,Experiment e) TEST---------------");
        myList.setExp(7, 2, new Experiment("Exp77new    ", 7, time, false, 5.87F));

        System.out.println();
        System.out.println("---------------orderDay(2) TEST---------------");

        myList.orderDay(2);
        for (Experiment exp : myList)
            System.out.println(exp);


        System.out.println();
        System.out.println("---------------removeDay(5) TEST---------------");
        myList.removeDay(5);
        for (Experiment exp : myList)
            System.out.println(exp);

        System.out.println();
        System.out.println("---------------removeExp(6,0) TEST---------------");
        myList.removeExp(6, 0);


        for (Experiment exp : myList)
            System.out.println(exp);


        System.out.println();
        System.out.println("---------------listExp(7) TEST---------------");
        myList.listExp(7);


        System.out.println();
        System.out.println("---------------orderExperiments() TEST---------------");

        ExperimentList sort = myList.orderExperiments();

        for (Experiment exp : sort)
            System.out.println(exp);

        System.out.println();
        System.out.println("---------------ExperimentList Structure getnext() TEST---------------");

        ExperimentNode head;

        head = myList.getHead();

        do {

            System.out.print(head);
            System.out.print("  ");
            System.out.print(head.getData());
            System.out.print("    ");

            if (head.getNext() != null) {
                System.out.print(" next==> ");
                System.out.print(head.getNext());
            }
            System.out.print("    ");
            if (head.getNextDay() != null) {
                System.out.print(" nextDay==> ");
                System.out.print(head.getNextDay());
            }

            if (head.getNext() != null && head.getNext().getNextDay() != null)
                System.out.println();

            System.out.println();

            head = head.getNext();
        } while ((head != null));


        System.out.println();
        System.out.println("---------------ExperimentList Structure getnextDay() TEST---------------");

        head = myList.getHead();

        do {

            System.out.print(head);
            System.out.print("  ");
            System.out.print(head.getData());
            System.out.print("    ");


            if (head.getNextDay() != null) {
                System.out.print(" nextDay==> ");
                System.out.print(head.getNextDay());
            }


            System.out.println();

            head = head.getNextDay();
        } while ((head != null));

        System.out.println();
        System.out.println("---------------ExperimentList listAll() TEST---------------");
        System.out.println();
        myList.listAll();

        //if you want to test it,delete comment line

        /*System.out.println();
        System.out.println("---------------ExperimentList test2---------------");

        test2();*/

    }

    public static void test2() {

        ExperimentList list = new ExperimentList();
        Random generator = new Random();
        generator.setSeed(3);
        boolean completed = true;
        float acc;
        int day;
        String time = "timeInfo";
        for (int i = 0; i < 20; i++) {
            System.out.println("----------------------");
            day = generator.nextInt(4);
            String setup = "setup" + i;
            acc = (float) (i * 0.1);
            Experiment e = new Experiment(setup, day, time, completed, acc);
            System.out.println("Add new experiment:");
            System.out.println(e.toString());
            list.addExp(e);
            list.listAll();
        }

        System.out.println("----------------------");
        System.out.println("getExp(0,3) Result:");
        Experiment e = list.getExp(0, 3);
        System.out.println(e.toString());

        System.out.println("----------------------");
        System.out.println("setExp(0,3), accuracy=1.0");
        e.setAccuracy((float) 1.0);
        list.setExp(0, 3, e);
        e = list.getExp(0, 3);

        System.out.println("----------------------");
        System.out.println("getExp Result:");
        e = list.getExp(0, 3);
        System.out.println(e.toString());

        System.out.println("----------------------");
        System.out.println("listExp(0) Result:");
        list.listExp(0);

        System.out.println("----------------------");
        System.out.println("removeExp(0,0) Result:");
        list.removeExp(0, 0);
        list.listAll();

        System.out.println("----------------------");
        System.out.println("removeExp(1,0) Result:");
        list.removeExp(1, 0);
        list.listAll();

        System.out.println("----------------------");
        System.out.println("removeExp(1,) Result:");
        list.removeExp(1, 0);
        list.listAll();

        System.out.println("----------------------");
        System.out.println("removeExp(3,6) Result:");
        list.removeExp(3, 6);
        list.listAll();

        System.out.println("----------------------");
        System.out.println("orderExperiment Result:");
        ExperimentList orderedList = list.orderExperiments();

        Iterator itr = orderedList.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }

    }
}
