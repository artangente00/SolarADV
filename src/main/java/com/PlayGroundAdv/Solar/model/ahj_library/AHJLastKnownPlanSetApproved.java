package com.PlayGroundAdv.Solar.model.ahj_library;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJLastKnownPlanSetApproved {
	
	private String projectName1;
	private String projectlink1;
	private String projectName2;
	private String projectlink2;
	private String projectName3;
	private String projectlink3;
	
	public List<ResultUpdateLog> compareFields(AHJLastKnownPlanSetApproved object) throws IllegalAccessException{
	    List<ResultUpdateLog> resultList = new ArrayList<>();      
	    Field[] fields = object.getClass().getDeclaredFields();
	    for(Field field : fields){
	        try {
	        	if(field.get(this) != null && !field.get(this).equals(field.get(object))){
		            ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(field.get(this).toString());
		            resultObject.setOldValue( field.get(object) != null ? field.get(object).toString() : null);
		            resultObject.setCategory("Last Known Plan Set to be Approved by AHJ");
		            resultList.add(resultObject);
		        }else if(field.get(this) == null && field.get(object) != null){
		        	ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(null);
		            resultObject.setOldValue(field.get(object).toString());
		            resultObject.setCategory("Last Known Plan Set to be Approved by AHJ");
		            resultList.add(resultObject);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return resultList;
	}
}
