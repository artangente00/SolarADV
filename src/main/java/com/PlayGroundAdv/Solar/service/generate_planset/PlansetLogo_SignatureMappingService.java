package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlansetLogo_SignatureMappingService {

	final CheckValueTypesService checkValueTypesService;
	final UserSettingRepository userSettingRepo;

	public PlansetLogo_SignatureMappingService(CheckValueTypesService checkValueTypesService,
			UserSettingRepository userSettingRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.userSettingRepo = userSettingRepo;
	}

	// A.B 03-30 CR-3250-MOD-004
	public void mapLogo_Signature(Long userId, PdfStamper stamper, String filePath) {
		try {

			String logoname = userSettingRepo.findLogo(userId);
			String signature = userSettingRepo.findSignature(userId);
			try {

				if (checkValueTypesService.isStringNotEmpty(logoname)
						&& new File(filePath + "img/logos/" + logoname).exists()) {

					String ext2 = FilenameUtils.getExtension(filePath + "img/logos/" + logoname);
					Image image = null;
					if (checkValueTypesService.Equals(ext2, "svg"))
						image = Image.getInstance(filePath + "img/logos/Mapping/" + logoname);
					else
						image = Image.getInstance(filePath + "img/logos/" + logoname);

					image.setRotationDegrees(90);
					if (image.getWidth() == image.getHeight()) {// A.B Square Logo
						image.setAbsolutePosition(1130f, 36.8f);// A.B Absolute Position
						image.scaleAbsolute(62f, 62f);// A.B Width, Length

					} else {// A.B Rectangular Logo Max height 39.685f & Max width 143.1496f
						float per = image.getHeight() * 100 / image.getWidth();
						float height = 39.685f;
						float width = 100 * height / per;
						while (width > 143.1496f) {
							height = height - 0.5f;
							width = 100 * height / per;
						}
						image.setAbsolutePosition(1152.315f, 36.8f);
						image.scaleAbsolute(width, height);
					}
					PdfContentByte content = stamper.getUnderContent(1);
					content.addImage(image);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {

				if (checkValueTypesService.isStringNotEmpty(signature)
						&& new File(filePath + "img/signatures/" + signature).exists()) {
					String ext = FilenameUtils.getExtension(filePath + "img/signatures/" + signature);
					Image image = null;
					if (checkValueTypesService.Equals(ext, "svg"))
						image = Image.getInstance(filePath + "img/signatures/Mapping/" + signature);
					else
						image = Image.getInstance(filePath + "img/signatures/" + signature);

					image.setRotationDegrees(90);
					image.setAbsolutePosition(1166.4882f, 181.417f);
					// A.B Signature Max height 26.929134f & Max width 93.5433f
					float per = image.getHeight() * 100 / image.getWidth();
					float height = 26.929134f;
					float width = 100 * height / per;
					while (width > 93.5433f) {
						height = height - 0.5f;
						width = 100 * height / per;
					}
					image.scaleAbsolute(width, height);

					PdfContentByte contents = stamper.getUnderContent(1);
					contents.addImage(image);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
