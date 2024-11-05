package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.model.EngineersModel;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineersRepository extends JpaRepository<Engineers,Long>, JpaSpecificationExecutor<Engineers> {
	
	Page<Engineers> findByIdIn(List<Long> engineersIDList, Pageable pageable);	
	
	List<String> findCompanyByCompanyAndContactAndLicenseState(String company, String contact, String licenseState);

	// M.A 25-12
	public static final String FILTER_ENGINEERS_LIST_MODEL = "SELECT u.id from Engineers u where CONCAT('%',upper(u.company),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.contact),'%') LIKE CONCAT('%',upper(:p2),'%') and CONCAT('%',upper(u.licenseState),'%') LIKE CONCAT('%',upper(:p3),'%') and (u.id NOT IN :p4 OR :p4  = NULL ) and u.isDeleted = :p5";


	@Query(value = FILTER_ENGINEERS_LIST_MODEL)
	public List<Long> filterEngineersListModel(@Param("p1") String company, @Param("p2") String contact, @Param("p3") String licenseType,@Param("p4") List<Long> engineersList, @Param("p5") Boolean isDeleted);
	
    Boolean existsByCompanyAndContactAndLicenseStateAndIsDeleted(String company, String contact , String licenceState, Boolean isDeleted);
	
	public static final String NON_DELETED_ENGINEERS_CONTACT_AND_STATE = "SELECT new com.PlayGroundAdv.Solar.model.EngineersModel(u.id, u.contact, u.licenseState) from Engineers u where u.isDeleted = false order by u.company ASC";


	@Query(value = NON_DELETED_ENGINEERS_CONTACT_AND_STATE)
	public List<EngineersModel> nonDeletedEngineersContactAndState();

	Page<Engineers> findByIsDeleted(Boolean deleted, Pageable pageable);
	
}
