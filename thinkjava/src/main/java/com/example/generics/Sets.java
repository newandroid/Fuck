package com.example.generics;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/21.
 */

public class Sets {
    public static <T> Set<T> union(Set<T> a, Set<T> b){
        HashSet hashSet = new HashSet<>(a);
//        if (hashSet instanceof Collection)
        EnumSet enumSet = EnumSet.copyOf(hashSet);
       return EnumSet.copyOf(hashSet);
    }
}
