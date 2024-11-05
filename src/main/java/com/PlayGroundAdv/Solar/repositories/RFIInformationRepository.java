package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RFInformationEntity;

@Repository
public interface RFIInformationRepository extends JpaRepository<RFInformationEntity, Long> {

	List<RFInformationEntity> findAllByIdPermitId(Long idPermit);

	List<AuthentificationEntity> findIdAdvUserCoByIdPermitId(Long idPermit);

	List<AuthentificationEntity> findOneIdAdvUserCoByIdPermitId(Long idPermit);

	AuthentificationEntity findFirstIdAdvUserCoByIdPermitId(Long idPermit);

	@Query("SELECT u FROM RFInformationEntity u WHERE u.attributeName = :p1 AND split_part(u.advQuestion,'::', 2) = :p2")
	List<RFInformationEntity> findByAttributeNameAndSplitAdvQuestion(@Param("p1") String attributeName,
			@Param("p2") String advQuestion);
}
