import java.util.StringTokenizer;

//used to keep the equationInfix, equationPostfix, variable and result
public class Equation {

    public Vars[] vars;
    private String equationInfix;
    private String equationPostfix;
    private double result;


    public Equation(int varNum) {
        vars = new Vars[varNum];
    }

    public static class Vars {

        public String name;
        public double value;

        public Vars(String name, double value) {
            this.name = name;
            this.value = value;
        }
    }

    public double getResult() {
        return result;
    }

    public String getEquationInfix() {
        return equationInfix;
    }

    public void setEquationInfix(String equationInfix) {
        this.equationInfix = equationInfix;
    }


    //Returns true if c1 and c2 are matching left and right Parenthesis
    private boolean isMatchPair(char c1, char c2) {
        if (c1 == '(' && c2 == ')')
            return true;
        else
            return false;
    }


    //Returns true if Infix expression has balanced Parenthesis
    public boolean areParenthesBalanced() {
        // Creates an empty character stack
        Stack<Character> stack = new Stack<Character>();

        // Traverse the given expression
        for (int i = 0; i < equationInfix.length(); i++) {

            //If the equationInfix.charAt(i) is a starting parenthesis then push it
            if (equationInfix.charAt(i) == '(')
                stack.push(equationInfix.charAt(i));

            // If equationInfix.charAt(i) is an ending parenthesis then pop from stack and check if the  popped parenthesis is a matching pair
            if (equationInfix.charAt(i) == ')') {

                // If we see an ending parenthesis without a pair then return false
                if (stack.isEmpty()) {
                    return false;
                }

                //Pop the top element from stack, if it is not a pair parenthesis of character,returns false This happens for expressions like x(y))
                else if (!isMatchPair(stack.pop(), equationInfix.charAt(i))) {
                    return false;
                }
            }

        }

        // If there is something left in expression then there is a starting parenthesis without a closing parenthesis

        if (stack.isEmpty())
            return true; /*balanced*/
        else {   /*not balanced*/
            return false;
        }
    }


    // Converts infix expression to postfix

    public String convertInfixToPostfix() {

        equationPostfix = "";  //equivalent postfix is empty initially

        // replace all variables name to value (x) 10.5  (y) 3  (w) -10.5 like that
        for (int i = 0; i < vars.length; ++i) {
            equationInfix = equationInfix.replaceAll(vars[i].name, Double.toString(vars[i].value));
        }

        // replace all function name to code name (cos) c  (sin) s  (abs) a like that
        equationInfix = equationInfix.replaceAll("cos", "c ");
        equationInfix = equationInfix.replaceAll("sin", "s ");
        equationInfix = equationInfix.replaceAll("abs", "a ");


        Stack<Character> stack = new Stack<>();  //stack to hold operands

        for (int i = 0; i < equationInfix.length(); i++) {
            char temp = equationInfix.charAt(i);  //symbol to be processed
            if (isOperator(temp) && equationInfix.charAt(i + 1) == ' ') {  //if a operator
                //repeatedly pops if stack top has same or higher precedence
                while (!stack.isEmpty() && checkPrecedence(temp, stack.peek())) {
                    equationPostfix += stack.pop();
                }
                stack.push(temp);
            } else if (temp == '(') {
                stack.push(temp);  //push if left parenthesis
            } else if (temp == ')') {
                //repeatedly pops if right parenthesis until left parenthesis is found
                while (!stack.isEmpty() && stack.peek() != '(') {
                    equationPostfix += stack.pop();
                }
                stack.pop();
            } else {
                equationPostfix += temp;
            }
        }

        //pops all elements of stack left
        while (!stack.isEmpty()) {
            equationPostfix += stack.pop();
        }

        equationPostfix = equationPostfix.trim();//first and end blank spaces are deleting


//this part reediting postfix expression
        for (int i = 0; i < equationPostfix.length() - 1; ++i) {

            if (isOperator(equationPostfix.charAt(i)) && isOperator(equationPostfix.charAt(i + 1))) {
                String temp;

                temp = equationPostfix.substring(0, i + 1);
                temp += " ";
                temp += equationPostfix.substring(i + 1);
                equationPostfix = temp;
            }

            if (equationPostfix.charAt(i) != ' ' && !isOperator(equationPostfix.charAt(i)) && isOperator(equationPostfix.charAt(i + 1))) {
                String temp;

                temp = equationPostfix.substring(0, i + 1);
                temp += " ";
                temp += equationPostfix.substring(i + 1);
                equationPostfix = temp;
            }
        }

        while (equationPostfix.contains("  ")) {
            equationPostfix = equationPostfix.replaceAll("  ", " ");
        }

        //If the operand at the end of the expression is not separated by a space, it adds a space in front of it.
        if (isOperator(equationPostfix.charAt(equationPostfix.length() - 1)) && equationPostfix.charAt(equationPostfix.length() - 2) != ' ') {
            String temp;

            temp = equationPostfix.substring(0, equationPostfix.length() - 1);
            temp += " ";
            temp += equationPostfix.substring(equationPostfix.length() - 1);
            equationPostfix = temp;
        }


        return equationPostfix;
    }


