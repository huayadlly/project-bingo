package cn.taike.paper.protocol;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by huayadlly on 2017/8/18.
 */
@Data
public class CompositionEvaluations {

    private Metrics metrics;
    private Assessments assessments;
    private List<Comments> comments;

    @Data
    public static class Metrics {

        private Integer chars;
        private Integer words;
        private Integer sentences;
    }

    @Data
    public static class Assessments {

        private Integer spelling;
        private Integer clarity;
        private Integer grammar;
        private Integer concision;
        private Integer logic;
    }

    @Data
    public static class Comments {

        private String text;
        private String module;
        private String feature;
        private String directness;
        private String subfeature;
        private List<List<Integer>> indices = Lists.newArrayList();

        private String modifyAdvise;     // 修改意见
        private String originalText;     // 原文文字
        private String replacementText;  // 替换文字
    }
}
