package org.s2pe.util;

import org.junit.Test;
import org.s2pe.dao.IndexDao;
import org.s2pe.dao.IndexDaoImpl;

import static org.junit.Assert.*;

/**
 * @author Strive to Positive Energy.
 * @Description: TODO
 * @date 2018/12/11 0011下午 15:17
 */
public class ProxyUtilTest {

    @Test
    public void newInstance() {
        IndexDao indexDao = (IndexDao) ProxyUtil.newInstance (new IndexDaoImpl ());
        indexDao.query ();
        System.out.println (indexDao.list (1, "proxy"));
    }
}