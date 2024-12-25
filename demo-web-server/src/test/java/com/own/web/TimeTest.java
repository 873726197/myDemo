package com.own.web;

import com.own.web.chain.TimeFilterDetailDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuChang
 * @date 2023/4/12 10:36
 * @describe
 */
public class TimeTest {

    public static final int[] notExistsHour = {7, 8, 9, 10};
    public static final int notEffectMonth = 3;
    public static final int notEffectDay = 15;
    public static final String notEffectDate = "20231111";
    public static final String[] existsWeek = {"SATURDAY", "SUNDAY"};

    public static void main(String[] args) {
//        LocalDateTime time = geTime("2023-03-16 11:11:11");
//        System.out.println(LocalDateTime.now().getDayOfWeek().getValue());
//        System.out.println(isEffect(time));
        List<TimeFilterDetailDto> list = new ArrayList<>();
        list.add(new TimeFilterDetailDto().setPeriodTime("18,8").setSortNum(1));
        list.add(new TimeFilterDetailDto().setDate("2023-4-13").setSortNum(2));
    }

    /**
     * 是否生效
     *
     * @param time 时间
     * @return boolean
     */
    public static boolean isEffect(LocalDateTime time) {
        TimeFilterDetailDto timeFilterDetailDto = new TimeFilterDetailDto();
        String times = timeFilterDetailDto.getPeriodTime();
        String[] split = times.split(",");
        boolean hour = Arrays.stream(split).anyMatch(h -> h.equals(String.valueOf(time.getHour())));

        //如果符合第一级条件直接返回 1.每天7，8，9，10点不生效
        if (hour) {
            return false;
        } else {
            int year = time.getYear();
            int monthValue = time.getMonthValue();
            int dayOfMonth = time.getDayOfMonth();
            //如果符合第二级条件直接返回 2.每年3月15 全天不生效
            if (notEffectMonth == monthValue && notEffectDay == dayOfMonth) {
                return false;
            }
            //如果符合第三级条件直接返回 3.20231111 不生效
            if (notEffectDate.equals(String.valueOf(year) + monthValue + dayOfMonth)) {
                return false;
            }
            //如果符合第四级条件直接返回 4.每周六周日全天生效
            boolean week = Arrays.stream(existsWeek).anyMatch(w -> w.equals(time.getDayOfWeek().name()));
            if (week) {
                return true;
            }
        }
        return true;
    }

    public static LocalDateTime geTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
