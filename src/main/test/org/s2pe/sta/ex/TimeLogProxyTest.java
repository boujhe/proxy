package org.s2pe.sta.ex;

import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/10 0010下午 19:51
 */
public class TimeLogProxyTest {

    @Test
    public void query() {
        TimeLogProxy timeLogProxy = new TimeLogProxy ();
        timeLogProxy.query ();
    }
}