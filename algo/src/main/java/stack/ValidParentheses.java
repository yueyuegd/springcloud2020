package stack;

import java.util.Stack;

/**
 * LeetCode_20:判断括号是否有效
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        byte[] bytes = s.getBytes();
        //奇数个肯定为false
        if (bytes.length % 2 != 0) {
            return false;
        }
        Stack<Byte> stack = new Stack<>();
        for (int i = 0; i < bytes.length; i++) {
            //左括号，将其压入栈
            byte element = bytes[i];
            /*if ("(".getBytes()[0] == element || "[".getBytes()[0]== element || "{".getBytes()[0] == element) {
                stack.push(element);
            }*/
            if (stack.size() == 0) {
                stack.push(element);
            }
            //右括号，从栈顶取出一个左括号与其匹配，匹配上的话继续
            // 匹配不上的话或者栈中没有数据的话说明是非法格式
            else if (")".getBytes()[0] == element || "]".getBytes()[0]== element || "}".getBytes()[0] == element) {
                byte elementInStack = stack.peek();
                if (")".getBytes()[0] == element && "(".getBytes()[0] == elementInStack) {
                    stack.pop();
                }
                else if ("]".getBytes()[0] == element && "[".getBytes()[0] == elementInStack) {
                    stack.pop();
                }
                else if ("}".getBytes()[0] == element && "{".getBytes()[0] == elementInStack) {
                    stack.pop();
                }else {
                    return false;
                }

            } else {
                stack.push(element);
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
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
        s = "[";
        flag = validParentheses.isValid(s);
        System.out.println(flag);
        s = "((";
        flag = validParentheses.isValid(s);
        System.out.println(flag);

    }
}
