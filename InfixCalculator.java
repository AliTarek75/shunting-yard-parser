import java.util.Scanner;

public class InfixCalculator {

    private static final String operations = "(+-*/)";
    private static final String precOrder = "+*";

    private static char equiv(char c) {
        if (c == '-') return '+';
        else if (c == '/') return '*';
        else return c;
    }

    private static int precedence(char c) {
        return precOrder.indexOf(equiv(c));
    }

    private static float calculate(char op, float v1, float v2) {
        switch (op) {
            case '+': return v1 + v2;
            case '-': return v1 - v2;
            case '*': return v1 * v2;
            case '/': return v1 / v2;
            default: return -1;
        }
    }

    private static boolean isOperator(char c) {
        return operations.indexOf(c) != -1;
    }

    public static float evaluateInfix(String expression) {

        // Converting to postfix    
        String[] stack = new String[expression.length()];
        int t = -1;

        String[] finalResult = new String[expression.length()];
        int i = 0;
        int len = expression.length();

        for (int j = 0; j < len; j++) {
            char c = expression.charAt(j);
            
            // Operator case
            if (isOperator(c)) {
                String opStr = Character.toString(c);
                
                // ( case, directly push
                if (t == -1 || c == '(') {
                    stack[++t] = opStr;
                    continue;
                }

                // ) case, pop until empty or (
                if (c == ')') {
                    while (t > -1 && !stack[t].equals("(")) {
                        finalResult[i++] = stack[t--];
                    }
                    t--; // skip the (
                    continue;
                }

                // Regular operators case
                while (t > -1 && !stack[t].equals("(") && precedence(c) <= precedence(stack[t].charAt(0))) {
                    finalResult[i++] = stack[t--];
                }
                stack[++t] = opStr;
            
            // Number case
            } else {

                // Reading the number
                int start = j;
                while (j < len && Character.isDigit(expression.charAt(j))) {
                    j++;
                }
                finalResult[i++] = expression.substring(start, j);
                j--; // Since the for loop will increase it by 1 by itself
            }
        }

        while (t > -1) {
            finalResult[i++] = stack[t--];
        }

        // Calculating the result from the postfix
        float[] calcStack = new float[i];
        int ct = -1;

        for (int j = 0; j < i; j++) {
            String token = finalResult[j];

            // operator case
            if (isOperator(token.charAt(0))) {
                char op = token.charAt(0);

                float v2 = calcStack[ct--];
                float v1 = calcStack[ct--];
                float result = calculate(op, v1, v2);

                calcStack[++ct] = result;

            // Number case
            } else {
                float value = Float.parseFloat(token);
                calcStack[++ct] = value;
            }
        }

        return calcStack[0];
    }   
    public static void main(String[] args) {
        System.out.println("Type a valid expression using +, -, *, /, (, or ). For example (7+11)*5 ");
        System.out.print("Expression: ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        expression = expression.replaceAll("\\s", "");
        System.out.print("Result: " + evaluateInfix(expression));
        scanner.close();
    }
}
