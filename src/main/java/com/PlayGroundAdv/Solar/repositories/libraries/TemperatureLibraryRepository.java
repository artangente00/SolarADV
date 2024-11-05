package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.PlayGroundAdv.Solar.entity.TemperatureLibraryEntity;
import com.PlayGroundAdv.Solar.model.AllPostalCodeModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureLibraryRepository extends JpaRepository<TemperatureLibraryEntity,Long>{
	
	// A.B 11-18 Get All Temp By postal Code
	Page<TemperatureLibraryEntity> findByPostalCode(String postalCode, Pageable pageable);	
	
	public static final String FIND_POSTAL_CODE = "SELECT new com.PlayGroundAdv.Solar.model.AllPostalCodeModel ( u.id, u.postalCode) FROM TemperatureLibraryEntity u order by u.postalCode";
	
	@Transactional
	@Modifying
	@Query(value = FIND_POSTAL_CODE)
	public List<AllPostalCodeModel> findAllPostalCode();
}
