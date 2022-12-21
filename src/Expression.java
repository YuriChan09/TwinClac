import CustomExceptions.DivideZero;
import CustomExceptions.Overflow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class Expression {
    String expression;

    public Expression(String raw_expression) {
        this.expression = raw_expression;
    }

    private String symbol_cleaner(String str) {
        str = str.replace("×", "*").replace("÷", "/");
        if (str.endsWith("+")||str.endsWith("-")||str.endsWith("*")||str.endsWith("/"))
            str = str.substring(0,str.length()-1);
        return str;
    }

    private String add_sub_cleaner(String str) {
        str = str.replace("+-", "-").replace("--", "+").replace("++", "+").replace("-+", "-");
        return str;
    }

    private String mul_div_cleaner(String str) {
        while (str.contains("*-") || str.contains("/-")) {

            int n1 = str.indexOf("*-");
            int n2 = str.indexOf("/-");
            int range = (n1 == -1 || n2 == -1) ? n1 + n2 + 1 : Math.min(n1, n2);
            int index = Math.max(str.lastIndexOf("+", range), str.lastIndexOf("-", range));

            if (index == -1) {

                StringBuilder str_builder = new StringBuilder(str);
                str_builder.delete(range + 1, range + 2);
                str_builder.insert(0, "-");
                str = str_builder.toString();

            } else if (str.charAt(index) == '+') {

                StringBuilder str_builder = new StringBuilder(str);
                str_builder.delete(range + 1, range + 2);
                str_builder.setCharAt(index, '-');
                str = str_builder.toString();

            } else if (str.charAt(index) == '-') {
                StringBuilder str_builder = new StringBuilder(str);
                str_builder.delete(range + 1, range + 2);
                if (index == 0)
                    str_builder.delete(0, 1);
                else
                    str_builder.setCharAt(index, '+');
                str = str_builder.toString();
            }
        }
        return str;
    }

    private String cleaner(String str) {
        str = symbol_cleaner(str);
        str = mul_div_cleaner(str);
        str = add_sub_cleaner(str);
        if (str.startsWith("-"))
            str = "0" + str;
        return str;
    }

    private int getPriority(Character op) {
        switch (op) {
            case '+', '-' -> {
                return 1;
            }
            case '*', '/' -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }

    private BigDecimal operate(BigDecimal a, BigDecimal b, Character c) throws DivideZero, Overflow {
        BigDecimal result = new BigDecimal("0");
        switch (c) {
            case '+' -> {
                result = a.add(b);
            }
            case '-' -> {
                result = a.subtract(b);
            }
            case '*' -> {
                result = a.multiply(b);
            }
            case '/' -> {
                if (b.equals(BigDecimal.ZERO)){
                    throw new DivideZero();
                };
                result = a.divide(b, 20, RoundingMode.HALF_UP);
            }

            }
        if (result.compareTo(new BigDecimal(1000000000)) >= 0 ) {
            throw new Overflow();
        }
        return result;
    }

    private String evaluate(String str) throws DivideZero, Overflow {
        // 记录当前位置的指针
        int i;
        // 操作数栈
        Stack<BigDecimal> nums = new Stack<>();
        // 运算符栈
        Stack<Character> ops = new Stack<>();

        for (i = 0; i < str.length(); i++) {

            // 如果遇到数字，压入nums栈
            if (Character.isDigit(str.charAt(i))) {
                StringBuilder e = new StringBuilder();

                // 读取后位数字，并移动指针
                while (i < str.length() && (Character.isDigit(str.charAt(i))||str.charAt(i)=='.')) {
                    e.append(str.charAt(i));
                    i++;
                }
                // 入栈
                BigDecimal num = new BigDecimal(e.toString());
                nums.push(num);

                // 指针到达运算符，需要回退一位
                i--;
            }
            // 如果遇到运算符，进行完ops栈内优先级更高或同级的运算，直到栈空，然后再入栈
            else {
                while (!ops.empty() && getPriority(ops.peek())>=getPriority(str.charAt(i))){

                    BigDecimal num2 = nums.pop();
                    BigDecimal num1 = nums.pop();
                    Character op = ops.pop();

                    nums.push(operate(num1, num2, op));


                }
                ops.push(str.charAt(i));
            }


        }
        // 处理栈中剩余的数
        while(!ops.empty()){

            BigDecimal num2 = nums.pop();
            BigDecimal num1 = nums.pop();
            Character op = ops.pop();

            nums.push(operate(num1,num2,op));
        }
        return nums.peek().setScale(9,RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public String get_eval() throws DivideZero, Overflow {
        String expr = this.cleaner(this.expression);
        return this.evaluate(expr);
    }
}
