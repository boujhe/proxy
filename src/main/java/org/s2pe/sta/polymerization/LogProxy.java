package org.s2pe.sta.polymerization;

import org.s2pe.dao.IndexDao;

/**
 * 以聚合的方式实现静态代理，对IndexDap.class日志记录的扩展类
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/11 0011上午 9:31
 */
public class LogProxy  implements IndexDao {//实现与目标对象相同的IndexDao.class接口
    /**
     * 添加与目标对象相同的IndexDao.class的属性
     */
    private IndexDao indexDao;

    /**
     * 添加构造方法，把目标对象注入到代理类的目标对象中
     * @param indexDao 目标对象
     */
    public LogProxy(IndexDao indexDao) {
//        把目标对象注入到代理类的目标对象中
        this.indexDao = indexDao;
    }

    @Override
    public void query() {
//        编写需要在原有逻辑运行之前的逻辑
        System.out.println ("------Log-------");
//        调用目标对象的目标方法运行原有的逻辑
        indexDao.query ();
//        编写需要在原有逻辑运行之前的逻辑
        System.out.println ("-----Log End---");
    }

    @Override
    public String list(int id, String name) {
        return name;
    }
}
