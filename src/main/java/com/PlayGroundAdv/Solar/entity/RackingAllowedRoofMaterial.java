package com.PlayGroundAdv.Solar.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RackingAllowedRoofMaterial {
	
	@EmbeddedId
	private RackingAllowedRoofMaterialKey id;
 
    @ManyToOne
    @MapsId("roofMaterial_id")
    @JoinColumn(name = "roofMaterial")
    private RoofMaterialType roofMaterial;
 
    @ManyToOne
    @MapsId("railRacking_id")
    @JoinColumn(name = "railRacking")
    private RailRacking railRacking;
    
	public RackingAllowedRoofMaterial() {
		super();
	}
    
	public RackingAllowedRoofMaterial(RackingAllowedRoofMaterialKey id) {
		super();
		this.id = id;
	}

	public RackingAllowedRoofMaterial(RoofMaterialType roofMaterial, RailRacking railRacking) {
		super();
		this.roofMaterial = roofMaterial;
		this.railRacking = railRacking;
	}
	
}
