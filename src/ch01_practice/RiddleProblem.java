package ch01_practice;

import java.util.ArrayList;

/**
 * 字谜
 */
public class RiddleProblem {

    /**
     * 直接枚举所有可能的组合，然后跟字典进行比较
     * @param input
     * @param dictionary
     * @return
     */
    public static ArrayList<String> solution1(char[][] input, String[] dictionary){
        ArrayList<String> result = new ArrayList<>();
        String str = "";

        // 行
        for(int i = 0; i < input.length; i++){
            // 列
            for(int j = 0; j < input[i].length; j++){

                // 遍历行
                str = "" + input[i][j];
                for(int k = j + 1; k < input[i].length; k++){
                    str += input[i][k];
                    for(int m = 0; m < dictionary.length; m++){
                        if(str.equals(dictionary[m])){
                            result.add(str);
                        }
                    }
                }
                str = "" + input[i][j];
                for(int k = j - 1; k >= 0; k--){
                    str += input[i][k];
                    for(int m = 0; m < dictionary.length; m++){
                        if(str.equals(dictionary[m])){
                            result.add(str);
                        }
                    }
                }

                // 遍历列
                str = "" + input[i][j];
                for(int k = i + 1; k < input.length; k++){
                    str += input[k][j];
                    for(int m = 0; m < dictionary.length; m++){
                        if(str.equals(dictionary[m])){
                            result.add(str);
                        }
                    }
                }
                str = "" + input[i][j];
                for(int k = i - 1; k >= 0; k--){
                    str += input[k][j];
                    for(int m = 0; m < dictionary.length; m++){
                        if(str.equals(dictionary[m])){
                            result.add(str);
                        }
                    }
                }

                //遍历对角线(+,+)
                str = "" + input[i][j];
                for(int m = i + 1, n = j + 1; m < input.length && n < input[m].length; m++, n++){
                    str += input[m][n];
                    for(int k = 0; k < dictionary.length; k++){
                        if(str.equals(dictionary[k])){
                            result.add(str);
                        }
                    }
                }
                //(-,-)
                str = "" + input[i][j];
                for(int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--){
                    str += input[m][n];
                    for(int k = 0; k < dictionary.length; k++){
                        if(str.equals(dictionary[k])){
                            result.add(str);
                        }
                    }
                }
                // (-,+)
                str = "" + input[i][j];
                for(int m = i - 1, n = j + 1; m >= 0 && n < input[m].length; m--, n++){
                    str += input[m][n];
                    for(int k = 0; k < dictionary.length; k++) {
                        if (str.equals(dictionary[k])) {
                            result.add(str);
                        }
                    }
                }
                //(+,-)
                str = "" + input[i][j];
                for(int m = i + 1, n = j - 1; m < input.length && n >= 0; m++, n--){
                    str += input[m][n];
                    for(int k = 0; k < dictionary.length; k++){
                        if(str.equals(dictionary[k])){
                            result.add(str);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        char[][] input = {
            {'t', 'h', 'i', 's'},
            {'w', 'a', 't', 's'},
            {'o', 'a', 'h', 'g'},
            {'f', 'g', 'd', 't'}
        };
        String[] dictionary = {"this", "two", "fat", "that"};
        ArrayList<String> result = solution1(input, dictionary);
        for(String item : result){
            System.out.println(item);
        }
    }
}
