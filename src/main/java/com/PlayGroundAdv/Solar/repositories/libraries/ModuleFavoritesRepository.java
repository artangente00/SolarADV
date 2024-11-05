package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.ModuleLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleFavoritesRepository extends JpaRepository<ModuleLibraryEntity ,Long>{ 
	
	List<ModuleLibraryEntity> findAllByAuthentificationEntityId(Long id);
	ModuleLibraryEntity findOneByAuthentificationEntityIdAndCmodulev2Id (Long userId,Long moduleId);
	List<ModuleLibraryEntity> findByAuthentificationEntityIdAndCmodulev2IdIn (Long userId,List<Long> moduleId);
	List<ModuleLibraryEntity> findAllByCmodulev2Id (Long moduleId);
	Boolean existsByAuthentificationEntityIdAndCmodulev2Id(Long userId,Long moduleId);
	
	@Query("SELECT u.id from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :p1 ") 
	List<Long> findFavoritesIds(@Param("p1") Long userId);
	
	@Query("SELECT u.cmodulev2.id from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteModules(@Param("idUser") Long userId);
}
