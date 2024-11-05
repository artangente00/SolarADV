package com.PlayGroundAdv.Solar.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContactsNameEntity;

@Repository
public interface ContactsNameRepository extends JpaRepository<ContactsNameEntity, Long> {

	Integer countByIdUser(AuthentificationEntity idUser);
}
