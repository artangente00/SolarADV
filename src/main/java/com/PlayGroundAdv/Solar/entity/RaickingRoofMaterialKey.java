package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RaickingRoofMaterialKey implements Serializable {
	@Column(name = "raicking_id")
    Long detailRackingId;

    @Column(name = "roof_material_id")
    Long roofMaterialId;
    
    private RaickingRoofMaterialKey() {}
    
    public RaickingRoofMaterialKey(
        Long detailRackingId,
        Long roofMaterialId) {
        this.detailRackingId = detailRackingId;
        this.roofMaterialId = roofMaterialId;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RaickingRoofMaterialKey that = (RaickingRoofMaterialKey) o;
        return Objects.equals(detailRackingId, that.detailRackingId) &&
               Objects.equals(roofMaterialId, that.roofMaterialId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(detailRackingId, roofMaterialId);
    }
}
