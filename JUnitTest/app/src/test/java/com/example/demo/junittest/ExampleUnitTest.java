package com.example.demo.junittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        com.example.demo.junittest.Test.test(8);

    }
    @Test
    public void addition_isCorrect1() {
        assertEquals(4, 2 + 2);
        com.example.demo.junittest.Test.test(8);
    }
    @Before
    public void bMethod(){
        System.out.println("每个方法执行之前执行");
    }
    @After
    public void aMethod(){
        System.out.println("没给方法执行之后执行");
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("所有方法执行之前执行");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("所以方法执行完成之后执行");

    }
    @Ignore
    public void ignore(){
        //加了这个注解，测试的时候会忽略当前方法（一般用在耗时较长的方法或者还没有写好的方法上面）
    }

}