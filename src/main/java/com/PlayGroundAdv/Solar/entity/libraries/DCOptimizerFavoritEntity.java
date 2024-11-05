package com.PlayGroundAdv.Solar.entity.libraries;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

@Entity
@Table(name = "DCOptimizerFavoritEntity")
public class DCOptimizerFavoritEntity {

private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="dcoptF_sequence32", sequenceName = "dcoptF_sequence32", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dcoptF_sequence32")    
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_OPTIMIZER")
	private DCOptimizerEntity optimizer;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private AuthentificationEntity user;


	public DCOptimizerFavoritEntity(DCOptimizerEntity optimizer, AuthentificationEntity user) {
		super();
		this.optimizer = optimizer;
		this.user = user;
	}


	public DCOptimizerFavoritEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public DCOptimizerEntity getOptimizer() {
		return optimizer;
	}


	public void setOptimizer(DCOptimizerEntity optimizer) {
		this.optimizer = optimizer;
	}


	public AuthentificationEntity getUser() {
		return user;
	}


	public void setUser(AuthentificationEntity user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "DCOptimizerFavoritEntity [id=" + id +" user=" + user + "]";
	}
	
	
	
	
}
