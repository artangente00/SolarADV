package com.PlayGroundAdv.Solar.entity.sheets;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.RequiredArgsConstructor;

@Embeddable
@RequiredArgsConstructor
public class UserCustomizeSheetKey implements Serializable {
	
	@Column(name = "sheet_id")
    Long sheetId;

    @Column(name = "user_id")
    Long userId;
    
    public UserCustomizeSheetKey(Long sheetId,Long userId) {
        this.sheetId = sheetId;
        this.userId = userId;
    }
 
    //Getters omitted for brevity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserCustomizeSheetKey that = (UserCustomizeSheetKey) o;
        return Objects.equals(sheetId, that.sheetId) &&
               Objects.equals(userId, that.userId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(sheetId, userId);
    }
}
