import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length <= 0)
                System.out.println("Please insert the command line argument(txt file path).");
            else
                System.out.printf("Result is %d", findWhiteLand(read(args[0])));
        } catch (IOException e) {
            System.out.println("Please check the command line argument(txt file path).");
            System.out.println(e);
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public static int findWhiteLand(Character matrix[][]) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int result = 0;

        Stack<Pair> stack = new Stack<Pair>();
        Pair position;
        //m * n times.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == '1') {
                    stack.push(new Pair(i, j));
                    while (!stack.isEmpty()) {//worst case m*n times.
                        position = stack.pop();

                        matrix[position.x][position.y] = (char) ('A' + result);//for optimization

                        if (!(position.x + 1 >= matrix.length || matrix[position.x + 1][position.y] != '1'))
                            stack.push(new Pair(position.x + 1, position.y));

                        if (!(position.x - 1 < 0 || matrix[position.x - 1][position.y] != '1'))
                            stack.push(new Pair(position.x - 1, position.y));

                        if (!(position.y + 1 >= matrix[position.x].length || matrix[position.x][position.y + 1] != '1'))
                            stack.push(new Pair(position.x, position.y + 1));

                        if (!(position.y - 1 < 0 || matrix[position.x][position.y - 1] != '1'))
                            stack.push(new Pair(position.x, position.y - 1));

                    }
                    result++;
                }
            }
        }

        return result;
    }

    //This method reads the matrix from the file and then convert to 2D Character array and returns it.
    public static Character[][] read(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String satir = new String();
        String temp = br.readLine();

        if (temp == null)//if file is empty, return null
            return null;

        temp = temp.replace(" ", "");

        satir += temp;
        int column = temp.length();
        int row = 0;

        while (temp != null) {
            row++;
            temp = br.readLine();
            if (temp != null) {
                temp = temp.replace(" ", "");
                satir += temp;
            }
        }
        br.close();
        Character[][] matrix = new Character[row][column];
        //convert string to Character matrix form
        for (int i = 0; i < satir.length(); i++) {
            matrix[i / column][i % column] = satir.charAt(i);
        }

        return matrix;
    }


}
