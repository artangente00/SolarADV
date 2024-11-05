package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.RailRackingOptionsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RailRackingOptionsRepository extends JpaRepository<RailRackingOptionsEntity, Long> {

}
