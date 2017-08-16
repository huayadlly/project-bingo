package cn.taike.paper.context;

import cn.taike.paper.exception.TokenErrorException;

/**
 * Created by huayadlly on 2017/8/16.
 */
public class ContextHandler {

    public static Long exchangeToken(String token) throws TokenErrorException {
        return Long.valueOf(token);
    }



}
