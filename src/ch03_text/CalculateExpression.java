package ch03_text;

/**
 * 表达式求值
 */
public class CalculateExpression {

    private String input; // 中缀表达式
    private String suffixExpression; // 后缀表达式
    private MyLinkedStack<String> valueStack; // 操作数栈
    private MyLinkedStack<String> operationStack; // 操作符栈
    private InfixToSuffix converter; // 后缀表达式转换器
    private String[] handleExpression; // 预处理的表达式

    public CalculateExpression(String input){
        this.input = input;
        converter = new InfixToSuffix(input);
        suffixExpression = converter.handleExpress();
        valueStack = new MyLinkedStack<>();
        operationStack = new MyLinkedStack<>();
    }

    // 表达式预处理
    public void handleExpression(){
        String temp = suffixExpression.trim(); // 除掉两边的空白
        handleExpression = temp.split("(\\s)+"); // 将字符串转化为以空白字符分割的字符串数组
    }

    // 判断是否是操作数
    public boolean isDigital(String str){
        char[] arrowedChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.'};
        char[] target = str.toCharArray();

        if(target[0] == '.'){
            return false;
        }

        //如果只有一位数，则排除单个:.,-的情况
        if(target.length == 1 && (target[0] == '.' || target[0] == '-')){
            return false;
        }

        // 统计特殊字符
        int pointCounter = 0;
        int negativeCounter = 0;
        for(int i = 0; i < target.length; i++){
            boolean flag = false; // 默认情况下该位置上不是有效的字符
            for(int j = 0; j < arrowedChars.length; j++){
                if(target[i] == arrowedChars[j]){
                    if(target[i] == '-') negativeCounter++;
                    if(target[i] == '.') pointCounter++;
                    flag = true;
                }
                if(negativeCounter > 1 || pointCounter > 1){
                    flag = false;
                }
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }

    // 判断是否是操作符
    public boolean isOperator(String str){
        char[] arrowedOperator = {'+', '-', '*', '/'};
        char[] target = str.toCharArray();

        for(int i = 0; i < target.length; i++){
            boolean flag = false; // 默认情况下该位置上不是有效的字符
            for(int j = 0; j < arrowedOperator.length; j++){
                if(target[i] == arrowedOperator[j]){
                    flag = true;
                }
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }

    // 计算表达式
    public double calculateExpress(){
        // 预处理后缀表达式
        handleExpression();
        for(int i = 0; i < handleExpression.length; i++){
            // 如果是操作数，则入操作数栈
            if(isDigital(handleExpression[i])){
                valueStack.push(handleExpression[i]);
                continue;
            }
            // 如果是操作符，则弹出两个操作数，进行运算，并把计算结果入操作数栈
            if(isOperator(handleExpression[i])){
                double value1 = Double.valueOf(valueStack.pop());
                double value2 = Double.valueOf(valueStack.pop());
                double temp = 0;
                switch (handleExpression[i]){
                    case "+":
                        temp = value2 + value1;
                        break;
                    case "-":
                        temp = value2 - value1;
                        break;
                    case "*":
                        temp = value2 * value1;
                        break;
                    case "/":
                        temp = value2 / value1;
                        break;
                }
                valueStack.push(String.valueOf(temp));
            }
        }
        return Double.valueOf(valueStack.pop());
    }

}
