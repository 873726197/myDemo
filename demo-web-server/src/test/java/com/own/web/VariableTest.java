package com.own.web;

import java.util.ArrayList;

/**
 * @author liuChang
 * @date 2024-06-18 14:31
 */
public class VariableTest {

    public static final String skipSchema = " ('information_schema', 'performance_schema', 'sys', 'mysql') ";

    static Integer count = 2;

    private static final Person person = new Person();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Person> list = new ArrayList<>();

        add(person,list);
        list.forEach(System.out::println);
//        send();
    }


    public static void send() throws InterruptedException {

        if (count >= 0){
            Thread.sleep(100);
            count --;
            System.out.println(count);
            send();
        }
    }

    public static void add(Person person, ArrayList<Person> list) throws InterruptedException {
        for (int i = 0; i < 20; i++){
            long l = System.currentTimeMillis();
            Thread.sleep(100);
            System.out.println("当前--"+l);
            list.add(person.setName(String.valueOf(l)));
        }
    }

}
