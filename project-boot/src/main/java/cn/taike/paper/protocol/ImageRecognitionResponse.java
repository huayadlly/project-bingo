package cn.taike.paper.protocol;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by huayadlly on 2017/8/16.
 */
@Data
public class ImageRecognitionResponse {

    private Long user_id;
    private String task_id;

    private String paper_id;
    private String paper_name;
    private String page_id;

    private String warp_info;
    private List<QuestionAndAnswer> qas = Lists.newArrayList();    // 识别结果
    private Map<String, String> sub_img_keys = Maps.newHashMap();  // 图片位置
}
