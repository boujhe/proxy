package org.s2pe.sta.ex;

import org.s2pe.dao.IndexDaoImpl;

/**
 * 以继承的方式实现静态代理，完成对IndexDao时间记录的扩展。
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 15:48
 */
public class TimeProxy extends IndexDaoImpl {//继承需要增强的目标类
    @Override
    public void query() {
//        编写需要在原有逻辑运行之前的逻辑
        System.out.println ("------Time-------");
//        调用父类的方法运行原有的逻辑
        super.query ();
//        编写需要在原有逻辑运行之前的逻辑
        System.out.println ("-----Time End----");
    }
}
