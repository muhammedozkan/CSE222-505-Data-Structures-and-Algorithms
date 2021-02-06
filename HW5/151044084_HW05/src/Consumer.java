public class Consumer extends Thread {

    // this class is implemented according to the design pattern rules. once implement
    // more than one using ex. thread2 thread3 thread4 same consumer object type

    private final BinaryHeap<Integer[]> queue;
    private final String name;
    private final int threadNo;
    private final int consumeCount;

    public Consumer(BinaryHeap<Integer[]> queue, int threadNo, String name, int consumeCount) {
        this.queue = queue;
        this.name = name;
        this.threadNo = threadNo;
        this.consumeCount = consumeCount;
    }

    @Override
    public void run() {
        int count = 0;
        //when w*h==count auto termianted thread
        while (consumeCount > count) {
            try {
                Integer a[] = consume();

                System.out.printf("Thread%d-%s: [%d,%d,%d]\n", threadNo, name, a[0], a[1], a[2]);
                //System.out.printf("Thread%d-%s: [%d,%d,%d] %d \n",threadNo,name, a[0], a[1], a[2],count);
                count++;
            } catch (InterruptedException ex) {
                System.out.println(ex);
                System.exit(-1);
            }
        }
    }

    private Integer[] consume() throws InterruptedException {
        //wait if queue is empty
        while (queue.isEmpty()) {
            synchronized (queue) {
                queue.wait();
            }
        }

        //consuming element
        synchronized (queue) {
            return queue.poll();
        }
    }
}