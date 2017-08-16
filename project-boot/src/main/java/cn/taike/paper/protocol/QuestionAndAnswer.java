package cn.taike.paper.protocol;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by huayadlly on 2017/8/16.
 */
@Data
public class QuestionAndAnswer {

    private Boolean result_right;
    private Double result_accuracy;

    private String questionId;
    private String predict;
    private String type;
    private List<String> locations = Lists.newArrayList();
}
