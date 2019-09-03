package ch04_text;

import ch03_text.InfixToSuffix;
import ch03_text.MyLinkedStack;

import java.util.Scanner;

/**
 * 表达式树
 */
public class MyExpressTree{

    private String input; // 后序表达式
    private MyLinkedStack<MyLinkedBinaryTree<String>> stack;
    private String[] allowedOperators = {"+", "-", "/", "*"};


    public MyExpressTree(String input){
        this.input = input;
        stack = new MyLinkedStack<>();
        generateExpressTree();
    }

    public void generateExpressTree(){
        // 遍历整个后序表达式
        String[] express = input.trim().split("(\\s)+");
        for(int i = 0; i < express.length; i++){
            MyLinkedBinaryTree<String> node = new MyLinkedBinaryTree<>();
            node.create(express[i]); // 创建一个节点
            // 如果是操作符
            if(isOperator(express[i]) && !stack.isEmpty()){
                MyLinkedBinaryTree<String> rightTree = stack.pop();
                MyLinkedBinaryTree<String> leftTree = stack.pop();
                node.merge(leftTree, rightTree);
            }
            stack.push(node);
        }
        MyLinkedBinaryTree<String> tree = stack.pop();
        tree.preOrderTraversal(); // 先序遍历
    }

    public boolean isOperator(String str){
        boolean result = false;
        for(int i = 0; i < allowedOperators.length; i++){
            if (allowedOperators[i].equals(str)) {
                result = true;
            }
        }
        return result;
    }

    public static void main(String[] args){

//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        String input = "a + b * c + ( d * e + f ) * g";

        // 中缀表达式转化成后缀表达式
        char[] allowedOperators = {'+', '-', '*', '/'};
        char[] allowedValues = new char[26];
        for(int i = 0; i < 26; i++){
            allowedValues[i] = (char)('a' + i);
        }
        InfixToSuffix solution = new InfixToSuffix(input, allowedOperators, allowedValues);
        input = solution.handleExpress();
        System.out.println(input);

//        String input = "a b c * + d e * f + g * +";
        MyExpressTree tree = new MyExpressTree(input);
    }
}
