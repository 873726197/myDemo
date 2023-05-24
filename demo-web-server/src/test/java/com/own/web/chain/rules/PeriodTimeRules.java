package com.own.web.chain.rules;

import com.own.web.chain.Handler;
import com.own.web.chain.HandlerChain;
import com.own.web.chain.TimeFilterDetailDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author liuChang
 * @date 2023/4/12 17:27
 * @describe 指定时间点的规则 每天7点、8点、9点、11点、12点、13点不生效
 */

@Data
@Accessors(chain = true)
public class PeriodTimeRules implements Handler {

    private TimeFilterDetailDto timeFilterDetailDto;

    @Override
    public Integer order() {
        return this.timeFilterDetailDto.getSortNum();
    }

    @Override
    public void doHandler(TimeFilterDetailDto isEffect, HandlerChain handlerChain) {
        String times = timeFilterDetailDto.getPeriodTime();
        String[] split = times.split(",");
        boolean hour = Arrays.stream(split).anyMatch(h -> h.equals(String.valueOf(LocalDateTime.now().getHour())));
        isEffect.setResult(timeFilterDetailDto.getIsEffect() == hour);
        handlerChain.doHandler(isEffect);
    }
}
