package cn.taike.spring.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by huayadlly on 2017/8/26.
 */
public class Main {

    public static void testProfile() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("product");          // 先将活动的profile设置为 product
        context.register(ProfileConfig.class);                          // 注册Bean的配置类
        context.refresh();                                              // 刷新容器

        ProfileSample profileSample = context.getBean(ProfileSample.class);
        System.out.println(profileSample.getContext());

        context.close();
    }

    public static void main(String[] args) {
        testProfile();
    }
}
