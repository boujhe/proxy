package org.s2pe.dao;

/**
 * 目标对象数据库连接层实现
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 14:18
 */
public class IndexDaoImpl implements IndexDao {
    /**
     * 模拟目标对象数据库连接层方法实现
     */
    @Override
    public void query() {
        System.out.println ("query");
    }

    @Override
    public String list(int id, String name) {
        return name;
    }
}
