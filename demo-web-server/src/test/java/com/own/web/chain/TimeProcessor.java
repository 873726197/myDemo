package com.own.web.chain;

import com.own.web.chain.rules.PeriodDateRules;
import com.own.web.chain.rules.PeriodTimeRules;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuChang
 * @date 2023/4/13 11:08
 * @describe 时间规则流程入口
 */
public class TimeProcessor {

    public static TimeChain generatorHandlers(List<TimeFilterDetailDto> timeFilterDetails) {

        List<Handler> handlers = new ArrayList<>();
        for (TimeFilterDetailDto timeFilterDetail : timeFilterDetails) {
            handlers.add(new PeriodTimeRules().setTimeFilterDetailDto(timeFilterDetail));
            handlers.add(new PeriodDateRules().setTimeFilterDetailDto(timeFilterDetail));
        }
        handlers.add(new PeriodDateRules());
        handlers.add(new PeriodTimeRules());
        return new TimeChain(handlers);
    }

    public static Boolean doProcessor(TimeChain timeChain) {
        TimeFilterDetailDto timeFilterDetailDto = new TimeFilterDetailDto();
        timeFilterDetailDto.setResult(true);
        timeChain.doHandler(timeFilterDetailDto);
        return timeFilterDetailDto.getResult();
    }
}
