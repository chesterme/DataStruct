package ch02_text;

import java.util.ArrayList;
import java.util.Random;

/**
 * 求解最大序列和问题的求解
 */
public class MaxSubSum {

    /**
     * 枚举所有可能
     */
    public static int maxSubSum1(int[] input){
        int maxSum = 0;
        int index = 0;
        for(int i = 0; i < input.length; i++){
            for(int j = i; j < input.length; j++){
                int thisSum = 0;
                for(int k = i; k <= j; k++){
                    thisSum += input[k];
                }
                if(maxSum < thisSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 枚举所有可能
     * @param input
     * @return
     */
    public static int maxSubSum2(int[] input){
        int maxSum = 0;
        int start = 0;
        for(int i = 0; i < input.length; i++){
            int thisSum = 0;
            for(int j = i; j < input.length; j++){
                thisSum += input[j];
                if(thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubSum3(int[] a){
        return maxSumRec(a, 0, a.length - 1);
    }

    // 分治方法
    public static int maxSumRec(int[] a, int left, int right){
        if(left == right){
            if(a[left] > 0){
                return a[left];
            }else{
                return 0;
            }
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, 0, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        // 得到中间部分的左边的最大子列和
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for(int i = center; i >= 0; i--){
            leftBorderSum += a[i];
            if(leftBorderSum > maxLeftBorderSum){
                maxLeftBorderSum = leftBorderSum;
            }
        }

        // 得到中间部分的右边的最大子列和
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for(int i = center + 1; i <= right; i++){
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum){
                maxRightBorderSum = rightBorderSum;
            }
        }

        // 获取左，中，右三者中最大者
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    public static int max3(int a, int b, int c){
        int max = a;
        if(max < b){
            max = b;
        }
        if(max < c){
            max = c;
        }
        return max;
    }

    // 联机方法
    public static int maxSubSum4(int[] a){
        int maxSum = 0, thisSum = 0;
        for(int i = 0; i < a.length; i++){
            thisSum += a[i];
            if(thisSum > maxSum){
                maxSum = thisSum;
            // 如果在[i,j]之间的和为负数，则舍弃它，因为该负数对后面求最大子列和没有任何作用
            }else if(thisSum < 0){
                thisSum = 0;
            }
        }
        return maxSum;
    }




    public static void main(String[] args){
        int[] input = new int[10];
        int[] output = new int[10];
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for(int i = 0; i < 10; i++){
            int value = random.nextInt(100);
            if(value % 2 == 0){
                value *= -1;
            }
            input[i] = value;
            System.out.print(input[i] + " ");
        }
        System.out.println("\n-----------");

        System.out.println("最大子列和为：" + maxSubSum2(input));
        System.out.println("最大子列和为：" + maxSubSum3(input));
        System.out.println("最大子列和为：" + maxSubSum4(input));
    }
}
