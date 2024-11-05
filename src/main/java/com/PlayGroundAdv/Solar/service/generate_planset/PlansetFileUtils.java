package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.File;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;

@Service
public class PlansetFileUtils {
	
	
	public File createFile(int sheetIndex, Long projectId) {
		
		String path = Constants.rapportPlansetFolderUrl + "PDF-E002-STRING" + projectId + "-" + sheetIndex + ".pdf";
		try {
			File fileRe = new File(path);
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(path);
			}
			return fileRe;
		} catch (Exception e) {
			e.printStackTrace();
			return new File(path);
		}
	}
	
	

}

