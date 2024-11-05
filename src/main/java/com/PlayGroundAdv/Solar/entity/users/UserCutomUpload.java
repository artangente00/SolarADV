package com.PlayGroundAdv.Solar.entity.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCutomUpload implements Serializable  {

	private static final long serialVersionUID = 2405172041950251807L;

	//A.B 04-11-2022 CR-806
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn
	@ManyToOne
	private AuthentificationEntity userId;
	
	@Column
	private String title;

	public UserCutomUpload(AuthentificationEntity userId, String title) {
		super();
		this.userId = userId;
		this.title = title;
	}
	
	
}
