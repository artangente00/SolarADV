package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;
import com.PlayGroundAdv.Solar.entity.TLDSheetLibraryEntity;
import com.PlayGroundAdv.Solar.repositories.sheets.RsheetsLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.TLDSheetLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;

@Service
public class SheetsMassUpdate {

	final AuthenticationRepository userRepo;
	final TLDSheetLibraryRepository tldSheetLibraryRepo;
	final RsheetsLibraryRepository rsheetsRepo;

	public SheetsMassUpdate(AuthenticationRepository userRepo, TLDSheetLibraryRepository tldSheetLibraryRepo,
			RsheetsLibraryRepository rsheetsRepo) {
		super();
		this.userRepo = userRepo;
		this.tldSheetLibraryRepo = tldSheetLibraryRepo;
		this.rsheetsRepo = rsheetsRepo;
	}

	static final String PATH = Constants.rapportThreeLineDiagramFolderUrl;
	static final String PATH_R_SHEET = Constants.rapportRSheetFolderUrl;
	static final String R_SHEET = "ReferenceSheets/";
	static final String TLD = "threeLinesSheets/";

	public void updateName() {
		List<RsheetsLibraryEntity> list = rsheetsRepo.findByIsDeletedFalse();
		try {
			for (RsheetsLibraryEntity l : list) {
				if (l.getPdfName() != null && l.getPdfName().contains("XR1000 Rail _ Local Pipe")) {
					l.setPdfName(l.getPdfName().replace("XR1000 Rail _ Local Pipe","XR1000 Rail & Local Pipe"));
					rsheetsRepo.save(l);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void groundRailRackingRsheet() {
		try {
			File folder = new File(PATH_R_SHEET + "massUpdate_rail_racking");
			File[] l = folder.listFiles();
			for (int i = 0; i < l.length; i++) {
				if (l[i].isFile()) {
					String name = l[i].getName();
					System.out.println("************  " + name + "  *************");
					RsheetsLibraryEntity s = rsheetsRepo.findOneByPdfNameAndIsDeleted(l[i].getName(), false);
					if (s != null && new File(PATH_R_SHEET + TLD + l[i].getName()).exists()) {
						System.out.println(l[i].isFile() + " exist");
					} else {
						RsheetsLibraryEntity sheet = new RsheetsLibraryEntity();
						sheet.setGroundRailRacking(true);
						sheet.setPdfName(name.replace("Rail _ Local","Rail & Local"));
						sheet.setState(name.split("_")[0]);
						sheet.setComponentType("RACK");
						sheet.setManufacturer("IronRidge");
						sheet.setModel("Ground Mount System with XR1000 Rail & Local Pipe");
						sheet.setPipeSize(name.split("_")[5]);
						sheet.setThicknessPipe(name.split("_")[6].replace("Sch", ""));
						sheet.setModuleLayout(name.split("_")[7]);
						sheet.setBracedOrUnbraced(name.split("_")[8]);
						sheet.setFootingDiameter(name.split("_")[9]);
						sheet.setExposureCategory(name.split("_")[10].replace("EX-", "").replace(").pdf", ""));
						sheet.setIsDeleted(false);
						sheet.setLastUpdate(new Date());
						sheet.setAddedBy(userRepo.findById(30680050L).orElse(null));
						rsheetsRepo.save(sheet);
						moveFile(PATH_R_SHEET + R_SHEET + l[i].getName(),
								PATH_R_SHEET + "massUpdate_rail_racking/" + l[i].getName().replace("Rail _ Local","Rail & Local"));
					}

				}
			}
			System.out.println("************  DONE  *************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tldMassUpdate(String n) {
		try {
			File folder = new File(PATH + n);
			File[] l = folder.listFiles();
			for (int i = 0; i < l.length; i++) {
				if (l[i].isFile()) {
					TLDSheetLibraryEntity s = tldSheetLibraryRepo.findOneByPdfNameAndIsDeleted(l[i].getName(), false);
					if (s != null && new File(PATH + TLD + l[i].getName()).exists()) {
						s.setLastUpdate(new Date());
						s.setUpdatedBy(userRepo.findById(20262551L).orElse(null));
						tldSheetLibraryRepo.save(s);
						moveFile(PATH + TLD + l[i].getName(), PATH + n + "/" + l[i].getName());
					} else {
						List<TLDSheetLibraryEntity> tldList = tldSheetLibraryRepo
								.findByPdfNameAndIsDeleted(l[i].getName(), false);
						if (tldList != null && !tldList.isEmpty()) {
							System.out.println(l[i].getName() + "  exist");
						} else {
							TLDSheetLibraryEntity tldSheetsList = new TLDSheetLibraryEntity();
							tldSheetsList.setPdfName(l[i].getName());
							tldSheetsList.setIsDeleted(false);
							tldSheetsList.setLastUpdate(new Date());
							tldSheetsList.setAddedBy(userRepo.findById(20262551L).orElse(null));
							tldSheetLibraryRepo.save(tldSheetsList);
							moveFile(PATH + TLD + l[i].getName(), PATH + n + "/" + l[i].getName());
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void moveFile(String oldf, String newf) throws IOException {
		Path from = Paths.get(newf);
		Path to = Paths.get(oldf);
		Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
	}
}
