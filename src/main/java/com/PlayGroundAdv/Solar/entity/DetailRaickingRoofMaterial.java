package com.PlayGroundAdv.Solar.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table
public class DetailRaickingRoofMaterial {
	
	@EmbeddedId
	RaickingRoofMaterialKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("detailRackingId")
    @JoinColumn(name = "racking_id")
    SsheetRackingMappingEntity detailRackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roofMaterialId")
    @JoinColumn(name = "roof_material_id")
    RoofMaterialType roofMaterialId;
    
    private DetailRaickingRoofMaterial() {}
    
    public DetailRaickingRoofMaterial(SsheetRackingMappingEntity detailRackingId, RoofMaterialType roofMaterialId) {
        this.detailRackingId = detailRackingId;
        this.roofMaterialId = roofMaterialId;
        this.id = new RaickingRoofMaterialKey(detailRackingId.getId(), roofMaterialId.getId());
    }
 
    //Getters and setters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        DetailRaickingRoofMaterial that = (DetailRaickingRoofMaterial) o;
        return Objects.equals(detailRackingId, that.detailRackingId) &&
               Objects.equals(roofMaterialId, that.roofMaterialId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(detailRackingId, roofMaterialId);
    }

	public void setProjectId(SsheetRackingMappingEntity detailRackingId) {
		this.detailRackingId = detailRackingId;
	}

	public void setBatteryId(RoofMaterialType roofMaterialId) {
		this.roofMaterialId = roofMaterialId;
	}

}
