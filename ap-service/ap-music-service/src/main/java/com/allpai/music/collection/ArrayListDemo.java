package com.allpai.music.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 10:37
 * list集合:元素可重复、有序
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        //ArrayList无参构造函数 size默认为0
        List arrayList = new ArrayList(1);
        //首次添加元素
        arrayList.add("a");
//        arrayList.remove(1);
        arrayList.add("b");
//        arrayList.add("a");
        System.out.println(arrayList.size()+"左移后的值"+(22>>1));

        List vector = new Vector();

    }
}
