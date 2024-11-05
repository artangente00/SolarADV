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
public class ProjectBattery {
	
	@EmbeddedId
	ProjectBatteryKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    PermitEnergyBatterySystem projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("batteryId")
    @JoinColumn(name = "battery_id")
    Battery batteryId;

    @Column
    private Integer quantity;
    
    private ProjectBattery() {}
    
    public ProjectBattery(PermitEnergyBatterySystem projectId, Battery batteryId,Integer quantity) {
        this.projectId = projectId;
        this.batteryId = batteryId;
        this.quantity = quantity;
        this.id = new ProjectBatteryKey(projectId.getId(), batteryId.getId());
    }
 
    //Getters and setters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ProjectBattery that = (ProjectBattery) o;
        return Objects.equals(projectId, that.projectId) &&
               Objects.equals(batteryId, that.batteryId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(projectId, batteryId);
    }

	public void setProjectId(PermitEnergyBatterySystem projectId) {
		this.projectId = projectId;
	}

	public void setBatteryId(Battery batteryId) {
		this.batteryId = batteryId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
