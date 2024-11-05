package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.CityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<CityEntity,Long> {

	List<CityEntity> findByNameAndState(String cityName, String state);
}
