package com.PlayGroundAdv.Solar.entity.libraries;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class GeneratorFavorite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn
	@ManyToOne
	private AuthentificationEntity idUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGenerator")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Generator idGenerator;

	public GeneratorFavorite(AuthentificationEntity idUser, Generator idGenerator) {
		super();
		this.idUser = idUser;
		this.idGenerator = idGenerator;
	}
	
	
	
}
