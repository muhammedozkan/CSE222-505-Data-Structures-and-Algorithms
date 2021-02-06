import java.util.Iterator;

public class _2DArrayIterator implements Iterator<Integer> {
    private int[] lineForm;
    private int length;
    private int index;


    public _2DArrayIterator(_2DArray list) {
        length = list.getArr().length * list.getArr()[0].length;//m*n
        lineForm = new int[length];
        index = 0;//used  for lineForm index
        spiral(list.getArr(), 0);
        index = 0;//used  for iterator index
    }


    public boolean hasNext() {
        return (index < length);
    }


    public Integer next() {
        return lineForm[index++];
    }


    public void remove() {
        throw new UnsupportedOperationException();
    }

    //this function converts the given matrix into a line form by handling the layer by layer.
    public void spiral(int arr[][], int layer) {

        int m = arr.length;
        int n = arr[0].length;

        if ((layer <= m / 2 && layer <= n / 2)) {

            int left = layer;
            int right = n - 1 - layer;
            int top = layer;
            int bottom = m - 1 - layer;

            if (left == right && top == bottom && left == top)//bug fix for exam 5*5 or odd square matrix. 5/2 5/2 = arr[2][2] cannot access, access it
            {
                lineForm[index] = arr[left][top];
            }

            layerBasedFill(arr, top, left, right, 1);
            layerBasedFill(arr, right, top, bottom, 2);
            layerBasedFill(arr, bottom, right, left, 3);
            layerBasedFill(arr, left, bottom, top, 4);

            spiral(arr, ++layer);//next layer calling
        }
    }

    public void layerBasedFill(int arr[][], int constant, int counter, int last, int direction) {
        if (direction == 1)//left to right
        {
            if (counter < last) {
                lineForm[index] = arr[constant][counter];
                index++;
                counter++;
                layerBasedFill(arr, constant, counter, last, direction);
            }
        } else if (direction == 2)//top to bottom
        {
            if (counter < last) {
                lineForm[index] = arr[counter][constant];
                index++;
                counter++;
                layerBasedFill(arr, constant, counter, last, direction);
            }
        } else if (direction == 3)//right to left
        {
            if (counter > last) {
                lineForm[index] = arr[constant][counter];
                index++;
                counter--;
                layerBasedFill(arr, constant, counter, last, direction);
            }
        } else if (direction == 4)//bottom to top
        {
            if (counter > last) {
                lineForm[index] = arr[counter][constant];
                index++;
                counter--;
                layerBasedFill(arr, constant, counter, last, direction);
            }
        }
    }
}
