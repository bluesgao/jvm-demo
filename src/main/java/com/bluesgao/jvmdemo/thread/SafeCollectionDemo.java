package com.bluesgao.jvmdemo.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SafeCollectionDemo {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("111");
        list.add("222");
        List safeList = Collections.synchronizedList(list);

        Iterator i = list.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}
