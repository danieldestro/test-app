package my.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import my.app.entity.MonthBreakdown;

@Repository
public class MonthBreakdownRepositoryImpl implements MonthBreakdownRepository {

    private static final Logger LOG = LoggerFactory.getLogger(MonthBreakdownRepositoryImpl.class);

    @PersistenceContext
    private EntityManager       entityManager;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:10}")
    private int                 batchSize;

    @Override
    public void bulkSave(List<MonthBreakdown> monthBreakdowns) {

        LOG.debug("MonthBreakdown batch insert size: {}", batchSize);
        int lines = 0;
        int batchExecutions = 0;
        entityManager.flush();
        for (MonthBreakdown monthBreakdown : monthBreakdowns) {
            lines++;
            entityManager.persist(monthBreakdown);

            if (lines % batchSize == 0 && lines > 0) {
                entityManager.flush();
                entityManager.clear();
                batchExecutions++;

                LOG.debug("MonthBreakdown batch insert #{} executed", batchExecutions);
            }
        }
        LOG.debug("MonthBreakdown batch insert executed #{} times in total for {} lines", batchExecutions, lines);
    }
}