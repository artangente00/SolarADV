package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.model.SpacingDetailsMappingModel;
import com.PlayGroundAdv.Solar.model.SsheetRackingModel;
import com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel;
import com.PlayGroundAdv.Solar.model.libraries.RaikingPageRequest;
import com.PlayGroundAdv.Solar.repositories.DetailRaickingRoofMaterialRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetRackingMappingRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetSpacingMappingRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class SsheetRackingmapping {

	final CheckValueTypesService checkValue;
	final SsheetRackingMappingRepository sheetRackingRepo;
	final SsheetSpacingMappingRepository sheetSpacingRepo;
	final SsheetLibraryRepository sheetLibraryRepo;
	final AuthenticationRepository userRepo;
	final RoofMaterialTypeRepository roofMaterialRepo;
	final DetailRaickingRoofMaterialRepository detailRaickingRoofMaterialRepo;
	public SsheetRackingmapping(CheckValueTypesService checkValue, SsheetRackingMappingRepository sheetRackingRepo, SsheetSpacingMappingRepository sheetSpacingRepo,
			SsheetLibraryRepository sheetLibraryRepo, AuthenticationRepository userRepo,
			RoofMaterialTypeRepository roofMaterialRepo, DetailRaickingRoofMaterialRepository detailRaickingRoofMaterialRepo) {
		super();
		this.checkValue = checkValue;
		this.sheetRackingRepo = sheetRackingRepo;
		this.sheetSpacingRepo = sheetSpacingRepo;
		this.sheetLibraryRepo = sheetLibraryRepo;
		this.userRepo = userRepo;
		this.roofMaterialRepo = roofMaterialRepo;
		this.detailRaickingRoofMaterialRepo = detailRaickingRoofMaterialRepo;
	}
	
	public Page<SsheetRackingModel> filterRaiking(RaikingPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("pdfname"));
			String roofType = checkValue.isStringNotEmpty(request.getRoofType())
					? request.getRoofType().toUpperCase()
					: null;
			String roofTypeOther = checkValue.isStringNotEmpty(request.getRoofTypeOther())
					? request.getRoofTypeOther().toUpperCase()
					: null;
			String rackingManufacturer = checkValue.isStringNotEmpty(request.getRackingManufacturer())
					? request.getRackingManufacturer().toUpperCase()
					: null;
			String rackingModel = checkValue.isStringNotEmpty(request.getRackingModel())
					? request.getRackingModel().toUpperCase()
					: null;
			String roofManufacturer = checkValue.isStringNotEmpty(request.getRoofManufacturer())
					? request.getRoofManufacturer().toUpperCase()
					: null;
			String roofModel = checkValue.isStringNotEmpty(request.getRoofModel())
					? request.getRoofModel().toUpperCase()
					: null;
			String flashingManufacturer = checkValue.isStringNotEmpty(request.getFlashingManufacturer())
					? request.getFlashingManufacturer().toUpperCase()
					: null;
			String sheetNumber = checkValue.isStringNotEmpty(request.getSheetNumber())
					? request.getSheetNumber().toUpperCase()
					: null;
			String quadNumber = checkValue.isStringNotEmpty(request.getQuadNumber())
					? request.getQuadNumber().toUpperCase()
					: null;
			if(roofType == null && roofTypeOther == null && rackingManufacturer == null && rackingModel == null && roofManufacturer == null 
					&& roofModel == null && flashingManufacturer == null && sheetNumber == null && quadNumber == null ) {
				Page<SsheetRackingMappingEntity> page = sheetRackingRepo.findByIsDeleted(false, pageable);
				return convertDto(page, pageable);				
			}else {
				Page<SsheetRackingMappingEntity> p = sheetRackingRepo.findAll(filterRaiking(roofType, roofTypeOther, rackingManufacturer,
						rackingModel, roofManufacturer, roofModel, flashingManufacturer, sheetNumber, quadNumber), pageable);
				return convertDto(p, pageable);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<SsheetRackingMappingEntity> filterRaiking(String roofType, String roofTypeOther, String rackingManufacturer, String rackingModel, String roofManufacturer,
			String roofModel, String flashingManufacturer, String sheetNumber, String quadNumber) {

		return new Specification<SsheetRackingMappingEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SsheetRackingMappingEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicateRoofType  = roofType != null
						? cb.like(cb.upper(root.get("roofType")), "%" + roofType + "%")
						: null;
				Predicate predicateRoofTypeOther  = roofTypeOther != null
						? cb.like(cb.upper(root.get("roofTypeOther")), "%" + roofTypeOther + "%")
						: null;
				Predicate predicateRackingManufacturer  = rackingManufacturer != null
						? cb.like(cb.upper(root.get("rackingManufacturer")), "%" + rackingManufacturer + "%")
						: null;
				Predicate predicateRackingModel  = rackingModel != null
						? cb.like(cb.upper(root.get("rackingModel")), "%" + rackingModel + "%")
						: null;
				Predicate predicateRoofManufacturer  = roofManufacturer != null
						? cb.like(cb.upper(root.get("roofManufacturer")), "%" + roofManufacturer + "%")
						: null;
				Predicate predicateRoofModel  = roofModel != null
						? cb.like(cb.upper(root.get("roofModel")), "%" + roofModel + "%")
						: null;
				Predicate predicateFlashingManufacturer  = flashingManufacturer != null
						? cb.like(cb.upper(root.get("flashingManufacturer")), "%" + flashingManufacturer + "%")
						: null;
				Predicate predicateSheetNumber  = sheetNumber != null
						? cb.like(cb.upper(root.get("sheetNumber")), "%" + sheetNumber + "%")
						: null;
				Predicate predicateQuadNumber  = quadNumber != null
						? cb.like(cb.upper(root.get("quadNumber")), "%" + quadNumber + "%")
						: null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), false);

				List<Predicate> list = Arrays.asList(predicateRoofType, predicateRoofTypeOther, predicateRackingManufacturer, predicateRackingModel, predicateRoofManufacturer,
						predicateRoofModel, predicateFlashingManufacturer, predicateSheetNumber, predicateQuadNumber, predicateDeleted);

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	

	private Page<SsheetRackingModel> convertDto(Page<SsheetRackingMappingEntity> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new SsheetRackingModel(
							c.getId(), c.getRoofType(), 
							c.getRoofTypeOther(),detailRaickingRoofMaterialRepo.findRoofMaterialIds(c.getId()),c.getRackingManufacturer(),
							c.getRackingModel(),c.getRoofManufacturer(), c.getRoofModel(), c.getMountingType(),
							c.getFlashingManufacturer(),c.getSheetNumber(),c.getQuadNumber(),c.getDetailsHeading(),c.getAhj(),
							c.getUtilityCompany(),c.getSSheetFile().getId(),c.getSSheetFile().getPdfName(),
							false,c.getFlashing(),c.getState(),c.getStanchionType()
							)).collect(Collectors.toList()),pageable, page.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<SsheetSpacingMappingModel> filterSpacing(RaikingPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("pdfname"));
			String roofType = checkValue.isStringNotEmpty(request.getRoofType())
					? request.getRoofType().toUpperCase()
					: null;
			String roofTypeOther = checkValue.isStringNotEmpty(request.getRoofTypeOther())
					? request.getRoofTypeOther().toUpperCase()
					: null;
			String rafterTrussSpacing = checkValue.isStringNotEmpty(request.getRafterTrussSpacing())
					? request.getRafterTrussSpacing().toUpperCase()
					: null;
			String stanchionMaxSpacing = checkValue.isStringNotEmpty(request.getStanchionMaxSpacing())
					? request.getStanchionMaxSpacing().toUpperCase()
					: null;
			String sheetNumber = checkValue.isStringNotEmpty(request.getSheetNumber())
					? request.getSheetNumber().toUpperCase()
					: null;
			String quadNumber = checkValue.isStringNotEmpty(request.getQuadNumber())
					? request.getQuadNumber().toUpperCase()
					: null;
			if(roofType == null && roofTypeOther == null && rafterTrussSpacing == null && stanchionMaxSpacing == null && sheetNumber == null && quadNumber == null ) {
				Page<SsheetSpacingMappingEntity> page = sheetSpacingRepo.findByIsDeleted(false, pageable);
				return convertDtoSpacing(page, pageable);				
			}else {
				Page<SsheetSpacingMappingEntity> p = sheetSpacingRepo.findAll(filterSpacing(roofType, roofTypeOther, rafterTrussSpacing,
						stanchionMaxSpacing, sheetNumber, quadNumber), pageable);
				return convertDtoSpacing(p, pageable);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<SsheetSpacingMappingEntity> filterSpacing(String roofType, String roofTypeOther, String rafterTrussSpacing, String stanchionMaxSpacing,
			String sheetNumber, String quadNumber) {

		return new Specification<SsheetSpacingMappingEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SsheetSpacingMappingEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicateRoofType  = roofType != null
						? cb.like(cb.upper(root.get("roofType")), "%" + roofType + "%")
						: null;
				Predicate predicateRoofTypeOther  = roofTypeOther != null
						? cb.like(cb.upper(root.get("roofTypeOther")), "%" + roofTypeOther + "%")
						: null;
				Predicate predicateRafterTrussSpacing  = rafterTrussSpacing != null
						? cb.like(cb.upper(root.get("rafterTrussSpacing")), "%" + rafterTrussSpacing + "%")
						: null;
				Predicate predicateStanchionMaxSpacing  = stanchionMaxSpacing != null
						? cb.like(cb.upper(root.get("stanchionMaxSpacing")), "%" + stanchionMaxSpacing + "%")
						: null;
				Predicate predicateSheetNumber  = sheetNumber != null
						? cb.like(cb.upper(root.get("sheetNumber")), "%" + sheetNumber + "%")
						: null;
				Predicate predicateQuadNumber  = quadNumber != null
						? cb.like(cb.upper(root.get("quadNumber")), "%" + quadNumber + "%")
						: null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), false);

				List<Predicate> list = Arrays.asList(predicateRoofType, predicateRoofTypeOther, predicateRafterTrussSpacing, predicateStanchionMaxSpacing,
						predicateSheetNumber, predicateQuadNumber, predicateDeleted);

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	
	private Page<SsheetSpacingMappingModel> convertDtoSpacing(Page<SsheetSpacingMappingEntity> page, Pageable pageable) {
		try {
			return new PageImpl<SsheetSpacingMappingModel>(
					page.stream().map(c -> new SsheetSpacingMappingModel(
							c.getDetailsHeading(),
							c.getId(),
							c.getLastUpdate(), 
							c.getSSheetFile().getId(),
							c.getPdfname(), 
							c.getQuadNumber(),
							c.getRafterTrussSpacing(),
							c.getRoofType(), 
							c.getRoofTypeOther(),
							c.getSheetNumber(),
							c.getStanchionMaxSpacing(),
							c.getStanchionType(),
							c.getFlashing()
							)).collect(Collectors.toList()),pageable, page.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	public String uploadHomePicture(SsheetRackingModel rackingMapping, Long id) throws IOException {

		try {
			AuthentificationEntity addedBy = userRepo.findById(id).orElse(null);
			if (rackingMapping.getUploadSheet() != null && rackingMapping.getUploadSheet().equals(true)) {
				Boolean fileExist = sheetLibraryRepo.existsByPdfNameAndIsDeleted(rackingMapping.getPdfname(), false);
				if (Boolean.TRUE.equals(fileExist)) {
					return "exist";
				} else {
					SsheetLibraryEntity sheet = new SsheetLibraryEntity();
					sheet.setPdfName(rackingMapping.getPdfname());
					sheet.setIsDeleted(false);
					sheet.setLastUpdate(new Date());
					sheet.setAddedBy(addedBy);
					sheetLibraryRepo.save(sheet);
					return addRackingSheetLogic(rackingMapping, sheet, id);
				}

			} else {
				List<SsheetRackingMappingEntity> isRackingExist = sheetRackingRepo.isRackingExist(rackingMapping.getSsheet(),
						rackingMapping.getRoofType(), rackingMapping.getRoofTypeOther(),
						rackingMapping.getRackingManufacturer(),
						rackingMapping.getRackingModel(), rackingMapping.getRoofManufacturer(),
						rackingMapping.getRoofModel(), rackingMapping.getMountingType(),
						rackingMapping.getFlashingManufacturer(), rackingMapping.getSheetNumber(),
						rackingMapping.getQuadNumber(), rackingMapping.getDetailsHeading(), rackingMapping.getAhj(),
						rackingMapping.getUtilityCompany());
				
				if (rackingMapping.getRoofMaterialType() == null || rackingMapping.getRoofMaterialType().isEmpty()) {
					isRackingExist = isRackingExist.stream()
						    .filter(p -> p.getRoofMaterialTypes() == null || p.getRoofMaterialTypes().isEmpty()).collect(Collectors.toList());
					if (isRackingExist != null && !isRackingExist.isEmpty()) {
						return "exist";
					}
				}else {
					isRackingExist = isRackingExist.stream()
						    .filter(p -> p.getRoofMaterialTypes() != null || !p.getRoofMaterialTypes().isEmpty()).collect(Collectors.toList());
					if (isRackingExist != null && !isRackingExist.isEmpty()) {
						Collections.sort(rackingMapping.getRoofMaterialType());
					    
						for (SsheetRackingMappingEntity raicking : isRackingExist) {
							if(raicking.getRoofMaterialTypes() != null &&  !raicking.getRoofMaterialTypes().isEmpty()) {
								List<Long> ids =  new ArrayList<>(raicking.getRoofMaterialTypes().stream().map(o -> o.getRoofMaterialId().getId()).collect(Collectors.toList()));  
								Collections.sort(ids);
								if(Boolean.TRUE.equals(rackingMapping.getRoofMaterialType().equals(ids))) {
									return "exist";
								}
							}
						}
					}
				}
				SsheetLibraryEntity sheet = sheetLibraryRepo.findById(rackingMapping.getSsheet()).orElse(null);
				return addRackingSheetLogic(rackingMapping, sheet, id);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	public String addRackingSheetLogic(SsheetRackingModel rackingMapping, SsheetLibraryEntity sheet, Long id) {

		try {
			AuthentificationEntity addedBy = userRepo.findById(id).orElse(null);
			
			SsheetRackingMappingEntity sSheetRacking = new SsheetRackingMappingEntity();
			sSheetRacking.setRoofType(rackingMapping.getRoofType());
			sSheetRacking.setRoofTypeOther(rackingMapping.getRoofTypeOther());
			sSheetRacking.setRackingManufacturer(rackingMapping.getRackingManufacturer());
			sSheetRacking.setRackingModel(rackingMapping.getRackingModel());
			sSheetRacking.setRoofManufacturer(rackingMapping.getRoofManufacturer());
			sSheetRacking.setRoofModel(rackingMapping.getRoofModel());
			sSheetRacking.setMountingType(rackingMapping.getMountingType());
			sSheetRacking.setFlashingManufacturer(rackingMapping.getFlashingManufacturer());
			sSheetRacking.setAhj(rackingMapping.getAhj());
			sSheetRacking.setUtilityCompany(rackingMapping.getUtilityCompany());
			sSheetRacking.setSheetNumber(rackingMapping.getSheetNumber());
			sSheetRacking.setQuadNumber(rackingMapping.getQuadNumber());
			sSheetRacking.setDetailsHeading(rackingMapping.getDetailsHeading());
			sSheetRacking.setPdfname(sheet.getPdfName());
			sSheetRacking.setSSheetFile(sheet);
			sSheetRacking.setIsDeleted(false);
			sSheetRacking.setLastUpdate(new Date());
			sSheetRacking.setAddedBy(addedBy);
			sSheetRacking.setFlashing(rackingMapping.getFlashing());
			sSheetRacking.setState(rackingMapping.getState());
			sSheetRacking.setStanchionType(rackingMapping.getStanchionType());
			if(rackingMapping.getRoofMaterialType() != null && !rackingMapping.getRoofMaterialType().isEmpty()) {
				sSheetRacking.setRoofMaterialTypes(new ArrayList<>());
				for (Long roofType : rackingMapping.getRoofMaterialType()) {
					RoofMaterialType roofMaterialType = checkValue.isLongPositive(roofType)
							? roofMaterialRepo.findById(roofType).orElse(null)
							: null;
					if(roofMaterialType != null) {
						sSheetRacking.addRoofType(roofMaterialType);
					}
				}
			}
			sheetRackingRepo.save(sSheetRacking);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	public String addSpacingLogic(SpacingDetailsMappingModel rackingMapping, Long id) {

		try {
			SsheetLibraryEntity sheetFile = sheetLibraryRepo.findById(rackingMapping.getSsheet()).orElse(null);
			if (sheetFile != null) {
				
				List<SsheetSpacingMappingEntity> ssm = sheetRackingRepo.getSsheetSpacingMappingEntity(rackingMapping.getSsheet(), rackingMapping.getRoofType(), rackingMapping.getRafterTrussSpacing(), rackingMapping.getStanchionMaxSpacing(), rackingMapping.getSheetNumber(), rackingMapping.getQuadNumber(), false, rackingMapping.getStanchionType());

				if (ssm != null && !ssm.isEmpty()) {

					return "exist";
				} else {
					AuthentificationEntity addedBy = userRepo.findById(id).orElse(null);
					if(addedBy != null) {
						SsheetSpacingMappingEntity sSheetRacking = new SsheetSpacingMappingEntity();
						sSheetRacking.setRoofType(rackingMapping.getRoofType());
						sSheetRacking.setRoofTypeOther(rackingMapping.getRoofTypeOther());
						sSheetRacking.setRafterTrussSpacing(rackingMapping.getRafterTrussSpacing());
						sSheetRacking.setStanchionMaxSpacing(rackingMapping.getStanchionMaxSpacing());
						sSheetRacking.setSheetNumber(rackingMapping.getSheetNumber());
						sSheetRacking.setQuadNumber(rackingMapping.getQuadNumber());
						sSheetRacking.setDetailsHeading(rackingMapping.getDetailsHeading());
						sSheetRacking.setPdfname(sheetFile.getPdfName());
						sSheetRacking.setSSheetFile(sheetFile);
						sSheetRacking.setLastUpdate(new Date());
						sSheetRacking.setAddedBy(addedBy);
						sSheetRacking.setIsDeleted(false);
						sSheetRacking.setStanchionType(rackingMapping.getStanchionType());
						sSheetRacking.setFlashing(rackingMapping.getFlashing());
						sheetSpacingRepo.save(sSheetRacking);
					}
					return "Done";
				}

			}
			return "Fail";

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public String addLogic(MultipartFile file, String siteSurveyiD, String fileName, String uploadnewSheet)
			throws IOException {

		try {
			byte[] bytes5 = file.getBytes();
			new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/").mkdirs();
			Path pathUp5 = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + fileName);
			Files.write(pathUp5, bytes5);

			return "Done";
		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}
	}

	public String addNewSSheet(MultipartFile file, String useriD, String fileName, String uploadnewSheet)
			throws IOException {

		try {
			Boolean fileExsit = sheetLibraryRepo.existsByPdfNameAndIsDeleted(fileName, false);
			if (Boolean.TRUE.equals(fileExsit)) {
				return "exist::";
			} else {
				AuthentificationEntity addedBy = userRepo.findById(Long.valueOf(useriD)).orElse(null);
				SsheetLibraryEntity rSheetsList = new SsheetLibraryEntity();
				rSheetsList.setPdfName(fileName);
				rSheetsList.setIsDeleted(false);
				rSheetsList.setLastUpdate(new Date());
				rSheetsList.setAddedBy(addedBy);
				sheetLibraryRepo.save(rSheetsList);

				byte[] bytes5 = file.getBytes();
				new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/").mkdirs();
				Path pathUp5 = Paths
						.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + fileName);
				Files.write(pathUp5, bytes5);

				return "Done::" + rSheetsList.getId();
			}
		} catch (Exception e) {

			e.printStackTrace();
			return "error::";
		}
	}

	public List<SsheetLibraryEntity> getSsheetspdfs() throws IOException {
		try {
			return sheetLibraryRepo.findByIsDeleted(false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String editRackingMapping(Long idUser, SsheetRackingModel rackingMapping) {

		try {
			if (Boolean.TRUE.equals(rackingMapping.getUploadSheet())) {

				Boolean fileExsit = sheetLibraryRepo.existsByPdfNameAndIsDeleted(rackingMapping.getPdfname(), false);
				if (Boolean.TRUE.equals(fileExsit)) {
					return "exist";
				} else {
					AuthentificationEntity addedBy = userRepo.findById(idUser).orElse(null);
					SsheetLibraryEntity sheet = new SsheetLibraryEntity();
					sheet.setPdfName(rackingMapping.getPdfname());
					sheet.setIsDeleted(false);
					sheet.setLastUpdate(new Date());
					sheet.setAddedBy(addedBy);
					sheetLibraryRepo.save(sheet);
					return editSheetRacking(rackingMapping, sheet);
				}
			} else {
				SsheetLibraryEntity sheet = sheetLibraryRepo.findById(rackingMapping.getSsheet()).orElse(null);
				return editSheetRacking(rackingMapping, sheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	public String editSheetRacking(SsheetRackingModel rackingMapping, SsheetLibraryEntity sheet) {

		try {
			SsheetRackingMappingEntity sSheetRacking = sheetRackingRepo.findById(rackingMapping.getId()).orElse(null);
			if (sSheetRacking != null) {
				sSheetRacking.setRoofType(rackingMapping.getRoofType());
				sSheetRacking.setRoofTypeOther(rackingMapping.getRoofTypeOther());
				sSheetRacking.setRackingManufacturer(rackingMapping.getRackingManufacturer());
				sSheetRacking.setRackingModel(rackingMapping.getRackingModel());
				sSheetRacking.setRoofManufacturer(rackingMapping.getRoofManufacturer());
				sSheetRacking.setRoofModel(rackingMapping.getRoofModel());
				sSheetRacking.setMountingType(rackingMapping.getMountingType());
				sSheetRacking.setFlashingManufacturer(rackingMapping.getFlashingManufacturer());
				sSheetRacking.setAhj(rackingMapping.getAhj());
				sSheetRacking.setUtilityCompany(rackingMapping.getUtilityCompany());
				sSheetRacking.setSheetNumber(rackingMapping.getSheetNumber());
				sSheetRacking.setQuadNumber(rackingMapping.getQuadNumber());
				sSheetRacking.setDetailsHeading(rackingMapping.getDetailsHeading());
				sSheetRacking.setPdfname(rackingMapping.getPdfname());
				sSheetRacking.setFlashing(rackingMapping.getFlashing());
				sSheetRacking.setState(rackingMapping.getState());
				sSheetRacking.setSSheetFile(sheet);
				sSheetRacking.setIsDeleted(false);
				sSheetRacking.setLastUpdate(new Date());
				sSheetRacking.setStanchionType(rackingMapping.getStanchionType());
				sSheetRacking.getRoofMaterialTypes().clear();
				
				if(rackingMapping.getRoofMaterialType() != null && !rackingMapping.getRoofMaterialType().isEmpty()) {
					detailRaickingRoofMaterialRepo.deleteByDetailRackingIdIdAndRoofMaterialIdIdNotIn(sSheetRacking.getId(), rackingMapping.getRoofMaterialType());
					for (Long roofType : rackingMapping.getRoofMaterialType()) {
						RoofMaterialType roofMaterialType = checkValue.isLongPositive(roofType)
								? roofMaterialRepo.findById(roofType).orElse(null)
								: null;
						if(roofMaterialType != null) {
							sSheetRacking.addRoofType(roofMaterialType);
						}
					}
				}else {
					detailRaickingRoofMaterialRepo.deleteByDetailRackingIdId(sSheetRacking.getId());
				}
				sheetRackingRepo.save(sSheetRacking);
				return "Done";
			} else {
				return "Fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	public String editSpacingMapping(SpacingDetailsMappingModel rackingMapping, Long id, Long idSsheet)
			throws IOException {

		try {

			if (rackingMapping != null && checkValue.NotEquals(rackingMapping.getUploadSheet(), true)) {
				SsheetLibraryEntity sSheet = sheetLibraryRepo.findById(rackingMapping.getSsheet()).orElse(null);
				if (sSheet != null ) {
					
					List<SsheetSpacingMappingEntity> ssm1 = sheetRackingRepo.getSsheetSpacingMappingEntity1(rackingMapping.getSsheet(), rackingMapping.getRoofType(), rackingMapping.getRafterTrussSpacing(), rackingMapping.getStanchionMaxSpacing(), rackingMapping.getSheetNumber(), rackingMapping.getQuadNumber(), false, id, rackingMapping.getStanchionType());

					if (!ssm1.isEmpty()) {

						return "exist";
					} else {
						SsheetSpacingMappingEntity sSheetRacking = sheetSpacingRepo.findById(id).orElse(null);
						sSheetRacking.setRoofType(rackingMapping.getRoofType());
						sSheetRacking.setRoofTypeOther(rackingMapping.getRoofTypeOther());
						sSheetRacking.setRafterTrussSpacing(rackingMapping.getRafterTrussSpacing());
						sSheetRacking.setStanchionMaxSpacing(rackingMapping.getStanchionMaxSpacing());
						sSheetRacking.setSheetNumber(rackingMapping.getSheetNumber());
						sSheetRacking.setQuadNumber(rackingMapping.getQuadNumber());
						sSheetRacking.setDetailsHeading(rackingMapping.getDetailsHeading());
						sSheetRacking.setPdfname(sSheet.getPdfName());
						sSheetRacking.setSSheetFile(sSheet);
						sSheetRacking.setLastUpdate(new Date());
						sSheetRacking.setStanchionType(rackingMapping.getStanchionType());
						sSheetRacking.setFlashing(rackingMapping.getFlashing());
						sheetSpacingRepo.save(sSheetRacking);

						return "Done";
					}
				} else
					return "Fail";
			} else {

				SsheetSpacingMappingEntity sSheetRacking = sheetSpacingRepo.findById(id).orElse(null);
				sSheetRacking.setRoofType(rackingMapping.getRoofType());
				sSheetRacking.setRoofTypeOther(rackingMapping.getRoofTypeOther());
				sSheetRacking.setRafterTrussSpacing(rackingMapping.getRafterTrussSpacing());
				sSheetRacking.setStanchionMaxSpacing(rackingMapping.getStanchionMaxSpacing());
				sSheetRacking.setSheetNumber(rackingMapping.getSheetNumber());
				sSheetRacking.setQuadNumber(rackingMapping.getQuadNumber());
				sSheetRacking.setDetailsHeading(rackingMapping.getDetailsHeading());
				sSheetRacking.setPdfname(rackingMapping.getPdfname());
				sSheetRacking.setStanchionType(rackingMapping.getStanchionType());
				sSheetRacking.setFlashing(rackingMapping.getFlashing());
				SsheetLibraryEntity sSheet = sheetLibraryRepo.findById(idSsheet).orElse(null);
				if (sSheet != null) {
					sSheetRacking.setSSheetFile(sSheet);
				}
				sSheetRacking.setLastUpdate(new Date());
				sheetSpacingRepo.save(sSheetRacking);

				return "Done";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "Fail";
		}

	}

	public String deleteRackingMapping(Long sheetID, Long userID) {

		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(null);
			SsheetRackingMappingEntity rackingSheetsList =  sheetRackingRepo.getSSHRME(sheetID, null, false);
			rackingSheetsList.setDeletedBy(deletedBy);
			rackingSheetsList.setDeletedOn(new Date());
			rackingSheetsList.setIsDeleted(true);
			sheetRackingRepo.save(rackingSheetsList);
			return "done";
		} catch (Exception e) {

			e.printStackTrace();
			return "fail";
		}

	}

	public String deleteSpacingMapping(Long sheetID, Long userID) {

		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(null);
			SsheetSpacingMappingEntity rackingSheetsList = sheetRackingRepo.getSSHSME(sheetID, null, false);
			rackingSheetsList.setDeletedBy(deletedBy);
			rackingSheetsList.setDeletedOn(new Date());
			rackingSheetsList.setIsDeleted(true);
			sheetSpacingRepo.save(rackingSheetsList);
			return "done";
		} catch (Exception e) {

			e.printStackTrace();
			return "fail";
		}

	}

	public Long getIdSheetFromLibrary(String fileName) {

		Long idSheet = 0L;
		try {
			List<SsheetLibraryEntity> sSheet =sheetRackingRepo.getByPdfName(fileName);
			if (sSheet != null && sSheet.size() == 1) {
				idSheet = sSheet.get(0).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSheet;
	}

}
