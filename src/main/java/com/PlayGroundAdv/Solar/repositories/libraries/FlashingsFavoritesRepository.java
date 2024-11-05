package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.FlashingFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashingsFavoritesRepository extends JpaRepository<FlashingFavLibraryEntity ,Long>{
	List<FlashingFavLibraryEntity> findAllByAuthentificationEntityId(Long id);
	List<FlashingFavLibraryEntity> findAllByAuthentificationEntityIdAndFlashingIdIn(Long id, List<Long>idJbox);
	FlashingFavLibraryEntity findOneByAuthentificationEntityIdAndFlashingId(Long userId,Long flashingId);
	List<FlashingFavLibraryEntity> findAllByAuthentificationEntityIdAndFlashingId(Long userId,Long flashingId);
	List<FlashingFavLibraryEntity> findAllByFlashingId (Long flashingId);
	Boolean existsByAuthentificationEntityIdAndFlashingId(Long userId,Long flashingId);
	
	@Query("SELECT u.flashing.id from FlashingFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteFlashings(@Param("idUser") Long userId);
}
