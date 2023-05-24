package com.own.web.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuChang
 * @date 2023/4/13 14:31
 * @describe
 */
public class MainTest {

    public static void main(String[] args) {
        List<TimeFilterDetailDto> list = new ArrayList<>();
        list.add(new TimeFilterDetailDto().setPeriodTime("10,8").setSortNum(6).setIsEffect(false));
        list.add(new TimeFilterDetailDto().setDate("4-13").setDateEnums(DateEnums.MONTH).setSortNum(2).setIsEffect(false));
        list.add(new TimeFilterDetailDto().setDate("2023-4-13,2023-4-14").setDateEnums(DateEnums.YEAR).setSortNum(3).setIsEffect(false));
        list.add(new TimeFilterDetailDto().setDate("4,7").setDateEnums(DateEnums.WEEK).setSortNum(4).setIsEffect(true));
        list.add(new TimeFilterDetailDto().setDate("2023-4-13-4").setDateEnums(DateEnums.YEAR_TIME).setSortNum(5).setIsEffect(true));
        TimeChain timeChain = TimeProcessor.generatorHandlers(list);
        boolean b = TimeProcessor.doProcessor(timeChain);
        System.out.println(b);

    }

}
