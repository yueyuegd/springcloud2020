package stack;

import java.util.Stack;

public class ValidParentheses3 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (stack.size() == 0) {
                stack.push(aChar);
            } else if (isSym(stack.peek(), aChar)) {
                stack.pop();
            } else {
                stack.push(aChar);
            }
        }
        return stack.size() == 0;
    }

    private boolean isSym(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }

    public static void main(String[] args) {
        ValidParentheses3 validParentheses = new ValidParentheses3();
        String s = "()";
        boolean flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "()[]{}";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "(]";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "([)]";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "{[]}";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        /*s = "[";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "((";
        flag = validParentheses.isValid(s);
        System.out.println(flag);*/

    }
}
