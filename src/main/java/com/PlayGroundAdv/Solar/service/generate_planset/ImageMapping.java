package com.PlayGroundAdv.Solar.service.generate_planset;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class ImageMapping {
	
	final CheckValueTypesService checkValueTypesService;
	
	public ImageMapping(CheckValueTypesService checkValueTypesService) {
		super();
		this.checkValueTypesService = checkValueTypesService;
	}

	//A.B 05-20 CR-3386-004: Add Google Street Image to PV-001
	public void mapUrlImageToPDF(PdfStamper stamper, float left, float bottom, float width, float length, String url) {
		try {
				URL imageUrl = new URL(url);
				BufferedImage bufferedImage = ImageIO.read(imageUrl);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "png", baos);
			    Image image  = Image.getInstance(baos.toByteArray());
				image.setAbsolutePosition(left, bottom);//A.B Absolute Position
				image.scaleAbsolute(width, length);//A.B Width, Length
				PdfContentByte content = stamper.getOverContent(1);
				content.addImage(image);
				
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		}
	}
	
}
