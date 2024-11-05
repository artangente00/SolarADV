package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.libraries.GeneratorFavorite;

@Repository
public interface GeneratorFavoriteRepository extends JpaRepository<GeneratorFavorite ,Long> {

	GeneratorFavorite findByIdGeneratorIdAndIdUserId(Long comp, Long user);
	List<GeneratorFavorite> findByIdGeneratorIdInAndIdUserId(List<Long> comp, Long user);
	Boolean existsByIdGeneratorIdAndIdUserId(Long comp, Long user);
	
	@Query("SELECT u.idUser.id from GeneratorFavorite u WHERE u.idGenerator.id = :p1 ")
	List<Long> findFavoriteUserId(@Param("p1") Long id);

}
