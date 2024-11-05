package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.RFIConfirmOfSubmitEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RFIConfirmOfSubmitRepository extends JpaRepository<RFIConfirmOfSubmitEntity,Long> {
}
