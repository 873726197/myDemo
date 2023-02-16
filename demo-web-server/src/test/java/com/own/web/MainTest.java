package com.own.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuChang
 * @date 2023/2/3 18:15
 * @describe
 */
public class MainTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(10).setName("zhangsan"));
        list.add(new Person().setAge(18).setName("xiaohong"));
        list.add(new Person().setAge(27).setName("xiaoli"));
        list.add(new Person().setAge(27).setName("lisi"));

        String s = "xiao";
        int age = 27;

        Stream<Person> s1 = list.stream()
                .filter(l -> l.getName().contains(s));

        if (Objects.nonNull(age)) {
            s1 = s1.filter(li -> li.getAge() == age);
        }

        List<Person> collect = s1.collect(Collectors.toList());
        System.out.println(collect);

        short sh1 = 1;
        sh1 = (short) (sh1 + 1);

        Integer i1 = 100, i2 = 100, i3 = 150, i4 = 150;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}

@Data
@Accessors(chain = true)
class Person {
    private String name;
    private int age;
}
