import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            if (args.length <= 0)
                System.out.println("Please insert the command line argument(txt file path).");
            else {
                Equation test = File_Reader.read(args[0]);

                if (!test.areParenthesBalanced())
                    System.out.println("Equation is not balanced. Please check the expression parentheses.");
                else {
                    System.out.println("Infix Form = " + test.getEquationInfix() + "\n");

                    System.out.println("Postfix Form = " + test.convertInfixToPostfix() + "\n");

                    System.out.printf("Result is %f\n", test.evaluatePostfix());
                }
            }
        } catch (IOException e) {
            System.out.println("Please check the command line argument(txt file path).");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
