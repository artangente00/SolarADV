package com.PlayGroundAdv.Solar.repositories.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.log.ArchiveLogEntity;

@Repository
public interface ArchiveLogRepository extends JpaRepository<ArchiveLogEntity ,Long> {

}
