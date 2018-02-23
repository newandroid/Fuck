package com.example.fullcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/11/23.
 */

public class SimpleContainer {
    public void tree() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("css", 28);
        map.put("csc", 21);
        map.put("ley", 60);
        map.put("chx", 60);
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string);
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }

    public void fill() {
        List<SimpleAddress> simpleAddresses = new ArrayList<>(Collections.nCopies(4, new SimpleAddress("my word")));
        System.out.println(simpleAddresses);
        Collections.fill(simpleAddresses, new SimpleAddress("your word"));
        System.out.println(simpleAddresses);
    }

    public void stack() {
        ArrayList<String> stackArray = new ArrayList<>();
        stackArray.add("my name");
        stackArray.add("my age");
        stackArray.add("my sex");
        String last = stackArray.remove(1);

        LinkedList<String> stackLinked = new LinkedList<>();
        stackLinked.add("my name");
        stackLinked.add("my age");
        stackLinked.add("my sex");
//        stackLinked.peekLast();
//        String last = stackLinked.removeLast();
        System.out.println("last:" + last);
        System.out.println(stackArray);
    }

    public class MyIner {
    }

}
