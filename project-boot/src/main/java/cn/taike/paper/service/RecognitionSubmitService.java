package cn.taike.paper.service;

import cn.taike.paper.handler.RecognitionPaperHandler;
import cn.taike.paper.web.RemoteAppController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Service
public class RecognitionSubmitService {

    @Autowired
    private RecognitionPaperHandler recognitionPaperHandler;

    public void submit(Long userId, RemoteAppController.ImageRequest imageRequest) {
        String taskId = UUID.randomUUID().toString();
        recognitionPaperHandler.sendImage(userId, taskId, imageRequest.getImgUrl());
    }
}
