package cn.taike.paper.domain;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PaperRecognitionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // find distinct paperId by userId order by updateTime desc
    public List<PaperInfoEntity> findDistinctPaperIdByUserId(Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaperInfoEntity> query = builder.createQuery(PaperInfoEntity.class);
        Root<PaperInfoEntity> root = query.from(PaperInfoEntity.class);

        query
                .select(root.get("paperId"))
                .distinct(true)
                .where(
                        builder.and(
                                builder.in(root.get("userId")).value(userId)
                        )
                )
                .orderBy(
                        builder.desc(root.get("updateTime"))
                );
        return entityManager.createQuery(query).getResultList();
    }

    public int countPagesDistinctHomeworkDateByGroupIdIn(List<Long> groupId, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaperInfoEntity> root = query.from(PaperInfoEntity.class);

        query
                .select(builder.countDistinct(root.get("homeworkDate")))
                .where(
                        builder.and(
                                builder.in(root.get("groupId")).value(groupId),
                                builder.lessThanOrEqualTo(root.get("homeworkDate"), LocalDate.now())
                        )
                )
                .orderBy(
                        builder.asc(root.get("homeworkDate"))
                );

        Long preResult = entityManager.createQuery(query).getSingleResult();
        return (int) Math.ceil((double) preResult / size);
    }
}
