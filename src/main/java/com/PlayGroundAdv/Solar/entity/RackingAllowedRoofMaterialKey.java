package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RackingAllowedRoofMaterialKey implements Serializable {
	
	private static final long serialVersionUID = 8825205626140909750L;
	
	@NonNull
    Long railRacking;
 
	@NonNull
    Long roofMaterial;

	public RackingAllowedRoofMaterialKey() {
		super();
	}

	public RackingAllowedRoofMaterialKey(Long railRacking, Long roofMaterial) {
		super();
		this.railRacking = railRacking;
		this.roofMaterial = roofMaterial;
	}

	@Override
	public int hashCode() {
		return  Objects.hash(railRacking, roofMaterial);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RackingAllowedRoofMaterialKey))
			return false;
		RackingAllowedRoofMaterialKey that = (RackingAllowedRoofMaterialKey) o;
		return Objects.equals(railRacking, that.railRacking) && Objects.equals(roofMaterial, that.roofMaterial);
	}
	
	
}
