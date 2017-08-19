package cn.taike.paper.service;

import cn.taike.bingo.util.DataFormatUtils;
import cn.taike.paper.domain.PaperInfoEntity;
import cn.taike.paper.domain.PaperInfoEntityJpaRepository;
import cn.taike.paper.handler.CompositionEvaluationHandler;
import cn.taike.paper.handler.RecognitionPaperHandler;
import cn.taike.paper.protocol.ImageRecognitionResponse;
import cn.taike.paper.web.RemoteAppController;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    private CompositionEvaluationHandler compositionEvaluationHandler;

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

            // invoke evaluation composition
            compositionEvaluationHandler.submitComposition(response.getUser_id(), response.getPaper_id(), response.getPage_id(), "text");

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

            // invoke evaluation composition
            compositionEvaluationHandler.submitComposition(response.getUser_id(), response.getPaper_id(), response.getPage_id(), "text");
        }
    }

    // get paper list
    public List<RemoteAppController.ResponseList> getList(Long userId) {

        List<RemoteAppController.ResponseList> resultList = Lists.newArrayList();
        List<PaperInfoEntity> list = paperInfoEntityJpaRepository.findByUserId(userId);
        list.forEach(paperInfoEntity -> {
            RemoteAppController.ResponseList response = new RemoteAppController.ResponseList();

            response.setUser_id(userId);
            response.setPaper_id(paperInfoEntity.getPaperId());
            response.setPage_id(paperInfoEntity.getPageId());
            response.setPaper_name(paperInfoEntity.getPaperName());

            resultList.add(response);
        });
        return resultList;

    }

    // get paper list
    public RemoteAppController.ResponseToApp getCompositionDetail(Long userId, RemoteAppController.ResponseList request) {

        RemoteAppController.ResponseToApp response = new RemoteAppController.ResponseToApp();
        RemoteAppController.ResponseList res = new RemoteAppController.ResponseList();
        Optional<PaperInfoEntity> optional = paperInfoEntityJpaRepository.findByUserIdAndPaperIdAndPageId(userId, request.getPaper_id(), request.getPage_id());
        if (optional.isPresent()) {
            PaperInfoEntity entity = optional.get();

            res.setUser_id(userId);
            res.setPaper_id(request.getPaper_id());
            res.setPage_id(request.getPage_id());
            res.setPaper_name(entity.getPaperName());

            response.setResponse(res);
            response.setEvaluation(entity.getCompositionEvaluations());
            response.setUpdate_time(entity.getUpdateTime().toString());
        }
        return response;
    }
}
