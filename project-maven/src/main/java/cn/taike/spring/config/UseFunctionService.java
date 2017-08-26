package cn.taike.spring.config;

/**
 * Created by huayadlly on 2017/8/26.
 */
public class UseFunctionService {

    FunctionService functionService;

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public String sayHello(String java) {
        return functionService.sayHello(java);
    }
}
