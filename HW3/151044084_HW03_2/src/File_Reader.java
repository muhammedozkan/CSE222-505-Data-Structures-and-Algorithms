import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class File_Reader {
    //Expression read from file and then it calls another method divideData().
    // This method also return Equation object
    public static Equation read(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String temp = br.readLine();

        if (temp == null)//if file is empty, return null
            return null;

        int row = 0;

        while (temp != null) {
            row++;
            temp = br.readLine();
        }
        br.close();
        String[] data = new String[row];

        br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < row; i++) {
            data[i] = br.readLine();
        }
        br.close();

        return divideData(data);
    }

    //String array convert to equation object
    private static Equation divideData(String[] data) {
        Equation equ = new Equation(data.length - 2);//Why -2? because one blank line and one line expression

        equ.setEquationInfix(data[data.length - 1]);//The last line contains the expression

        for (int i = 0; i < data.length - 2; ++i) {
            int index = data[i].indexOf("=");
            String varName = data[i].substring(0, index).replace(" ", "");
            double varValue = Double.parseDouble((data[i].substring(index + 1).replace(" ", "")));

            equ.vars[i] = new Equation.Vars(varName, varValue);
        }

        return equ;
    }
}
