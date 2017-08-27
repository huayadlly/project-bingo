package cn.taike.spring.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huayadlly on 2017/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("prod")
public class DemoTest {

    @Autowired
    private TestBeans testBeans;

    @Test
    public void testTestBeanDev() {

        String expected = "prod profile Bean";
        String actual = testBeans.getName();

//        Assert.assertEquals(expected, actual);
    }

}
