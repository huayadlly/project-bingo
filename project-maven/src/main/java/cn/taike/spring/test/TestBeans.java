package cn.taike.spring.test;

/**
 * Created by huayadlly on 2017/8/27.
 */
public class TestBeans {

    private String name;

    public TestBeans(String username) {
        super();
        this.name = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
