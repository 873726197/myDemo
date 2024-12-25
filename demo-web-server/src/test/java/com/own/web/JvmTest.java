package com.own.web;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author liuChang
 * @date 2023/4/11 14:59
 * @describe
 */
public class JvmTest {

    public static void main(String[] args) {
        int a = 1;
        long b = 2L;


        ArrayList<Person> list = new ArrayList<>();
        while (true){
            list.add(new Person());
        }
//        Runnable task = () -> {
//            try {
//                while (true) {
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        };
//        new Thread(task).start();
//
//        while (true){
//
//        }

    }

    @Data
    static class Person {

    }
}
