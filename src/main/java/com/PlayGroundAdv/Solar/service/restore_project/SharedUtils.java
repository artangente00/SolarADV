package com.PlayGroundAdv.Solar.service.restore_project;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class SharedUtils {
	
	final CheckValueTypesService checkValue;
	
	public SharedUtils(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}


	public String findStringValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	 return checkValue.isStringNotEmpty(row.getCell(2).getStringCellValue()) ? row.getCell(2).getStringCellValue() : null;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public Date findDateValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	 return row.getCell(2).getDateCellValue();
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public Boolean findBooleanValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	String b = row.getCell(2).getStringCellValue();
	            	return b != null ? b.equals("t") : null;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

	
	public Integer findIntValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	String v = row.getCell(2).getStringCellValue();
	            	return v != null && checkValue.isStringNotEmpty(v) ? Integer.valueOf(v) : null;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

	
	public Long findLongValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	String v = row.getCell(2).getStringCellValue();
	            	return v != null && checkValue.isStringNotEmpty(v) ? Long.valueOf(v) : null;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

	
	public Float findFloatValue(HSSFSheet sheet, String nameToSearch) {
		try {
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
	            if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	            	String v = row.getCell(2).getStringCellValue();
	            	 return v != null && checkValue.isStringNotEmpty(v) ? Float.valueOf(v) : null;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
}
