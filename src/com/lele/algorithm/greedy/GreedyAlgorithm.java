package com.lele.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * author: hwl
 * date: 2020/12/10 21:31
 * version: 1.0.0
 * modified by:
 * description:
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {

        // 创建广播电台，放入到map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        // 将各个电台放入到broadcasts
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

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        // 加入到 map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        // 定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // 定义一个maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        // 如果maxKey不为null，则会加入到selects
        String maxKey = null;
        while(allAreas.size() != 0) {// 如果allAreas不为0,则表示还没有覆盖到所有的地区

            maxKey = null;

            // 遍历broadcasts,取出对应key
            for (String key : broadcasts.keySet()) {

                tempSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 求出tempSet 和 allAreas 集合的交集
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖的地区的数量，比maxKey指向的集合为覆盖的地区还多
                // 需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            // maxKey != null
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区，从allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是：" + selects);// K1,K2,K3,K5
    }
















}
