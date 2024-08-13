package com.simelabs.vrs.repository;

import com.simelabs.vrs.entity.VisitesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitesRepository extends JpaRepository<VisitesEntity, Long> {

}
