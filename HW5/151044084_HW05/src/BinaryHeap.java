import java.util.Comparator;
import java.util.NoSuchElementException;


public class BinaryHeap<E> {
    private E[] items;                    // store items at index 1 to n
    private int size;                       // number of items on queue
    private Comparator<E> comparator;  // compare object


    public BinaryHeap(int initCapacity, Comparator<E> comparator) {
        this.comparator = comparator;
        items = (E[]) new Object[initCapacity + 1];//0. element don't use
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void offer(E x) {

        // double size of array if necessary
        if (size == items.length - 1) {
            reallocate(2 * items.length);
        }

        // add x, and shifting
        items[++size] = x;
        shifting(size);
    }


    public E poll() {
        if (isEmpty()) throw new NoSuchElementException();
        E max = items[1];
        swap(1, size--);
        editing(1);
        items[size + 1] = null;

        // to avoid unnecessary traversing and help  garbage collection
        if ((size > 0) && (size == (items.length - 1) / 4)) {
            reallocate(items.length / 2);
        }
        return max;
    }

    // helper function to expand or shrink array
    private void reallocate(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private void shifting(int k) {
        while (k > 1 && compare(parent(k), k)) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void editing(int k) {
        boolean flag = true;
        while (leftChild(k) <= size && flag) {
            int j = leftChild(k);
            if (j < size && compare(j, rightChild(k))) {
                j++;
            }
            if (!compare(k, j)) {
                flag = false;
            } else {
                swap(k, j);
                k = j;
            }
        }
    }

    //using comparator object
    private boolean compare(int i, int j) {
        return comparator.compare(items[i], items[j]) < 0;
    }

    private void swap(int i, int j) {
        E temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //returns left children.
    private int leftChild(int i) {
        return (2 * i);
    }

    //returns right children.
    private int rightChild(int i) {
        return (2 * i) + 1;
    }

    // returns position of parent
    private int parent(int i) {
        return i / 2;
    }


}