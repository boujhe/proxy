package org.s2pe.dao;

/**
 * 目标对象数据库连接层接口
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 14:17
 */
public interface IndexDao {
    /**
     * 目标对象数据库连接层方法
     */
    public void query();

    public String list(int id, String name);
}
