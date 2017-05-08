package my.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import my.app.entity.PriceSummary;

public interface PriceSummaryRepository extends JpaRepository<PriceSummary, Integer>, JpaSpecificationExecutor<PriceSummary> {
}