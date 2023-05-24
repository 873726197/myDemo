package com.own.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuChang
 * @date 2023/4/12 14:42
 * @describe
 */
@Getter
@AllArgsConstructor
public enum CodeEnums {

    s(1,"1"),
    f(2,"2");

    public final int code;
    private final String msg;

//    public static int getCode1(int code){
//        CodeEnums codeEnums = Arrays.stream(CodeEnums.values()).filter(c -> c.getCode() == code).findFirst().orElse(null);
//        assert codeEnums != null;
//        return codeEnums.getCode();
//    }

    public static void main(String[] args) {

        CodeEnums code1 = CodeEnums.s;
        switch (code1){
            case s:
                System.out.println("!111");
                break;
            default:
                break;
        }
    }
}
