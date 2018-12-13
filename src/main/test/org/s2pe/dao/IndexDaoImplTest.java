package org.s2pe.dao;

import org.junit.Test;

/**
 * 目标对象数据库连接从测试类
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 14:19
 */
public class IndexDaoImplTest {
    /**
     *目标对象测试方法
     */
    @Test
    public void query() {
        IndexDao indexDao = new IndexDaoImpl ();
        indexDao.query ();
    }
}