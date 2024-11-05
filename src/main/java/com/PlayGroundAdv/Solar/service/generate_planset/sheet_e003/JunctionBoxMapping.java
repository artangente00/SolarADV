package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

/*
 * @author Soumeya
 */

//S.B CR-PM-3516-MOD-002 07/13/2021
@Service
public class JunctionBoxMapping {
	
	public void junctionBoxQty(AcroFields form, int sheetIndex, Integer junctionQty, String transitioningPVWire, String index) {
		try {
			if(transitioningPVWire != null && transitioningPVWire.equals("Rooftop Junction box(es)")) {
				if(junctionQty != null) {
					form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+index+"-QTY",  junctionQty+"");
				}else {
					form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+index+"-QTY",  "1");
				}
			}else {
				form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+index+"-QTY",  "0");
			}
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		} 
	}

}
