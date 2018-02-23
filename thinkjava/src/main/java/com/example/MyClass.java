package com.example;

import com.example.generics.LinkStack;

import java.lang.reflect.Method;

public class MyClass {

    public void testReflect(){
        try {
            BaseObject baseObject = new BaseObject();
            Method method = BaseObject.class.getDeclaredMethod("setFakeInt",int.class);
            method.setAccessible(true);
            System.out.println("getFakeInt:"+baseObject.getFakeInt());
            method.invoke(baseObject,5);
            System.out.println("getFakeInt:"+baseObject.getFakeInt());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testInit(){
//       Class haha =  BaseObject.class;
        try {
            Class haha =  Class.forName("com.example.BaseObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testGenerics(){
        LinkStack<String> stringLinkStack = new LinkStack<>();
        for (String s:"my name is css".split(" ")){
            stringLinkStack.push(s);
        }
        String s;
        while ((s= stringLinkStack.pop())!=null) System.out.println(s);
    }

}
