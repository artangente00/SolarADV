package com.PlayGroundAdv.Solar.entity.sheets;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PlansetCustomizeSheets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table
@RequiredArgsConstructor
public class UserCustomizeSheets implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	UserCustomizeSheetKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sheetId")
    @JoinColumn(name = "sheet_id")
    PlansetCustomizeSheets sheetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    AuthentificationEntity userId;
    
    public UserCustomizeSheets(PlansetCustomizeSheets sheetId, AuthentificationEntity userId) {
        this.sheetId = sheetId;
        this.userId = userId;
        this.id = new UserCustomizeSheetKey(sheetId.getId(), userId.getId());
    }
 
    //Getters and setters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserCustomizeSheets that = (UserCustomizeSheets) o;
        return Objects.equals(sheetId, that.sheetId) &&
               Objects.equals(userId, that.userId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(sheetId, userId);
    }

	public void setProjectId(PlansetCustomizeSheets sheetId) {
		this.sheetId = sheetId;
	}

	public void setBatteryId(AuthentificationEntity userId) {
		this.userId = userId;
	}

}
