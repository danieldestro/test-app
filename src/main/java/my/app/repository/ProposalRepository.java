package my.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import my.app.entity.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer>, JpaSpecificationExecutor<Proposal> {
}