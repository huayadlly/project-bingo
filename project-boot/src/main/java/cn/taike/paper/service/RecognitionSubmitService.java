package cn.taike.paper.service;

import cn.taike.bingo.util.DataFormatUtils;
import cn.taike.paper.domain.PaperInfoEntity;
import cn.taike.paper.domain.PaperInfoEntityJpaRepository;
import cn.taike.paper.handler.RecognitionPaperHandler;
import cn.taike.paper.protocol.ImageRecognitionResponse;
import cn.taike.paper.web.RemoteAppController;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Service
public class RecognitionSubmitService {

    @Autowired
    private RecognitionPaperHandler recognitionPaperHandler;
    @Autowired
    private PaperInfoEntityJpaRepository paperInfoEntityJpaRepository;

    public void submit(Long userId, RemoteAppController.ImageRequest imageRequest) {
        String taskId = UUID.randomUUID().toString();
        recognitionPaperHandler.sendImage(userId, taskId, imageRequest.getImgUrl());
    }

    public void saveRecognitionResponse(ImageRecognitionResponse response) {

        Optional<PaperInfoEntity> optional = paperInfoEntityJpaRepository.findByUserIdAndPaperIdAndPageId(response.getUser_id(), response.getPaper_id(), response.getPage_id());
        if (optional.isPresent()) {

            // update
            PaperInfoEntity paperInfoEntity = optional.get();
            paperInfoEntity.setTaskId(response.getTask_id());

            paperInfoEntity.setWrapInfo(response.getWarp_info());
            paperInfoEntity.setQas(DataFormatUtils.toJsonNoException(response.getQas()));
            paperInfoEntity.setSubImgKeys(DataFormatUtils.toJsonNoException(response.getSub_img_keys()));
            paperInfoEntity.setUpdateTime(new DateTime());

            paperInfoEntityJpaRepository.save(paperInfoEntity);

            // TODO publish event

        } else {
            // save
            PaperInfoEntity entity = new PaperInfoEntity();

            entity.setUserId(response.getUser_id());
            entity.setTaskId(response.getTask_id());

            entity.setPaperId(response.getPaper_id());
            entity.setPageId(entity.getPageId());
            entity.setPaperName(response.getPaper_name());
            entity.setWrapInfo(response.getWarp_info());

            entity.setQas(DataFormatUtils.toJsonNoException(response.getQas()));
            entity.setSubImgKeys(DataFormatUtils.toJsonNoException(response.getSub_img_keys()));

            paperInfoEntityJpaRepository.save(entity);

            // TODO publish event
        }
    }
}
