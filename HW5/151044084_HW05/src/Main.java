import java.io.IOException;

public class Main {

    public static void main(String args[]) {

        BinaryHeap<Integer[]> PQLEX = new BinaryHeap(100, new Comparators.LexComparator());

        BinaryHeap<Integer[]> PQEUC = new BinaryHeap(100, new Comparators.EucComparator());

        BinaryHeap<Integer[]> PQBMX = new BinaryHeap(100, new Comparators.BmxComparator());

        Integer data[][][];

        try {
            if (args.length <= 0) {
                System.out.println("Please insert the command line argument(png or jpg file path).");
            } else {
                data = ImageReader.read(args[0]);

                Thread producerThread = new Thread(new Producer(PQLEX, PQEUC, PQBMX, data, 100, 1, "Producer"), "ProducerPQLEX-PQEUC-PQBMX");

                producerThread.start();//thread1
            }
        } catch (IOException e) {
            System.out.println("Please check the command line argument(png or jpg file path).");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}

