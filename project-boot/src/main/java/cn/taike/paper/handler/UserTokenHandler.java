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

    public  Long exchangeToken(String token) throws IllegalUserTokenException {
        try {
            return new Long(token);

        } catch (IllegalArgumentException e) {  // assert it's illegalUserTokenException
            switch (e.getMessage()) {
                case "404":
                    log.warn("User token handler, user token not found.");
                    throw new IllegalUserTokenException(e.toString());
                default:
                    log.error("User token handler, user token error.", e);
                    throw e;
            }
        } catch (Exception e) {
            log.error("User token handler, user token error.", e);
            throw e;
        }
    }


}
