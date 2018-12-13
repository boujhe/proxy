package org.s2pe.sta.polymerization;

import org.s2pe.dao.IndexDao;
import org.s2pe.dao.IndexDaoImpl;
import org.junit.Test;

/**
 * 以聚合的方式实现静态代理，对IndexDap.class日志记录和时间记录的双重代理的测试类
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/11 0011上午 10:36
 */
public class TimeLogProxyTest {

    @Test
    public void TimeLogProxyTest() {
//        创建目标对象
        IndexDao indexDao = new IndexDaoImpl ();
//        创建日志记录代理对象，将IndexDao对象注入到日志代理对象中
        LogProxy logProxy = new LogProxy (indexDao);
//        创建代理对象，将日志代理对象注入到时间代理对象中
        TimeProxy TimeProxy = new TimeProxy (logProxy);
//        调用时间代理对象的代理方法
        TimeProxy.query ();
    }
}
