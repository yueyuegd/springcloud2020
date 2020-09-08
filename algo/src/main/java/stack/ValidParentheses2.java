package stack;

public class ValidParentheses2 {

    public static void main(String[] args) {

        ValidParentheses2 validParentheses = new ValidParentheses2();
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

    public boolean isValid(String s) {
        if("".equals(s)){
            return true;
        }
        String[] split = s.split("");
        if(split.length%2!=0){
            return false;
        }
        while (split.length>0){
            String one = split[0];
            String two = split[1];
            if(checkStr(one,two)){
                if(split.length==2){
                    return true;
                }
                String substring = s.substring(2);
                isValid(substring);
            }
            String end = split[split.length-1];
            if(checkStr(one,end)){
                if(split.length==2){
                    return true;
                }
                String substring = s.substring(1,split.length-1);
                isValid(substring);
            }
            return false;
        }
        return false;
    }

    private boolean checkStr(String one,String two){
        if(one.equals("(") && two.equals(")")){
            return true;
        }
        if(one.equals("[") && two.equals("]")){
            return true;
        }
        if(one.equals("{") && two.equals("}")){
            return true;
        }
        return false;
    }


}
