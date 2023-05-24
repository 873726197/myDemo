package com.own.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.rholder.retry.*;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuChang
 * @date 2023/2/3 18:15
 * @describe
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) throws Exception {

        String s = "null";

//        filter();
//        Test test = new Test();
//        List<Test> list = new ArrayList<>();
//        List<Test> list1 = new ArrayList<>();
//        List<Test> list2 = new ArrayList<>();
//        List<Test> list3 = new ArrayList<>();
//        Test test1 = new Test();
//        Test test11 = new Test();
//        Test test12 = new Test();
//        test1.setName("张三");
//        test11.setName("zhangs");
//        test1.setList(list1);
//        list.add(test1);
//        list.add(test11);
//        list.add(test12);
//        Test test2 = new Test();
//        test2.setName("李四");
//        test2.setList(list2);
//        list1.add(test2);
//        Test test3 = new Test();
//        test3.setName("王五");
//        list2.add(test3);
//        test3.setList(list3);
//        test.setList(list);
//        test.setName("麻二");
//        List<Test> list4 = new ArrayList<>();
//        list4.add(test);
//
//        ArrayList<String> names = new ArrayList<>();
//        getAllNames(list4, names);

//        List<Person> list = new ArrayList<>();
//        list.add(new Person().setAge(18).setName("张胜男").setId(1));
//        list.add(new Person().setAge(20).setName("张三").setId(2));
//        list.add(new Person().setAge(7).setName("小红").setId(3));
//        list.add(new Person().setName("小黑").setId(3));

//        List<Person> collect = list.stream()
//                .sorted(Comparator.comparing(Person::getAge).reversed())
//                .sorted(Comparator.comparing(Person::getId))
//                .collect(Collectors.toList());
//        System.out.println(collect);

//        Person person = new Person().setAge(1).setChild(new Child().setId("12234"));
//        Field[] declaredFields = person.getClass().getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            Class<?> type = declaredField.getType();
//            if (!type.equals(String.class)) {
//                if (type.getDeclaredFields().length > 0) {
//
//                }
//            }
//            String name = declaredField.getName();
//            Object value = declaredField.get(person);
//
//        }

//        Map<String, Object> map = getFieldValueMap(person);
//        System.out.println(map);

//        List<Field> list1 = getAllFields(person.getClass());
//        System.out.println(list1);

//        Object o = retryTest();
//        System.out.println(JSONObject.toJSON(o));

        List<Child> list = new ArrayList<>();
        list.add(new Child().setAge(18).setName("张胜男").setId("1"));
        list.add(new Child().setAge(20).setName("张三").setId("2"));
        list.add(new Child().setAge(7).setName("小红").setId("3"));
        list.add(new Child().setName("小黑"));
        System.out.println(list.stream().sorted(Comparator.comparing(Child::getAge).reversed()).collect(Collectors.toList()));

//        variableParameter("www/%s/%s","aa","bb");

    }

    public static void variableParameter(String source, String... str) {
        System.out.printf((source) + "%n", (Object[]) str);
    }

    public static Object retryTest() throws Exception {

        Person person = new Person().setName("zhangsan");
        Retryer<Object> retry = RetryerBuilder
                .newBuilder()
                .retryIfException()
                .withWaitStrategy(WaitStrategies.incrementingWait(3, TimeUnit.SECONDS, 2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("dzyun:查询云盘第{}次重试", attempt.getAttemptNumber());
                        if (attempt.hasException()) {
                            log.error("dzyun:查询云盘第{}次重试异常,原因{}", attempt.getAttemptNumber(), attempt.getExceptionCause().toString());
                        }
                    }
                }).build();

        return retry.call(() -> {
            if (!person.getName().equals("xxx")) {
                throw new RuntimeException("名字不对");
            }

            person.setAge(18);
            return person;
        });
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return fields;
    }


    public static Map<String, Object> getFieldValueMap(Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (fieldValue != null) {
                if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                    map.put(field.getName(), fieldValue);
                } else {
                    map.put(field.getName(), getFieldValueMap(fieldValue));
                }
            }
        }
        return map;
    }


    //递归获取name
    public static void getAllNames(List<Test> list, List<String> names) {

        for (Test test : list) {
            if (StrUtil.isNotEmpty(test.getName())) {
                names.add(test.getName());
            }
            if (CollectionUtil.isNotEmpty(test.getList())) {
                getAllNames(test.getList(), names);
            }
        }

    }


    /**
     * 过滤器
     */
    private static void filter() {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(10).setName("zhangsan"));
        list.add(new Person().setAge(18).setName("xiaohong"));
        list.add(new Person().setAge(27).setName("xiaoli"));
        list.add(new Person().setAge(27).setName("lisi"));

        String s = "xiao";
        int age = 27;

        Stream<Person> s1 = list.stream()
                .filter(l -> l.getName().equals(s));

        if (Objects.nonNull(age)) {
            s1 = s1.filter(li -> li.getAge() == age);
        }

        List<Person> collect = s1.collect(Collectors.toList());
//        System.out.println(collect);

    }
}


@Data
@Accessors(chain = true)
class Person {
    private String name;
    private Integer age;
    private int id;

    private Child child;

    private boolean isSuccess;
}

@Data
@Accessors(chain = true)
class Child {
    private String name;

    private int age;

    private String id;

    private Double score;

}

@Data
class CTYunVolumeSnapshot {
    private String note;
    private String name;
    private String readyToUse;
    private String createDate;

}

@Data
class Test {
    private List<Test> list;

    private String name;
}
