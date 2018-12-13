package org.s2pe.sta.ex;

/**
 * 原始的目标对象及加上时间记录的扩展逻辑又加上日志的扩展逻辑，
 * 可以将LogProxy.class类作为目标对象进行扩展
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 19:42
 */
public class TimeLogProxy extends LogProxy {//继承LogProxy

    /**
     * 将LogProxy作为目标对象，该类为LogProxy类的代理对象，
     * 在这里覆盖的方法中调用父类的构造方法并家属代理类的逻辑。
     */
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
