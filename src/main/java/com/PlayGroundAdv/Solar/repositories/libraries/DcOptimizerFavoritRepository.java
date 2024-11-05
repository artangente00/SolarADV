package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerFavoritEntity;
import com.PlayGroundAdv.Solar.model.ConverterFavModelResult;
import org.springframework.stereotype.Repository;

@Repository
public interface DcOptimizerFavoritRepository extends JpaRepository<DCOptimizerFavoritEntity, Long> {

	DCOptimizerFavoritEntity findFirstByOptimizerIdAndUserId(Long idOptimizer, Long idUser);

	List<DCOptimizerFavoritEntity> findByOptimizerId(Long id);

	List<DCOptimizerFavoritEntity> findByUserId(Long idUser);

	List<DCOptimizerFavoritEntity> findByUserIdAndOptimizerIdIn(Long idUser, List<Long> idOptimizer);

	Boolean existsByOptimizerIdAndUserId(Long idOpti, Long idUser);


	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ConverterFavModelResult( u.optimizer.id, u.optimizer.manufacturer , u.optimizer.model ) "
			+ "FROM DCOptimizerFavoritEntity u , PermitEntity v WHERE v.id = :p1 AND u.user.id = v.authentificationEntity.id AND u.optimizer.isDeleted = false ORDER BY u.optimizer.manufacturer,  u.optimizer.model ")
	public List<ConverterFavModelResult> getConverterAllFavModel(@Param("p1") Long idPermit);


	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ConverterFavModelResult( u.optimizer.id, u.optimizer.manufacturer , u.optimizer.model ) "
			+ "FROM DCOptimizerFavoritEntity u WHERE u.user.id = :p1 AND  u.optimizer.isDeleted = false ORDER BY u.optimizer.manufacturer,  u.optimizer.model ")
	public List<ConverterFavModelResult> getConverterFavModel(@Param("p1") Long idUser);

}
