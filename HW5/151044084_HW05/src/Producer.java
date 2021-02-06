public class Producer extends Thread {

    private final BinaryHeap<Integer[]> lex;
    private final BinaryHeap<Integer[]> euc;
    private final BinaryHeap<Integer[]> bmx;

    private final String name;
    private final int threadNo;

    private final Integer[][][] data;
    private final int SIZE;

    public Producer(BinaryHeap<Integer[]> lex, BinaryHeap<Integer[]> euc, BinaryHeap<Integer[]> bmx, Integer[][][] data, int size, int threadNo, String name) {

        this.lex = lex;
        this.euc = euc;
        this.bmx = bmx;

        this.name = name;
        this.threadNo = threadNo;

        this.data = data;
        this.SIZE = size;
    }

    @Override
    public void run() {
        for (int j = 0; data[0].length > j; j++) {
            for (int i = 0; data.length > i; i++) {


                produce(data[i][j], lex);
                produce(data[i][j], euc);
                produce(data[i][j], bmx);

                System.out.printf("Thread 1:[%d, %d, %d]\n", data[i][j][0], data[i][j][1], data[i][j][2]);
                //System.out.printf("Thread 1:[%d, %d, %d] %d\n", data[i][j][0], data[i][j][1], data[i][j][2], (data.length * j) + i);

                // ex. 100==SIZE start consumers. first 100 element insert then start thread
                if ((data.length * j) + i == SIZE - 1) {
                    Thread consumerThreadLex = new Thread(new Consumer(lex, threadNo + 1, "PQLEX", data.length * data[0].length), "ConsumerPQLEX");
                    Thread consumerThreadEuc = new Thread(new Consumer(euc, threadNo + 2, "PQEUC", data.length * data[0].length), "ConsumerPQEUC");
                    Thread consumerThreadBmx = new Thread(new Consumer(bmx, threadNo + 3, "PQBMX", data.length * data[0].length), "ConsumerPQBMX");

                    consumerThreadLex.start();//thread2
                    consumerThreadEuc.start();//thread3
                    consumerThreadBmx.start();//thread4
                }

            }
        }

    }

    private void produce(Integer[] data, BinaryHeap<Integer[]> queue) {

        //producing element and notify waiting consumer
        synchronized (queue) {
            queue.offer(data);
            queue.notify();
        }
    }
}