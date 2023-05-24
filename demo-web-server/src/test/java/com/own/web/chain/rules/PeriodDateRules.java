package com.own.web.chain.rules;

import com.own.web.chain.DateEnums;
import com.own.web.chain.Handler;
import com.own.web.chain.HandlerChain;
import com.own.web.chain.TimeFilterDetailDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author liuChang
 * @date 2023/4/12 17:48
 * @describe 具体日期规则
 */

@Data
@Accessors(chain = true)
public class PeriodDateRules implements Handler {

    private TimeFilterDetailDto timeFilterDetailDto;

    private final String comma = ",";
    private final String bar = "-";

    @Override
    public Integer order() {
        return this.timeFilterDetailDto.getSortNum();
    }

    @Override
    public void doHandler(TimeFilterDetailDto isEffect, HandlerChain handlerChain) {

        LocalDateTime now = LocalDateTime.now();
        int nowWeek = now.getDayOfWeek().getValue();
        int nowYear = now.getYear();
        int nowMonth = now.getMonthValue();
        int nowDay = now.getDayOfMonth();
        int nowHour = now.getHour();
        DateEnums dateEnums = timeFilterDetailDto.getDateEnums();
        String date = timeFilterDetailDto.getDate();
        switch (dateEnums) {
            case WEEK:
                if(timeFilterDetailDto.getFrequency().equals("1")){

                }
                //每周六，周日 全天 生效
                String[] split = date.split(comma);
                boolean b = Arrays.stream(split).anyMatch(w -> Integer.parseInt(w) == nowWeek);
                isEffect.setResult(timeFilterDetailDto.getIsEffect() == b);
                break;
            case MONTH:
                //每年3月15日全天不生效
                String[] md = date.split(bar);
                String month = md[0];
                String day = md[1];
                if (Integer.parseInt(month) == nowMonth && Integer.parseInt(day) == nowDay) {
                    isEffect.setResult(timeFilterDetailDto.getIsEffect());
                }
                break;
            case YEAR:
                //2023年11月11日,2023年12月12日 全天 不生效
                yearRule(date, nowYear, nowMonth, nowDay, nowHour, false, isEffect);
                break;
            case YEAR_TIME:
                //2023年5月4日,2023年5月7日 04点生效
                yearRule(date, nowYear, nowMonth, nowDay, nowHour, true, isEffect);
                break;
        }
        handlerChain.doHandler(isEffect);
    }

    private void yearRule(String date, int nowYear, int nowMonth, int nowDay, int nowHour, Boolean isHavingTime, TimeFilterDetailDto isEffect) {
        String[] years = date.split(comma);
        for (String ys : years) {
            String[] year = ys.split(bar);
            int y = Integer.parseInt(year[0]);
            int m = Integer.parseInt(year[1]);
            int d = Integer.parseInt(year[2]);
            if (isHavingTime) {
                int h = Integer.parseInt(year[3]);
                if (y == nowYear && m == nowMonth && d == nowDay && h == nowHour) {
                    isEffect.setResult(timeFilterDetailDto.getIsEffect());
                    break;
                }
            } else {
                if (y == nowYear && m == nowMonth && d == nowDay) {
                    isEffect.setResult(timeFilterDetailDto.getIsEffect());
                    break;
                }
            }
        }
    }
}
