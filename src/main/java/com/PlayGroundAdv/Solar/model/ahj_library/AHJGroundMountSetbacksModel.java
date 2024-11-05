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
public class AHJGroundMountSetbacksModel {
	private String bedroomWindowEgressReq;
	private String groundCarportCallOutSetbacks;
	private String groundMtShowDistanceToNearest;
	private String callOutDistanceFromExistingStructures;
	private String callOutArraySolarRoofSectionDimensionsOnLayout;
	private String groundMountStructSetbacks;
	private List<String> notes;
	
	public List<ResultUpdateLog> compareFields(AHJGroundMountSetbacksModel object) throws IllegalAccessException{
	    List<ResultUpdateLog> resultList = new ArrayList<>();      
	    Field[] fields = object.getClass().getDeclaredFields();
	    for(Field field : fields){
	        try {
	        	if(!field.getName().equals("notes") && field.get(this) != null && !field.get(this).equals(field.get(object))){
		            ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(field.get(this).toString());
		            resultObject.setOldValue( field.get(object) != null ? field.get(object).toString() : null);
		            resultObject.setCategory("Ground Mount setbacks and dims");
		            resultList.add(resultObject);
		        }else if(!field.getName().equals("notes") && field.get(this) == null && field.get(object) != null){
		        	ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(null);
		            resultObject.setOldValue(field.get(object).toString());
		            resultObject.setCategory("Ground Mount setbacks and dims");
		            resultList.add(resultObject);
		        }else if(field.getName().equals("notes")) {
		        	String previousNotes = field.get(object) != null ? field.get(object).toString().replace("[", "").replace("]", "") : "";
		        	String newNotes = field.get(this) != null ? field.get(this).toString().replace("[", "").replace("]", "") : "";
		        	if (!previousNotes.equals(newNotes)) {
		        		ResultUpdateLog resultObject = new ResultUpdateLog();
			            resultObject.setFieldName("Special Notes");
			            resultObject.setNewValue(newNotes);
			            resultObject.setOldValue(previousNotes);
			            resultObject.setCategory("Ground Mount setbacks and dims");
			            resultList.add(resultObject);
					}
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return resultList;
	}
}
