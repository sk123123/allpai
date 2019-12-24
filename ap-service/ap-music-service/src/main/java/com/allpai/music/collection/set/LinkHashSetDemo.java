package com.allpai.music.collection.set;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 10:25
 */
public class LinkHashSetDemo {
    public static void main(String[] args) {
        Set linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("java");
        Iterator<Object> iterator= linkedHashSet.iterator();
        while (iterator.hasNext()){
            Object str = iterator.next();
            System.out.println(str.toString());
        }
    }
}
