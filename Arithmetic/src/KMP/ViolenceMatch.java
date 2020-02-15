package KMP;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "dolcke you li ke i like java";
        String str2 = "lcike";
        int index = violenceMatch(str1,str2);
        System.out.println(index);
    }
    //字符串匹配的问题
    //传统的暴力匹配算法
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int len1 = s1.length;
        int len2 = s2.length;
        int i = 0; //指向s1
        int j = 0; //指向 s2
        while (i < len1 && j < len2){
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else {
                i -= j - 1;
                j = 0;
            }
        }
        if(j == len2){
            return i - j;
        }else {
            return -1;
        }
    }
}
