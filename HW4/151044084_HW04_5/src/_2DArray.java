import java.util.Iterator;

public class _2DArray implements Iterable<Integer> {
    private int arr[][];

    public int[][] getArr() {
        return arr;
    }

    public _2DArray(int[][] arr) {
        this.arr = arr;
    }

    public Iterator<Integer> iterator() {
        return new _2DArrayIterator(this);
    }
}
