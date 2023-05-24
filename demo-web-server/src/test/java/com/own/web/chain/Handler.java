package com.own.web.chain;

/**
 * @author liuChang
 * @date 2023/4/13 10:39
 * @describe 每个规则具体执行的逻辑接口
 */
public interface Handler extends Ordered {

    void doHandler(TimeFilterDetailDto isEffect, HandlerChain handlerChain);

}
