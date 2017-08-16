package cn.taike.paper.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by huayadlly on 2017/8/13.
 */
@Repository
public interface PaperInfoEntityJpaRepository extends JpaRepository<PaperInfoEntity, Long> {

    Optional<PaperInfoEntity> findByUserIdAndPaperIdAndPageId(Long userId, String paperId, String pageId);

}
