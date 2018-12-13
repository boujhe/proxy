package org.s2pe.sta.ex;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 以继承的方式实现静态代理，完成对IndexDao时间记录的扩展测试类
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 15:53
 */
public class LogProxyTest {

    @Test
    public void query() {
//        创建代理对象
        LogProxy logProxy = new LogProxy ();
//        调用代理类的代理方法
        logProxy.query ();
    }


}