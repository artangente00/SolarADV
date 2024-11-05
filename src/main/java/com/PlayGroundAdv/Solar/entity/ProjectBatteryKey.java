package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectBatteryKey implements Serializable {
	
	@Column(name = "project_id")
    Long projectId;

    @Column(name = "battery_id")
    Long batteryId;
    
    private ProjectBatteryKey() {}
    
    public ProjectBatteryKey(
        Long projectId,
        Long batteryId) {
        this.projectId = projectId;
        this.batteryId = batteryId;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ProjectBatteryKey that = (ProjectBatteryKey) o;
        return Objects.equals(projectId, that.projectId) &&
               Objects.equals(batteryId, that.batteryId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(projectId, batteryId);
    }
}
