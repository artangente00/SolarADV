package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.RailRackingFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RailRackingFavoriteRepository  extends JpaRepository<RailRackingFavLibraryEntity ,Long>{ 

	List<RailRackingFavLibraryEntity> findAllByAuthentificationEntityId(Long id);
	List<RailRackingFavLibraryEntity> findAllByAuthentificationEntityIdAndRailRackingIdIn(Long userId,List<Long> railRackingId);
	Boolean existsByAuthentificationEntityId(Long id);
	RailRackingFavLibraryEntity findOneByAuthentificationEntityIdAndRailRackingId(Long userId,Long railRackingId);
	List<RailRackingFavLibraryEntity> findAllByRailRackingId (Long railRackingId);
	Boolean  existsByRailRackingId (Long railRackingId);
	Boolean existsByRailRackingIdAndAuthentificationEntityId(Long railRackingId, Long userId);
	
}
