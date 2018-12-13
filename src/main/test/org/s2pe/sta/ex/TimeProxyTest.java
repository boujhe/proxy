package org.s2pe.sta.ex;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 16:39
 */
public class TimeProxyTest {

    @Test
    public void query() {
//        创建代理对象
        TimeProxy timeProxy = new TimeProxy ();
//        实现代理对象的代理方法
        timeProxy.query ();
    }
}