package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RFIConfirmationEntity;

@Repository
public interface RFIConfirmRepository extends JpaRepository<RFIConfirmationEntity, Long> {

	RFIConfirmationEntity findByIdPermitId(Long idPermit);

	@Query("SELECT u.iscONTRACTORConfirm from RFIConfirmationEntity u where u.idPermit.id = :p1")
	Boolean findContractorConfirmation(@Param("p1") Long projectId);

	@Query("SELECT u.iscONTRACTORConfirm from RFIConfirmationEntity u where u.idPermit.id = :p1")
	List<Boolean> isContractorConfirmation(@Param("p1") Long projectId);

	List<RFIConfirmationEntity> findAllByIdPermitId(Long idPermit);

	RFIConfirmationEntity findOneByIdPermitId(Long idPermit);

	Boolean findOneIscONTRACTORConfirmByIdPermitId(Long idPermit);

	List<Boolean> findIscONTRACTORConfirmByIdPermitId(Long idPermit);

}
