package Greedy;
//贪心算法

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法解决集合问题
 * 问题场景如下：
 * 假设一些广播电台能够覆盖的地区如下：
 *  K1："北京","上海","天津"
 *  K2:"广州","北京","深圳"
 *  K3:"成都","上海","杭州"
 *  K4:"上海","天津"
 *  K5:"杭州","大连"
 *  现在要选择一些电台覆盖所有的区域，应当如何选择？
 */
public class Greedy {
    public static void main(String[] args) {
        //存放电台的集合
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
        //创建各个电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcast.put("K1",hashSet1);
        broadcast.put("K2",hashSet2);
        broadcast.put("K3",hashSet3);
        broadcast.put("K4",hashSet4);
        broadcast.put("K5",hashSet5);
        //保存所的城市
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放选择的结果集合
        ArrayList<String> select = new ArrayList<>();
        //存放临时的地区与电台的覆盖交集
        HashSet<String> temp = new HashSet<>();
        String maxKey = null;//每次置空

        while (allAreas.size() != 0){
          maxKey = null;
            for (String key : broadcast.keySet()){
                //每次清空这个
                temp.clear();
                HashSet<String> areas = broadcast.get(key);
                temp.addAll(areas);
                //取所的地区的集合和电台所覆盖的地区的交集
                temp.retainAll(allAreas);
                //找每一轮找覆盖地区最多的电台
                if (temp.size() > 0 && (maxKey == null || temp.size() > broadcast.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if (maxKey != null){
                select.add(maxKey);
                //在所有的城市集合里清除这个电台已经覆盖的地区
                allAreas.removeAll(broadcast.get(maxKey));
            }
        }
        System.out.println("贪婪算法选择的结果：" + select);
    }
}
