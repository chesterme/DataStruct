package ch03_text;

/**
 * 中缀表达式转为后缀表达式
 */
public class InfixToSuffix {

    private String input;
    private String[] handledInput;
    private MyLinkedStack<String> operatorStack; // 操作符栈
    private char[] allowedOperators; // 合法的操作符
    private char[] allowedValues; // 合法的操作数

    public InfixToSuffix(String input, char[] allowedOperators, char[] allowedValues){
        this.input = input;
        handledInput = null;
        operatorStack = new MyLinkedStack<>();
        this.allowedOperators = allowedOperators;
        this.allowedValues = allowedValues;
    }

    public InfixToSuffix(String input){
        this.input = input;
        handledInput = null;
        operatorStack = new MyLinkedStack<>();
        char[] temp1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.'};
        this.allowedValues = temp1;
        char[] temp2 = {'(', ')', '-', '+', '*', '/'};
        this.allowedOperators = temp2;
    }

    // 处理字符串
    public void handleInput(){
        // 剔除输入中两边的空白
        String temp = input.trim();
        // 以空白字符为分隔符，得到一个字符串数组
        handledInput = temp.split("(\\s)+");
    }

    // 处理表达式
    public String handleExpress(){
        handleInput();
        String output = "";
        for(int i = 0; i < handledInput.length; i++){
            // 如果是合法操作数则直接输出
            if(isAllowedValue(handledInput[i])){
                output += handledInput[i] + " ";
                continue;
            }
            // 如果是操作符
            if(isSymbol(handledInput[i])){
                // 若栈为空，则直接入栈
                if(operatorStack.isEmpty()){
                    operatorStack.push(handledInput[i]);
                    continue;
                }
                // 如果遇到左括号，则将其入栈
                if(handledInput[i].equals("(")){
                    operatorStack.push(handledInput[i]);
                    continue;
                }
                // 如果遇到右括号，表明括号内的中缀表达式已经扫描完成，将栈顶元素弹出至左括号为止
                if(handledInput[i].equals(")")){
                    while(!operatorStack.getTop().equals("(")){
                        output += operatorStack.pop() + " ";
                    }
                    operatorStack.pop(); // 将(弹出，但不显示
                    continue;
                }
                // 如果遇到运算符，则比较栈顶元素与该运算符的优先级
                if(isOperator(handledInput[i])){
                    int flag = compareOperator(handledInput[i]);
                    switch(flag){
                        // 该运算符的优先级 > 栈顶元素的优先级，则把该运算符压入栈中
                        case 1:
                            operatorStack.push(handledInput[i]);
                            break;
                        // 若该运算符的优先级 <= 栈顶元素的优先级，则把栈顶元素弹出并输出
                        // 重新比较栈顶元素，直到新操作符的优先级 > 栈顶元素为止
                        case 0:
                        case -1:
                        case -2:
                            // 处理当前栈顶元素是(的情况
                            if(operatorStack.getTop().equals("(")){
                                operatorStack.push(handledInput[i]);
                                continue;
                            }
                            do{
                                output += operatorStack.pop() + " ";
                                // 比如：( a * b / c + 2 )
                                if(operatorStack.isEmpty() || operatorStack.getTop().equals("(")){
                                    break;
                                }
                            }while(compareOperator(handledInput[i]) < 1);
                            operatorStack.push(handledInput[i]);
                            break;
                    }
                }
            }
        }
        // 在遍历完表达式后，若操作符栈不为空，则输入栈中全部内容
        while(!operatorStack.isEmpty()){
            output += operatorStack.pop() + " ";
        }
        return output;
    }

    // 判断是否是操作符
    public boolean isSymbol(String str){
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            boolean flag = false; // 假定在该位置上的字符不是合法字符
            for(int j = 0; j < allowedOperators.length; j++) {
                if (chars[i] == allowedOperators[j]) {
                    flag = true;
                }
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }

    // 判断字符串表示的内容是否是合法的操作数
    public boolean isAllowedValue(String str){
        char[] chars = str.toCharArray();
        if(chars[0] == '.' || (chars.length == 1 && ((chars[0] == '-') || (chars[0] == '+')))){
            return false;
        }
        int pointCount = 0;
        for(int i = 0; i < chars.length; i++){
            boolean flag = false; //假设该位置上的字符是非法字符
            for(int j = 0; j < allowedValues.length; j++){
                if(chars[i] == allowedValues[j]){
                    if(chars[i] == '.'){
                        pointCount++;
                    }
                    flag = true;
                }
                if(pointCount > 1 || (chars[i] == '-' && i > 0)){
                    flag = false;
                }
            }
            if(flag == false){
                return false;
            }
        }
        return true;
    }

    // 判断是否是操作符
    public boolean isOperator(String str){
        char[] arrowOperators = {'-', '+', '*', '/'};
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            boolean flag = false; // 假定在该位置上的字符不是合法字符
            for(int j = 0; j < arrowOperators.length; j++) {
                if (chars[i] == arrowOperators[j]) {
                    flag = true;
                }
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }

    // 比较操作符优先级
    public int compareOperator(String operator){
        String stackOperator = operatorStack.getTop();
        // +,-的优先级为1，*,/的优先级为2，(,)的优先级为3
        int operator1 = (stackOperator.equals("-") || stackOperator.equals("+")) ? 1 : ((stackOperator.equals("*") || stackOperator.equals("/")) ? 2 : 3);
        int operator2 = (operator.equals("-") || operator.equals("+")) ? 1 : ((operator.equals("*") || operator.equals("/")) ? 2 : 3);
        int flag = operator2 - operator1;
        return flag;
    }


}
