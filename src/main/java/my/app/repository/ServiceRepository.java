package my.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import my.app.entity.ServiceOffer;

public interface ServiceRepository extends JpaRepository<ServiceOffer, Integer>, JpaSpecificationExecutor<ServiceOffer> {

    List<ServiceOffer> findByProposalId(Integer id);
}