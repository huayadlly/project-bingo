package cn.taike.bingo.exception;

/**
 * Created by huayadlly on 2017/8/16.
 */
public class IllegalUserTokenException extends Exception {

    public IllegalUserTokenException() {
    }

    public IllegalUserTokenException(String errorMessage) {
        super(errorMessage);
    }

}
