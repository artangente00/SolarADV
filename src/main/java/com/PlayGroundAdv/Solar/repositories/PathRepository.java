package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PlayGroundAdv.Solar.entity.PathEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<PathEntity,Long>{
	
	@Query("SELECT u.dir from PathEntity u") 
	String findFilePath();
	
	@Query("SELECT u.googleDriveFilePath from PathEntity u") 
	String findGoogleDriveFilePath();
	
	@Query("SELECT u.googleDriveEmail from PathEntity u") 
	String findGoogleDriveEmail();
	
	@Query("SELECT u.urlPath from PathEntity u ")
	String  findURLPath();
}
