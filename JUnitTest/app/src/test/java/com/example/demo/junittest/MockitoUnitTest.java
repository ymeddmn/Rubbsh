package com.example.demo.junittest;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoUnitTest {

    @Mock
    Animinal animinal;

    /**
     * 通过thenReturn提前设置返回的值
     */
    @Test
    public void thenReturn(){
        when(animinal.getName()).thenReturn("默认返回狗");
        System.out.println(animinal.getName());
    }

    /**
     * 在我们设置的回调中修改返回值
     */
    @Test
    public void thenAnswer(){
        when(animinal.getName()).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocatzion) throws Throwable {
                Animinal a = (Animinal) invocatzion.getMock();
                return "哈哈";
            }
        });
        System.out.println(animinal.getName());
    }

    /**
     * doReturn提前设置要返回的值
     */
    @Test
    public void doReturn1(){
        Mockito.doReturn("哈哈哈doReturn1").when(animinal).getName();
        System.out.println(animinal.getName());
    }

    /**
     * 提前拦截返回值
     */
    @Test
    public void doAnswer1(){
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return "提前设置返回值";
            }
        }).when(animinal).getName();
        System.out.println(animinal.getName());
    }
}
