package com.own.common.utils;

import com.own.common.enums.CodeEnums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuChang
 * @date 2023/2/24 10:34
 * @describe 反射工具类
 */

public class ReflectUtils {


    public static List<String> getFieldsByObject(Class<?> clazz){

        List<String> names = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            String name = field.getName();
            names.add(name);
        }

        return names;
    }

    public static void main(String[] args) {

        CodeEnums code1 = CodeEnums.s;
        switch (code1){
            case f:
                System.out.println("!111");
                break;
            default:
                break;
        }
    }
}
