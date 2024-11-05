package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RFIQuestionEntity;
import com.PlayGroundAdv.Solar.model.RFIQuestionEntityModel;

@Repository
public interface RFIQuestionRepository extends JpaRepository<RFIQuestionEntity,Long>, JpaSpecificationExecutor<RFIQuestionEntity> {

	Page<RFIQuestionEntity> findByFieldName(String fieldName, Pageable pageable);
	Page<RFIQuestionEntity> findByFieldNameAndQuestionActived(String fieldName, boolean isActivated , Pageable pageable);
	
	Page<RFIQuestionEntity> findAllByQuestionActived(boolean isActivated , Pageable pageable);
	List<RFIQuestionEntity> findByFieldNameAndQuestionActived(String fieldName, boolean isActivated);
	
	@Query(value="SELECT u FROM RFIQuestionEntity u WHERE u.id_RFIQuestion = :p1")
	RFIQuestionEntity findByIdRFIQuestion(@Param("p1") Long idQuestion);
	
	@Query(value="SELECT DISTINCT r.fieldName from RFIQuestionEntity r ORDER BY r.fieldName")
	List<String> findDistinctFieldName();

	
	@Query("SELECT new  com.PlayGroundAdv.Solar.model.RFIQuestionEntityModel(u.id_RFIQuestion, u.fieldName, u.questionstatic, u.rfiQuestion, " + 
			" u.questionActived, u.addedBy, u.confirmation, u.isRFIDocument, " + 
			" u.isAttachementShown) from RFIQuestionEntity u where u.fieldName = :p1 and CONCAT(u.questionstatic,' ',u.rfiQuestion) = :p2") 
	RFIQuestionEntityModel findByQuestion(@Param("p1") String fieldName, @Param("p2") String rfiQuestion);

}
