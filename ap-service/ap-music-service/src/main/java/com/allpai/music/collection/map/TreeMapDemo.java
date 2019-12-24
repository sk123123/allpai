package com.allpai.music.collection.map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 11:10
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        Map treeMap = new TreeMap();
        treeMap.put("a","aaa");
        treeMap.put("d","ddd");
        treeMap.put("b","bbb");
        treeMap.put("c","ccc");
        Set<Map.Entry<String,String>> entrySet =  treeMap.entrySet();
        for(Map.Entry<String,String> entry : entrySet){
            System.out.println(entry.getKey() + "========" + entry.getValue());
        }
    }
}
