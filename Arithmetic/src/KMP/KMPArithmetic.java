package KMP;

import java.util.Arrays;

//KMP算法是一种字符串匹配算法
public class KMPArithmetic {
    /**
     * 1) KMP 是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
     * 2) Knuth-Morris-Pratt 字符串查找算法，简称为“KMP 算法”，
     *     常用于在一个文本串S内查找-一个模式串P的出现位置，
     * 这个算法由Donald Knuth、Vaughan Pratt、James H. Moris三人于1977年联合发表，
     *     故取这3人的姓氏命名此算法.
     * 3) KMP 方法算法就利用之前判断过信息，通过- -个next数组
     *     保存模式串中前后最长公共子序列的长度，
     *     每次回溯时，通过next数组找到，前面匹配过的位置，省去了大量的计算时间
     */
    public static void main(String[] args) {
        String str2 = "ABCDABD";
//        int[] next = kmpNext(str2);
//        System.out.println(Arrays.toString(next));
//        int index = kmpSearch(str1,str2,next);
//        System.out.println("index = " + index);

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        str1 =  replace(str1,"AB","ab");
        System.out.println(str1);
    }

    /**
     * kmp搜索算法
     * @param str1 原字符串
     * @param str2 需要匹配的子串
     * @param next 子串的部分匹配值表
     * @return 返回字符串开始匹配位置的下标，如果不匹配，则返回一个非法下标，即-1
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //需要考虑str1.charAt(i) ！= str2.charAt(j)时j的位置要重置

            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //得到子串的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建部分匹配值表
        int[] next = new int[dest.length()];
        next[0] = 0;
        //这个kmp部分匹配表确实有点看不懂
        for (int i = 1,j = 0; i < next.length; i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    //实现方法 replace, 能够替换字符串中的某个部分

    /**
     * s1中的子串s2用字符串rep替换掉
     * @param s1 原来的字符串
     * @param s2 需要将原来的字符串中的子串替换
     * @param rep 要替换的字符串
     */
    public static String replace(String s1,String s2,String rep){
        //首先使用KMP算法找出要替换的位置
        int[] next = kmpNext(s2);
        int index = kmpSearch(s1,s2,next);
        while (index != -1) {
            s1 = s1.substring(0, index) + rep + s1.substring(index + s2.length());
            next = kmpNext(s2);
            index = kmpSearch(s1,s2,next);
        }
        return s1;
    }
}
