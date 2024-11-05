package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;

public class DCOptimizerFavoritEntityModel {
	private Long id;
	private DCOptimizerEntity optimizer;
	private AuthentificationEntity user;

	public DCOptimizerFavoritEntityModel() {
		super();
	}

	public DCOptimizerFavoritEntityModel(Long id, DCOptimizerEntity optimizer, AuthentificationEntity user) {
		super();
		this.id = id;
		this.optimizer = optimizer;
		this.user = user;
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

}
