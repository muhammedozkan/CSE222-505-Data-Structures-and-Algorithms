import java.util.EmptyStackException;

/*Generic Stack Structure*/
public class Stack<E> {

    private int size;//capacity
    private E[] arr;
    private int top;

    /**
     * Constructor to create stack initial size=10
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        this.size = 10;
        this.arr = (E[]) new Object[size];
        this.top = -1;
    }

    /**
     * This method adds new entry to the top
     * of the stack
     *
     * @param entry
     */
    public void push(E entry) {
        if (top == (size - 1)) {
            this.increaseCapacity();
        }
        this.arr[++top] = entry;//O(1) complexity
    }

    /**
     * This method removes an entry from the
     * top of the stack.
     *
     * @return E
     */
    public E pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arr[top--];//O(1) complexity
    }

    /**
     * This method returns top of the stack
     * without removing it.
     *
     * @return E
     */
    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[top];//O(1) complexity
    }

    /**
     * This method increases capacity by 2 times
     * if the stack is full
     */
    private void increaseCapacity() {

        @SuppressWarnings("unchecked")
        E[] newStack = (E[]) new Object[this.size * 2];
        for (int i = 0; i < size; i++) {
            newStack[i] = this.arr[i];//O(n) complexity
        }
        this.arr = newStack;
        this.size *= 2;
    }

    /**
     * This method returns true
     * if the stack is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (top == -1);
    }
}