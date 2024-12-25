package com.own.web.chain;

/**
 * @author liuChang
 * @date 2023/4/13 11:12
 * @describe 责任链的主流程逻辑接口
 */
public interface HandlerChain {

    void doHandler(TimeFilterDetailDto isEffect);

}
