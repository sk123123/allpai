package com.allpai.music.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 10:38
 */
public class HashSetDemo {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("java");
        set.add("c");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            int str = iterator.next().hashCode();
            System.out.println(str+"str");
        }
        int j = set.hashCode();
        int i = j^(j>>>16);
        set.add("java");
        System.out.println(set+"hashCode:"+j);
    }
}
