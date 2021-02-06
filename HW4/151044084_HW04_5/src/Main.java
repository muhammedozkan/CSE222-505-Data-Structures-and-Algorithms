public class Main {

    public static void main(String[] args) {
        int arr[][] = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};

        int arr1[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        _2DArray array = new _2DArray(arr);
       // _2DArray array = new _2DArray(arr1);

        for (int a : array) {
            System.out.print(a + " ");
        }
    }
}