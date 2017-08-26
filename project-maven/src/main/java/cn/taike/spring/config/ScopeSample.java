package cn.taike.spring.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by huayadlly on 2017/8/26.
 */
@Service
@Scope("prototype")         // 默认值是singleton
public class ScopeSample {


}
