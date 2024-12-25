package com.own.web.chain;

import lombok.Data;

import java.util.*;

/**
 * @author liuChang
 * @date 2023/4/12 17:55
 * @describe
 */
@Data
public class TimeChain implements HandlerChain {

    private final Set<Handler> handlerChain = new TreeSet<>(Comparator.comparingInt(Ordered::order).reversed());

    public TimeChain(List<Handler> handlers) {
        handlerChain.addAll(handlers);
    }

    private Iterator<Handler> iterator;

    @Override
    public void doHandler(TimeFilterDetailDto isEffect) {
        if (iterator == null) {
            iterator = this.handlerChain.iterator();
        }
        if (iterator.hasNext()) {
            Handler next = iterator.next();
            next.doHandler(isEffect, this);
        }
    }

}
