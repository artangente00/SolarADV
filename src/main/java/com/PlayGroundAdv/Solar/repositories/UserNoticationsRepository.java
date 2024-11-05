package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.PlayGroundAdv.Solar.entity.UserNoticationsEntity;

@Repository
public interface UserNoticationsRepository extends JpaRepository<UserNoticationsEntity, Long> {
	
	List<UserNoticationsEntity> findByIdUserIdAndIsRead(Long idUser, Boolean isRead);
	List<UserNoticationsEntity> findByIdUserIdAndIdNotifId(Long idUser, Long idNotif);
	
	Page<UserNoticationsEntity> findByIdUserIdAndIsReadAndIdNotifTitleOrderByIdNotifDateNotifDesc(Long id, Boolean isread, String title, Pageable pageable);
	Page<UserNoticationsEntity> findByIdUserIdAndIdNotifTitleOrderByIdNotifDateNotifDesc(Long id, String title, Pageable pageable);

}
