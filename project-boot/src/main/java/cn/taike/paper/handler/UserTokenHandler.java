package cn.taike.paper.handler;

import cn.taike.bingo.exception.IllegalUserTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/8/16.
 */
@Slf4j
@Component
public class UserTokenHandler {

    public static Long exchangeToken(String token) throws IllegalUserTokenException {
        return Long.valueOf(token);
    }


}
