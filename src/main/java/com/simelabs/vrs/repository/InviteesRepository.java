package com.simelabs.vrs.repository;

import com.simelabs.vrs.entity.InviteesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteesRepository extends JpaRepository<InviteesEntity, Long> {

}
