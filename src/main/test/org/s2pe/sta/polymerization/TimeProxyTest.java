package org.s2pe.sta.polymerization;

import org.s2pe.dao.IndexDao;
import org.s2pe.dao.IndexDaoImpl;
import org.junit.Test;

/**
 * 以聚合的方式实现静态代理，对IndexDap.class日志记录的测试类
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/11 0011上午 9:41
 */
public class TimeProxyTest {

    @Test
    public void query() {
//        创建目标对象
        IndexDao indexDao = new IndexDaoImpl ();
//        创建代理对象，将目标对象注入到代理对象中
        TimeProxy TimeProxy = new TimeProxy (indexDao);
//        调用代理对象的代理方法
        TimeProxy.query ();
    }
}