package cn.taike.bingo.util;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by huayadlly on 2017/8/17.
 */
public interface BeJson {

    default String toJson() throws JsonProcessingException {
        return DataFormatUtils.toJson(this);
    }

    default String toJsonNoException() {
        return DataFormatUtils.toJsonNoException(this);
    }
}
