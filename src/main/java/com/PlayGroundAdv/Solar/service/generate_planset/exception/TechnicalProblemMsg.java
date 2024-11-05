package com.PlayGroundAdv.Solar.service.generate_planset.exception;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.project.SubmitProjectService;

@Service
public class TechnicalProblemMsg {

	final SubmitProjectService submitProjectService;
	
	
	public TechnicalProblemMsg(SubmitProjectService submitProjectService) {
		super();
		this.submitProjectService = submitProjectService;
	}

	//CR-2424
	public void traiterException(Exception e) {
		Boolean contain = false;
		int k = 0;
		if (e != null) {
			while (Boolean.FALSE.equals(contain) && k < e.getStackTrace().length) {
				if (e.getStackTrace()[k].toString().contains("com.PlayGroundAdv.Solar")) {
					contain = true;
				} else {
					k++;
				}
			}
		}
		try {
			if (Boolean.TRUE.equals(contain)) {
				String exceptionInfo = "<li>Error cause : " + e + ".</li><li>Error location : In file "
						+ e.getStackTrace()[k].getFileName() + " within the method : "
						+ e.getStackTrace()[k].getMethodName() + " at line : " + e.getStackTrace()[k].getLineNumber()
						+ ".</li>";
				submitProjectService.technicalProblemMail(exceptionInfo,null);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
