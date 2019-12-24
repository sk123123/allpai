package com.allpai.music.collection;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 10:34
 * java集合框架
 */
public class CollectionDemo {
    public static void main(String[] args) {
        Collection collection = new HashSet();
        String s1 = new String("1") + new String("2");
        String s2 = "12";
        s1.intern();

        boolean falg = (s1==s2) ;
        boolean falg1 = (s1.equals(s2)) ;
        System.out.println(s1+"falg:"+falg);
        System.out.println("falg1:"+falg1);
        Integer i=100,j=100;
        System.out.println(i==j);
    }
}
