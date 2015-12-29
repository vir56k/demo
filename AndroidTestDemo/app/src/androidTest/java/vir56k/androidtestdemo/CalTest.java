package vir56k.androidtestdemo;

import junit.framework.TestCase;

/**
 * Created by zhangyunfei on 15/11/24.
 */
public class CalTest extends TestCase {
    Cal cal;

    public void setUp() throws Exception {
        super.setUp();
        cal = new Cal();
    }

    public void tearDown() throws Exception {

    }

    public void testAdd() throws Exception {
        int res = cal.add(10, 1);
        assertEquals(11,res);
    }
}