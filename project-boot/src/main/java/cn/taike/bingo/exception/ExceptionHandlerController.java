package cn.taike.bingo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by huayadlly on 2017/8/20.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public Object allException(Exception e) {
        log.error("THERE IS EXCEPTION ABOVE CONTROLLER.", e);
        return ResponseEntity.badRequest().body(e.toString());
    }
}