    //Checks if the input is operator or not

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == 'c' || c == 's' || c == 'a')
            return true;
        return false;
    }


    //return true if c2 has same or higher precedence than c1

    private boolean checkPrecedence(char c1, char c2) {
        if ((c2 == '+' || c2 == '-') && (c1 == '+' || c1 == '-'))
            return true;
        else if ((c2 == '*' || c2 == '/') && (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
            return true;
        else if ((c2 == 'c' || c2 == 's' || c2 == 'a') && (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/' || c1 == 'c' || c1 == 's' || c1 == 'a'))
            return true;
        else
            return false;
    }

    public static boolean isDouble(String word) {
        try {
            Double.parseDouble(word);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    // Method to evaluate value of a postfix expression
    public double evaluatePostfix() {
        //create a stack
        Stack<Double> stack = new Stack<>();
        StringTokenizer postfix = new StringTokenizer(equationPostfix);
        double valLeft;
        double valRight;

        // Scan all word one by one
        while (postfix.hasMoreTokens()) {
            String word = postfix.nextToken();

            // If the scanned character is an operand (convertable double value),
            // push it to the stack.
            if (isDouble(word))
                stack.push(Double.parseDouble(word));

                //  If the word is an operator, pop two or one (abs cos sin one element) (+ - * / two element)
                // elements from stack apply the operator
            else {

                if (word.equals("a") || word.equals("c") || word.equals("s")) {
                    valRight = stack.pop();
                    valLeft = 0;
                } else {
                    valRight = stack.pop();
                    valLeft = stack.pop();
                }

                switch (word) {
                    case "+":
                        stack.push(valLeft + valRight);
                        break;

                    case "-":
                        stack.push(valLeft - valRight);
                        break;

                    case "/":
                        stack.push(valLeft / valRight);
                        break;

                    case "*":
                        stack.push(valLeft * valRight);
                        break;

                    case "a":
                        stack.push(abs(valRight));
                        break;

                    case "c":
                        stack.push(cos(valRight));
                        break;

                    case "s":
                        stack.push(sin(valRight));
                        break;
                }
            }
        }
        return result = stack.pop();
    }

    public static double sin(double n)//degree
    {
        double denom, sinx;

        n %= 360; //2pi repeating

        // Converting degrees to radian
        n *= 0.017453292519943295;// pi/180 constant value

        double x = n;

        // maps the sum along the series
        sinx = n;

        int i = 1;
        do {
            denom = 2 * i * (2 * i + 1);
            x = -x * n * n / denom;
            sinx = sinx + x;
            i++;
        } while (i < 25);

        return sinx;
    }

    public static double cos(double n)//degree
    {
        double denom, cosx;

        n %= 360;//2pi repeating

        // Converting degrees to radian
        n *= 0.017453292519943295;// pi/180 constant value

        double x = 1;

        // maps the sum along the series
        cosx = x;

        int i = 1;
        do {
            denom = 2 * i * (2 * i - 1);
            x = -x * n * n / denom;
            cosx = cosx + x;
            i++;
        }
        while (i < 25);

        return cosx;
    }

    public static double abs(double n) {
        if (n <= 0.0)
            return 0.0 - n;
        else
            return n;

    }
}