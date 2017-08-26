package cn.taike.spring.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by huayadlly on 2017/8/26.
 */
public class Main {

    // 1
    public static void testMyConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UseFunctionService use = context.getBean(UseFunctionService.class);
        String sayHello = use.sayHello("java ,China");

        System.out.println(sayHello);
        context.close();
    }

    // 2
    public static void testBeanScope() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

        ScopeSample scopeSample = context.getBean(ScopeSample.class);
        ScopeSample scopeSample2 = context.getBean(ScopeSample.class);

        System.out.println("相等？：" + scopeSample.equals(scopeSample2));
        context.close();
    }

    public static void main(String[] args) {
        testMyConfig();             // 测试配置文件
        testBeanScope();            // scope


    }
}
