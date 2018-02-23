package com.example.generics;

/**
 * Created by Administrator on 2017/11/22.
 */

public class NonCovariantGenerics {
    public NonCovariantGenerics() {
//        List<Fruit> fruits = new ArrayList<Apple>();
//        Apple.set(new Apple());
    }

    class Fruit{}
    class Apple extends Fruit{}
}
