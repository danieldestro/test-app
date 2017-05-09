package my.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import my.app.entity.PriceSummary;
import my.app.entity.Proposal;

public interface PriceSummaryRepository extends JpaRepository<PriceSummary, Integer>, JpaSpecificationExecutor<PriceSummary> {

    PriceSummary findByProposal(Proposal proposal);
}